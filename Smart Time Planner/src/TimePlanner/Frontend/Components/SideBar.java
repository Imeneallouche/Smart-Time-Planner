package TimePlanner.Frontend.Components;

//import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class SideBar {

    private Stage primaryStage;
    private VBox sidebar;

    public SideBar(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sidebar = createSidebar();
    }

    public VBox getSidebar() {
        return sidebar;
    }

    private VBox createSidebar() {
        Button homeButton = createButton("Home", "/fxml/home.fxml");
        Button statisticsButton = createButton("Statistics", "/fxml/statistics.fxml");
        Button usersButton = createButton("Users", "/fxml/users.fxml");
        Button settingsButton = createButton("Settings", "/fxml/settings.fxml");

        VBox sidebar = new VBox(homeButton, statisticsButton, usersButton, settingsButton);
        sidebar.setPadding(new Insets(10));
        sidebar.setSpacing(10);

        return sidebar;
    }

    private Button createButton(String text, String page) {
        Button button = new Button(text);
        button.setOnAction(e -> navigateToPage(page));
        return button;
    }

    private void navigateToPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
