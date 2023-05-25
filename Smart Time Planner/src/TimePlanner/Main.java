package TimePlanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Frontend/Pages/Login/Login.fxml"));
        primaryStage.setTitle("Smart Time Planner");
        primaryStage.setScene(new Scene(root, 1028, 680));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}