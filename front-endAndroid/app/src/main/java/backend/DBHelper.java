package backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "moodBumpData.db";
    public static final String DATABASE_TABLE_ENTRIES = "diaryEntries";
    public static final String DATABASE_TABLE_SUGGESTIONS = "musicSuggestions";
    public static final String DATABASE_TABLE_PLAYLISTS = "playlists";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        buildDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_SUGGESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PLAYLISTS);
        buildDatabase(db);
    }

    private void buildDatabase(SQLiteDatabase db) {
        Log.i("DataBase", "Building Database");
        String entryTableStr = "CREATE TABLE " + DATABASE_TABLE_ENTRIES + "(id integer primary key, date integer, text varchar(1024), mood float(10), suggestionID integer, foreign key (suggestionID) references " + DATABASE_TABLE_SUGGESTIONS + "(id))";
        String suggestionsTableStr = "CREATE TABLE " + DATABASE_TABLE_SUGGESTIONS + "(id integer primary key, playlistID integer, rating, float(10), foreign key (playlistID) references " + DATABASE_TABLE_PLAYLISTS +"(id))";
        String playlistTableStr = "CREATE TABLE " + DATABASE_TABLE_PLAYLISTS + "(id integer primary key, name varchar(128))";
        db.execSQL(entryTableStr);
        db.execSQL(suggestionsTableStr);
        db.execSQL(playlistTableStr);
    }

    public void insertEntry(journalEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("text", entry.getText());
        val.put("mood", entry.getMood());
        val.put("date", entry.getDate().getTime());
        if (db.insert(DATABASE_TABLE_ENTRIES, null, val) == -1) {
            Log.e("DataBase", "Could not insert a new row");
        }
    }

    public ArrayList<journalEntry> getAllEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DATABASE_TABLE_ENTRIES, null);
        res.moveToFirst();
        ArrayList<journalEntry> entries = new ArrayList<journalEntry>();
        while (res.isAfterLast() == false) {
            Date date = new Date(res.getLong(res.getColumnIndex("date")));
            String text = res.getString(res.getColumnIndex("text"));
            float mood = res.getFloat(res.getColumnIndex("mood"));
            entries.add(new journalEntry(date, text, mood));
            res.moveToNext();
        }
        return entries;
    }
}