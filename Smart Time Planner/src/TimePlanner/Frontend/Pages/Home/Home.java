package TimePlanner.Frontend.Pages.Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

import TimePlanner.Frontend.Components.SideBar;

public class Home extends Application {

    private Stage primaryStage;
    private SideBar sidebarComponent;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        sidebarComponent = new SideBar(primaryStage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent root = loader.load();
            BorderPane homeLayout = (BorderPane) root;
            homeLayout.setLeft(sidebarComponent.getSidebar());

            Scene scene = new Scene(homeLayout, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home Page");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
