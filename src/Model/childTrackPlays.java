package Model;

public class childTrackPlays {
    private int childID;
    private int trackID;
    private int timesPlayed;

    public childTrackPlays(int childID, int trackID, int timesPlayed) {
        this.childID = childID;
        this.trackID = trackID;
        this.timesPlayed = timesPlayed;
    }

    public int getChildID(){return childID;}
    public void setChildID(int childID){this.childID = childID;}

    public int getTrackID(){return trackID;}
    public void setTrackID(int artistID){this.trackID = artistID;}

    public int getTimesPlayed(){return timesPlayed;}
    public void setTimesPlayed(int timesPlayed){this.timesPlayed = timesPlayed;}
}

