package TimePlanner.Backend.Controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import TimePlanner.Backend.Models.Creneau;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

public class CreneauxLibresController implements Initializable {

    @FXML
    private ComboBox<String> StarthourComboBox;

    @FXML
    private ComboBox<String> StartMinuteComboBox;

    @FXML
    private ComboBox<String> EndHourComboBox;

    @FXML
    private ComboBox<String> EndMinuteComboBox;

    @FXML
    private Label CurrentPeriodDay;

    /*
     * 
     */

    @FXML
    private Label creneau1first;
    @FXML
    private Label creneau2first;
    @FXML
    private Label creneau3first;
    @FXML
    private Label creneau4first;
    @FXML
    private Label creneau5first;
    @FXML
    private Label creneau6first;
    @FXML
    private Label creneau7first;
    @FXML
    private Label creneau8first;
    @FXML
    private Label creneau9first;

    /* 
     * 
     */

    @FXML
    private Label creneau1second;
    @FXML
    private Label creneau2second;
    @FXML
    private Label creneau3second;
    @FXML
    private Label creneau4second;
    @FXML
    private Label creneau5second;
    @FXML
    private Label creneau6second;
    @FXML
    private Label creneau7second;
    @FXML
    private Label creneau8second;
    @FXML
    private Label creneau9second;

    /*
     * 
     */

    @FXML
    private Button AjouterCreneauButton;

    @FXML
    private Button nexButton;

    @FXML
    private Label ErrorMessageAjouter;

    /*
     * 
     */

    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

    LocalDate firstday = utilisateur.getProjets_en_cours().get(0).getPeriode().getStartDate();
    LocalDate endday = utilisateur.getProjets_en_cours().get(0).getPeriode().getEndDate();
    LocalDate currentDay = firstday;

    ArrayList<ArrayList<Creneau>> creneaux = new ArrayList<>();

