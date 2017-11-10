package Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class artistsService {
    public static void selectAll(List<artists> targetList, DatabaseConnection database){
        PreparedStatement statement = database.newStatement("SELECT artistID, artistName, genre FROM artists ORDER BY artistName");

        try{
            if(statement != null){
                ResultSet results = database.executeQuery(statement);

                if(results != null){
                    while(results.next()){
                        targetList.add(new artists(results.getInt("artistID"), results.getString("artistName"), results.getString("genre")));
                    }
                }
            }
        }catch (SQLException resultsException){
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

    public static artists selectById(int id, DatabaseConnection database){
        artists result = null;

        PreparedStatement
    }
    public static void save(artists artists, DatabaseConnection database){}
    public static void deleteById(int id, DatabaseConnection database){}
}
