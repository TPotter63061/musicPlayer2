import javafx.fxml.FXMLLoader;
        import javafx.stage.Stage;
        import javafx.scene.*;
        import javafx.application.Application;

public class loginLaunch extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginscreen.fxml"));
        Scene scene = new Scene(root, 600, 332);
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> System.exit(0));

        stage.show();
    }
    public static void main(String[] args){launch(args);}
}

