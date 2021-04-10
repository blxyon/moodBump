package backend;

import java.util.Date;

public class journalEntry {
    private Date date;
    private String text;
    private float mood;

    public journalEntry(Date date, String text, float mood) {
        this.date = date;
        this.text = text;
        this.mood = mood;
    }

    public Date getDate() { return date; }
    public String getText() { return text; }
    public float getMood() { return mood; }
}
