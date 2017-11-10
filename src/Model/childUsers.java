package Model;

public class childUsers {
    private int childID;
    private int userID;
    private String username;
    private String password;
    private int permissionLevel;

    public int getChildID(){return childID;}
    public void setChildID(int childID){this.childID = childID;}

    public int getUserID(){return userID;}
    public void setUserID(int userID){this.userID = userID;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public int getPermissionLevel(){return permissionLevel;}
    public void setPermissionLevel(int permissionLevel){this.permissionLevel = permissionLevel;}
}
