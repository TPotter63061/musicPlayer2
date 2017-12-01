package Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class childUsersService {

    public static void selectAll(List<childUsers> targetList, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("SELECT childID, userID, username, password, permissionLevel FROM childUsers ORDER BY childID");
        try{
            if(statement != null){
                ResultSet results = database.executeQuery(statement);

                if(results != null){
                    while(results.next()){
                        targetList.add(new childUsers(
                                results.getInt("childID"),
                                results.getInt("userID"),
                                results.getString("username"),
                                results.getString("password"),
                                results.getInt("permissionLevel")
                        ));
                    }
                }
            }
        }catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

    public static childUsers selectById(int id, DatabaseConnection database){
        childUsers result = null;
        PreparedStatement statement = database.newStatement("SELECT childID, userID, username, password, permissionLevel FROM childUsers WHERE id=?");

        try{
            if(statement != null){
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if(results != null) {
                    result = new childUsers(
                            results.getInt("childID"),
                            results.getInt("userID"),
                            results.getString("username"),
                            results.getString("password"),
                            results.getInt("permissionLevel")
                    );
                }
            }
        }catch (SQLException resultsException){
            System.out.println("Database select by id error" + resultsException.getMessage());
        }
        return result;
    }
    public static void save(childUsers itemToSave, DatabaseConnection database) {

        childUsers existingItem = null;
        if (itemToSave.getChildID() != 0) existingItem = selectById(itemToSave.getChildID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO childUsers (childID, userID, username, password, permissionLevel) VALUES (?,?,?,?,?)");
                statement.setInt(0, itemToSave.getChildID());
                statement.setInt(1, itemToSave.getUserID());
                statement.setString(2, itemToSave.getUsername());
                statement.setString(3, itemToSave.getPassword());
                statement.setInt(4, itemToSave.getPermissionLevel());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE childUsers SET childID, userID, username, password, permissionLevel = ? WHERE childID = ?, userID = ?");
                statement.setString(2, itemToSave.getUsername());
                statement.setString(3, itemToSave.getPassword());
                statement.setInt(4, itemToSave.getPermissionLevel());

                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
    public static void deleteById(int id, DatabaseConnection database){
        PreparedStatement statement =  database.newStatement("DELETE FROM childUsers WHERE id=?");
        try{
            if(statement != null){
                statement.setInt(1, id);
                database.executeUpdate(statement);
            }
        }catch (SQLException resultsException){
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }
}
