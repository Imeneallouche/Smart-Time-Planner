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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    /*
     * private static void createSerializedProfile() {
     * // Create a sample profile object
     * Profile profile = new Profile("Imene", "imene@example.com", "password123",
     * "1234567890");
     * }
     */
    public static void main(String[] args) {
        // createSerializedProfile();
        launch(args);
    }
}