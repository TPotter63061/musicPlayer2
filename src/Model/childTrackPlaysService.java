package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class childTrackPlaysService {

    public static void selectAll(List<childTrackPlays> targetList, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("SELECT childID, trackID, timesPlayed FROM childTrackPlays ORDER BY trackID");
        try{
            if(statement != null){
                ResultSet results = database.executeQuery(statement);

                if(results != null){
                    while(results.next()){
                        targetList.add(new childTrackPlays(
                                results.getInt("childID"),
                                results.getInt("trackID"),
                                results.getInt("timesPlayed")
                        ));
                    }
                }
            }
        }catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

    public static childTrackPlays selectById(int id, DatabaseConnection database){
        childTrackPlays result = null;

        PreparedStatement statement = database.newStatement("Select childID, trackID, timesPlayed FROM childTrackPlays WHERE id=?");

        try{
            if(statement != null){
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if(results != null) {
                    result = new childTrackPlays(
                            results.getInt("childID"),
                            results.getInt("trackID"),
                            results.getInt("timesPlayed"));
                }
            }
        }catch (SQLException resultsException){
            System.out.println("Database select by id error" + resultsException.getMessage());
        }
        return result;
    }
    public static void save(childTrackPlays itemToSave, DatabaseConnection database) {

        childTrackPlays existingItem = null;
        if (itemToSave.getChildID() != 0) existingItem = selectById(itemToSave.getChildID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO childTrackPlays (childID, trackID, timesPlayed) VALUES (?,?,?)");
                statement.setInt(0, itemToSave.getChildID());
                statement.setInt(1, itemToSave.getTrackID());
                statement.setInt(2, itemToSave.getTimesPlayed());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE childTrackPlays SET childID, trackID, timesPlayed = ? WHERE childID = ?, trackID = ?");
                statement.setInt(2, itemToSave.getTimesPlayed());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
    public static void deleteById(int id, DatabaseConnection database){
        PreparedStatement statement =  database.newStatement("DELETE FROM childTrackPlays WHERE id=?");
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
