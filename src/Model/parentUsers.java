package Model;

public class parentUsers {
    private int userID;
    private int username;
    private int password;
    private boolean linkedAccount;

    public int getUserID(){return userID;}
    public void setUserID(int userID){this.userID = userID;}

    public int getUsername(){return username;}
    public void setUsername(int username){this.username = username;}

    public int getPassword(){return password;}
    public void setPassword(int password){this.password = password;}

    public boolean isLinkedAccount(){return linkedAccount;}
    public void setLinkedAccount(boolean linkedAccount){this.linkedAccount = linkedAccount;}
}
