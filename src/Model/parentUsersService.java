package Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class parentUsersService {

    public static void selectAll(List<parentUsers> targetList, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("SELECT userID, username, password, linkedAccount FROM parentUsers ORDER BY userID");
        try{
            if(statement != null){
                ResultSet results = database.executeQuery(statement);

                if(results != null){
                    while(results.next()){
                        targetList.add(new parentUsers(
                                results.getInt("userID"),
                                results.getString("username"),
                                results.getString("password"),
                                results.getBoolean("linkedAccount")
                        ));
                    }
                }
            }
        }catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

    public static parentUsers selectById(int id, DatabaseConnection database){
        parentUsers result = null;
        PreparedStatement statement = database.newStatement("SELECT userID, username, password, linkedAccount FROM parentUsers WHERE id=?");

        try{
            if(statement != null){
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if(results != null) {
                    result = new parentUsers(
                            results.getInt("userID"),
                            results.getString("username"),
                            results.getString("password"),
                            results.getBoolean("linkedAccount")
                    );
                }
            }
        }catch (SQLException resultsException){
            System.out.println("Database select by id error" + resultsException.getMessage());
        }
        return result;
    }
    public static void save(parentUsers itemToSave, DatabaseConnection database) {

        parentUsers existingItem = null;
        if (itemToSave.getUserID() != 0) existingItem = selectById(itemToSave.getUserID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO parentUsers (userID, username, password, linkedAccount) VALUES (?,?,?,?)");
                statement.setInt(0, itemToSave.getUserID());
                statement.setString(1, itemToSave.getUsername());
                statement.setString(2, itemToSave.getPassword());
                statement.setBoolean(3, itemToSave.isLinkedAccount());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE parentUsers SET userID, username, password, linkedAccount= ? WHERE childID = ?, userID = ?");
                statement.setString(1, itemToSave.getUsername());
                statement.setString(2, itemToSave.getPassword());
                statement.setBoolean(3, itemToSave.isLinkedAccount());

                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
    public static void deleteById(int id, DatabaseConnection database){
        PreparedStatement statement =  database.newStatement("DELETE FROM parentUsers WHERE id=?");
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
