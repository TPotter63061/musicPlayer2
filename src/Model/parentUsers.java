package Model;

public class parentUsers {
    private int userID;
    private String username;
    private String password;
    private boolean linkedAccount;

    public parentUsers(int userID, String username, String password, boolean linkedAccount) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.linkedAccount = linkedAccount;
    }

    public int getUserID(){return userID;}
    public void setUserID(int userID){this.userID = userID;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public boolean isLinkedAccount(){return linkedAccount;}
    public void setLinkedAccount(boolean linkedAccount){this.linkedAccount = linkedAccount;}
}
