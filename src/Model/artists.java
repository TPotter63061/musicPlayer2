package Model;

public class artists {
    private int artistID;
    private String artistName;
    private String genre;

    public int getArtistID(){return artistID;}
    public void setArtistID(int artistID){this.artistID = artistID;}

    public String getArtistName(){return artistName;}
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre(){return genre;}
    public void setGenre(String genre){this.genre = genre;}
}