    ArrayList<Creneau> creneaujour = new ArrayList<>();
    int nbcreneaux = 0;

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeHourComboBox();
        initializeMinuteComboBox();
        CurrentPeriodDay.setText(currentDay.toString());
    }

    private void initializeHourComboBox() {
        ObservableList<String> hours = FXCollections.observableArrayList();
        for (int i = 0; i <= 23; i++) {
            String hour = String.format("%02d", i); // Format the hour to always have two digits
            hours.add(hour);
        }
        StarthourComboBox.setItems(hours);
        EndHourComboBox.setItems(hours);
    }

    private void initializeMinuteComboBox() {

        ObservableList<String> minutes = FXCollections.observableArrayList();
        for (int i = 0; i <= 59; i++) {
            String minute = String.format("%02d", i); // Format the hour to always have two digits
            minutes.add(minute);
        }
        StartMinuteComboBox.setItems(minutes);
        EndMinuteComboBox.setItems(minutes);

    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * EVENT LISTENER FOR THE BUTTON AJOUTER CRENEAU
     */

    @FXML
    private void handleAjouterCreneauController() {
        String FirstHour = StarthourComboBox.getValue();
        String LastHour = EndHourComboBox.getValue();

        String FirstMinute = StartMinuteComboBox.getValue();
        String LastMinute = EndMinuteComboBox.getValue();

        boolean conditiona = FirstHour != null && FirstHour != "";
        boolean conditionb = LastHour != null && LastHour != "";
        boolean conditionc = FirstMinute != null && FirstMinute != "";
        boolean conditiond = LastMinute != null && LastMinute != "";

        if (conditiona && conditionb && conditionc && conditiond) {
            // ADD LE CRENEAU TO THE FRONT AND BACK
            // FIRST : THE FRONTEND
            nbcreneaux++;

            switch (nbcreneaux) {
                case 1:
                    creneau1first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau1second.setText(LastHour + "hr" + LastMinute + "min");
                    break;

                case 2:
                    creneau2first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau2second.setText(LastHour + "hr" + LastMinute + "min");
                    break;

                case 3:
                    creneau3first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau3second.setText(LastHour + "hr" + LastMinute + "min");
                    break;
                case 4:
                    creneau4first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau4second.setText(LastHour + "hr" + LastMinute + "min");
                    break;
                case 5:
                    creneau5first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau5second.setText(LastHour + "hr" + LastMinute + "min");
                    break;
                case 6:
                    creneau6first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau6second.setText(LastHour + "hr" + LastMinute + "min");
                    break;
                case 7:
                    creneau7first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau7second.setText(LastHour + "hr" + LastMinute + "min");
                    break;
                case 8:
                    creneau8first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau8second.setText(LastHour + "hr" + LastMinute + "min");
                    break;
                case 9:
                    creneau9first.setText(FirstHour + "hr" + FirstMinute + "min");
                    creneau9second.setText(LastHour + "hr" + LastMinute + "min");
                    break;
                default:
                    break;
            }

            // AFTER THAT WE CLEAR THE VALUES OF THE COMBOBOXES
            StarthourComboBox.setValue(null);
            StartMinuteComboBox.setValue(null);
            EndHourComboBox.setValue(null);
            EndMinuteComboBox.setValue(null);

            // NOW TO THE BACKEND
            LocalTime debut = LocalTime.of(Integer.parseInt(FirstHour), Integer.parseInt(FirstMinute));
            LocalTime fin = LocalTime.of(Integer.parseInt(FirstHour), Integer.parseInt(FirstMinute));

            Creneau creneau = new Creneau(debut, fin);

            creneaujour.add(creneau);
        }

        else

        {
            ErrorMessageAjouter.setText("Missing values");
        }
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

    @FXML
    private void handleNextButton() {

        // PUTH LES CRENEAU LIBRES OF THE DAY
        creneaux.add(creneaujour);

        // EMPTY THE ARRAYLIST OF LES CRENEAU DU JOURS
        creneaujour.clear();

        // AS LONG AS WE DDIN'T COME TO THE END OF OUR PERIOD
        if (!currentDay.isAfter(endday)) {
            // Move to the next day
            currentDay = currentDay.plus(1, ChronoUnit.DAYS);

            // CHANGE THE FRONT : THE CURRENT DATE WILL BE INCREMENTED
            CurrentPeriodDay.setText(currentDay.toString());

            // REMOVE ALL LES CRENEAUX FROM THE FRONT
            creneau1first.setText(null);
            creneau2first.setText(null);
            creneau3first.setText(null);
            creneau4first.setText(null);
            creneau5first.setText(null);
            creneau6first.setText(null);
            creneau7first.setText(null);
            creneau8first.setText(null);
            creneau9first.setText(null);

            creneau1second.setText(null);
            creneau2second.setText(null);
            creneau3second.setText(null);
            creneau4second.setText(null);
            creneau5second.setText(null);
            creneau6second.setText(null);
            creneau7second.setText(null);
            creneau8second.setText(null);
            creneau9second.setText(null);

        }

        // ELSE IF WE CAME TO THE END OF THE PERIOD
        else {

            // 1- SAVE ALL LES CRENEAUX IN THE USER
            utilisateur.getProjets_en_cours().get(0).getPeriode().setCreneaux(creneaux);

            // 2- SAVE IT IN THE DATAMANAGER AS WELL
            DataManager.getInstance().setUtilisateur(utilisateur);

            // 2- SERIALIZE THE NEW OBJECT UTILISATEUR
            serializeUser(utilisateur);

            // 3- REDIRECT THE USER TO THE HOME PAGE
            String nextPage = "../../Frontend/Pages/Profile/Profile.fxml";
            try {

                Parent next = FXMLLoader.load(getClass().getResource(nextPage));

                // Get the current scene
                Scene currentScene = nexButton.getScene();

                // Set the root of the current scene to the Step2 root
                currentScene.setRoot(next);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 
     * 
     * 
     * 
     * 
     */

    private void serializeUser(Utilisateur utilisateur) {

        String username = utilisateur.getProfile().getNom().toLowerCase().replaceAll(" ", "");

        try {
            FileOutputStream fileOut = new FileOutputStream("./src/TimePlanner/UsersInformation/" + username + ".ser",
                    false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(utilisateur);
            out.close();
            fileOut.close();
            System.out.println("Profile serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}