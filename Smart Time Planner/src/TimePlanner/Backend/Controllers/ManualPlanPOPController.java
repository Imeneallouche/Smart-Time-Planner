package TimePlanner.Backend.Controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;

import TimePlanner.Backend.Models.Creneau;
import TimePlanner.Backend.Models.EtatRealisation;
import TimePlanner.Backend.Models.Periode;
import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManualPlanPOPController implements Initializable {

    @FXML
    private TextField NomTache;

    @FXML
    private TextField DescriptionTache;

    @FXML
    private TextField CategorieTache;

    @FXML
    private ComboBox<EtatRealisation> stateTache;

    @FXML
    private ComboBox<String> CreneauTache;

    @FXML
    private DatePicker DateTache;

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
     */

    // GET THE OBJECT CURRENT USER
    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

    // GET THE INDEX OF THE PROJECT
    int index = 0;

    // GET LA PERIODE DU PROJET
    Periode ProjectPeriod = utilisateur.getProjets_en_cours().get(index).getPeriode();

    // GET DATE OF START OF THE PROJECT
    LocalDate startDate = ProjectPeriod.getStartDate();

    // GET THE DATE OF THE END OF THE PERIOD
    LocalDate endDate = ProjectPeriod.getEndDate();

    // GET LES CRENEAUX LIBRES OF THE PERIOD
    ArrayList<ArrayList<Creneau>> creneauxlibres = ProjectPeriod.getCreneaux();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stateTache.setItems(FXCollections.observableArrayList(EtatRealisation.values()));
    }

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
     * 
     * 
     * 
     * 
     * EVENT LISTENER FOR PLANNIFIER TACHE
     */
    @FXML
    private void handlePlannifier() {

        String nom = NomTache.getText();
        String description = DescriptionTache.getText();
        String categorie = CategorieTache.getText();
        LocalDate jour = DateTache.getValue();
        EtatRealisation state = stateTache.getValue();

        String creneau = CreneauTache.getValue();
        LocalTime debut = LocalTime.parse(creneau.split(" - ")[0].trim());
        LocalTime fin = LocalTime.parse(creneau.split(" - ")[1].trim());
        Creneau realcreneau = new Creneau(debut, fin);

        System.out.println("creneau: " + debut + " - " + fin);

        boolean a = nom != null && nom != "";
        boolean b = categorie != null && nom != "";
        boolean c = jour != null;
        boolean d = creneau != null;
        boolean e = state != null;
        boolean f = description != null && description != "";

        if (a && b && c && d && e && f) {

            // REMOVE THE ERROR
            errorPlannifier.setText(null);

            // CREATE THE TASK
            Tache task = new Tache(nom, description, categorie, jour, realcreneau, state);

            // ADD THE NEW TASK TO THE CALENDAR
            utilisateur.getProjets_en_cours().get(index).ajouterTache(task);

            // UPDATE THE DATAMANAGER
            DataManager.getInstance().setUtilisateur(utilisateur);

            // DIMINISH THE PAGE
            Stage currentStage = (Stage) Plannifier.getScene().getWindow();
            currentStage.close();

            // SERIALIZE THE NEW OBJECT
            serializeUser(utilisateur);
        }

        else {
            // SHOW THE ERROR OF MISSING VALUES
            errorPlannifier.setText("Missing values");
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
     * LISTENER TO THE DATE PICKER CHANGE IN ORDER TO METTRE A JOUR LES CRENEAUX
     * LIBRES
     * ACCORDING TO THE DATE SELECTED
     */
    @FXML
    private void handleDateRealisation() {
        LocalDate date = DateTache.getValue();
        LocalDate current = startDate;

        // FIRST DELETE THE OLD ITEMS
        CreneauTache.getItems().clear();

        for (int i = 0; i < creneauxlibres.size(); i++) {
            if (current.equals(date)) {

                // WE LOAD LES CRENEAUX OF THIS DATE
                ArrayList<String> p = new ArrayList<>();

                // CREATE LES CRNREAUX STRINGS ARRAY : p
                for (Creneau creneau : creneauxlibres.get(i)) {
                    String creneauString = creneau.getDebut().toString() + " - " + creneau.getFin().toString();
                    p.add(creneauString);
                }

                // LAST ADD THE NEW ITEMS
                CreneauTache.setItems(FXCollections.observableArrayList(p));

                // BREAK IF THE DATE HAS BEEN FOUND
                break;
            }

            // ELSE WE INCREMENT THE CURRENT DATE
            current = current.plus(1, ChronoUnit.DAYS);

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
     * SERIALIZE TH ENEW USER
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
