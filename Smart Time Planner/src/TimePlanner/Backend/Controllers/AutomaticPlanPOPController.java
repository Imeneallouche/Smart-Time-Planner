package TimePlanner.Backend.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import TimePlanner.Backend.Models.EtatRealisation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutomaticPlanPOPController implements Initializable {

    @FXML
    private TextField NomTache;
    @FXML
    private TextField DescriptionTache;
    @FXML
    private TextField CategorieTache;
    @FXML
    private ComboBox<Integer> priority;
    @FXML
    private ComboBox<String> TypeTache;
    @FXML
    private DatePicker deadline;
    @FXML
    private TextField dureetache;
    @FXML
    private ComboBox<EtatRealisation> stateTache;

    /*
     * 
     */

    @FXML
    private Button Annuler;

    @FXML
    private Button Plannifier;

    @FXML
    private Label errorPlannifier;

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * SET THE VALUES OF THE COMBOBOXES
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // SET THE VALUES OF THE COMBOBOXES
        stateTache.setItems(FXCollections.observableArrayList(EtatRealisation.values()));
        priority.setItems(FXCollections.observableArrayList(1, 2, 3));
        TypeTache.setItems(FXCollections.observableArrayList("Tache simple", "Tache décomposable"));
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * EVENT LISTENER FOR THE ANNULER BUTTON (CLOSE THE WINDOW)
     */

    @FXML
    private void handleAnnuler() {

        Stage currentStage = (Stage) Plannifier.getScene().getWindow();
        currentStage.close();
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * EVENT LISTENER FOR THE PLANNIFIER LESSSSSS GOOOOOOOOOO
     */

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

}
