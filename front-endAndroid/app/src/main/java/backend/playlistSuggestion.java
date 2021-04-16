package backend;

public class playlistSuggestion {
    private int playlistID;
    private float rating;

    public playlistSuggestion(int playlistID, float rating) {
        this.playlistID = playlistID;
        this.rating = rating;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public float getRating() {
        return rating;
    }
}
