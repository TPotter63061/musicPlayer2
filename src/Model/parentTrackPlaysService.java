package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class parentTrackPlaysService {

    public static void selectAll(List<parentTrackPlays> targetList, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("SELECT userID, trackID, timesPlayed FROM parentTrackPlays ORDER BY trackID");
        try{
            if(statement != null){
                ResultSet results = database.executeQuery(statement);

                if(results != null){
                    while(results.next()){
                        targetList.add(new parentTrackPlays(
                                results.getInt("userID"),
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

    public static parentTrackPlays selectById(int id, DatabaseConnection database){
        parentTrackPlays result = null;

        PreparedStatement statement = database.newStatement("Select userID, trackID, timesPlayed FROM parentTrackPlays WHERE id=?");

        try{
            if(statement != null){
                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if(results != null) {
                    result = new parentTrackPlays(
                            results.getInt("userID"),
                            results.getInt("trackID"),
                            results.getInt("timesPlayed"));
                }
            }
        }catch (SQLException resultsException){
            System.out.println("Database select by id error" + resultsException.getMessage());
        }
        return result;
    }
    public static void save(parentTrackPlays itemToSave, DatabaseConnection database) {

        parentTrackPlays existingItem = null;
        if (itemToSave.getUserID() != 0) existingItem = selectById(itemToSave.getUserID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO parentTrackPlays (userID, trackID, timesPlayed) VALUES (?,?,?)");
                statement.setInt(0, itemToSave.getUserID());
                statement.setInt(1, itemToSave.getTrackID());
                statement.setInt(2, itemToSave.getTimesPlayed());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE parentTrackPlays SET userID, trackID, timesPlayed = ? WHERE userID = ?, trackID = ?");
                statement.setInt(2, itemToSave.getTimesPlayed());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
    public static void deleteById(int id, DatabaseConnection database){
        PreparedStatement statement =  database.newStatement("DELETE FROM parentTrackPlays WHERE id=?");
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
