import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL(
                "create table " + DATABASE_TABLE_ENTRIES +
                "id integer primary key, date date, text varchar(8192), mood float(10)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
