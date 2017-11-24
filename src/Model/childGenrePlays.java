package Model;

public class childGenrePlays {
    private int childID;
    private int artistID;
    private int timesPlayed;

    public childGenrePlays(int childID, int artistID, int timesPlayed) {
        this.childID = childID;
        this.artistID = artistID;
        this.timesPlayed = timesPlayed;
    }

    public int getChildID(){return childID;}
    public void setChildID(int childID){this.childID = childID;}

    public int getArtistID(){return artistID;}
    public void setArtistID(int artistID){this.artistID = artistID;}

    public int getTimesPlayed(){return timesPlayed;}
    public void setTimesPlayed(int timesPlayed){this.timesPlayed = timesPlayed;}
}
