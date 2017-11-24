package Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class childGenrePlaysService {

        public static void selectAll(List<childGenrePlays> targetList, DatabaseConnection database){
                PreparedStatement statement = database.newStatement("SELECT childID, artistID, timesPlayed FROM childGenrePlays ORDER BY artistID");
                try{
                        if(statement != null){
                                ResultSet results = database.executeQuery(statement);

                                if(results != null){
                                        while(results.next()){
                                                targetList.add(new childGenrePlays(
                                                        results.getInt("childID"),
                                                        results.getInt("artistID"),
                                                        results.getInt("timesPlayed")
                                                ));
                                        }
                                }
                        }
                }catch (SQLException resultsException){
                        System.out.println("Database select all error: " + resultsException.getMessage());
                }
        }

        public static childGenrePlays selectById(int id, DatabaseConnection database){
                childGenrePlays result = null;

                PreparedStatement statement = database.newStatement("Select childID, artistID, timesPlayed FROM childGenrePlays WHERE id=?");

                try{
                        if(statement != null){
                                statement.setInt(1, id);
                                ResultSet results = database.executeQuery(statement);

                                if(results != null) {
                                        result = new childGenrePlays(
                                                results.getInt("childID"),
                                                results.getInt("artistID"),
                                                results.getInt("timesPlayed"));
                                }
                        }
                }catch (SQLException resultsException){
                        System.out.println("Database select by id error" + resultsException.getMessage());
                }
                return result;
        }
        public static void save(childGenrePlays itemToSave, DatabaseConnection database) {

                childGenrePlays existingItem = null;
                if (itemToSave.getChildID() != 0) existingItem = selectById(itemToSave.getChildID(), database);

                try {
                        if (existingItem == null) {
                                PreparedStatement statement = database.newStatement("INSERT INTO childGenrePlays (childID, artistID, timesPlayed) VALUES (?,?,?)");
                                statement.setInt(0, itemToSave.getChildID());
                                statement.setInt(1, itemToSave.getArtistID());
                                statement.setInt(2, itemToSave.getTimesPlayed());
                                database.executeUpdate(statement);
                        }
                        else {
                                PreparedStatement statement = database.newStatement("UPDATE childGenrePlays SET childID, artistID, timesPlayed = ? WHERE childID = ?, artistID = ?");
                                statement.setInt(2, itemToSave.getTimesPlayed());
                                database.executeUpdate(statement);
                        }
                } catch (SQLException resultsException) {
                        System.out.println("Database saving error: " + resultsException.getMessage());
                }
        }
        public static void deleteById(int id, DatabaseConnection database){
                PreparedStatement statement =  database.newStatement("DELETE FROM childGenrePlays WHERE id=?");
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
