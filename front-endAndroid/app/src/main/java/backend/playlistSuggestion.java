package backend;

public class playlistSuggestion {
    private int id;
    private int playlistID;
    private String playlistName;
    private float rating;

    public playlistSuggestion(int id, int playlistID, String playlistName, float rating) {
        this.id = id;
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public float getRating() {
        return rating;
    }
}
