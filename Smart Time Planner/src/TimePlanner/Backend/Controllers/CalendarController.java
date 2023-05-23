package TimePlanner.Backend.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import TimePlanner.Backend.Models.Creneau;
import TimePlanner.Backend.Models.EtatRealisation;
import TimePlanner.Backend.Models.Projet;
import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Models.TacheDecomposable;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CalendarController {
    @FXML
    private Label actualdate;

    @FXML
    private Label nom1;
    @FXML
    private Label nom2;
    @FXML
    private Label nom3;
    @FXML
    private Label nom4;
    @FXML
    private Label nom5;
    @FXML
    private Label nom6;
    @FXML
    private Label nom7;
    @FXML
    private Label nom8;
    @FXML
    private Label nom9;

    @FXML
    private Label statut1;
    @FXML
    private Label statut2;
    @FXML
    private Label statut3;
    @FXML
    private Label statut4;
    @FXML
    private Label statut5;
    @FXML
    private Label statut6;
    @FXML
    private Label statut7;
    @FXML
    private Label statut8;
    @FXML
    private Label statut9;

    @FXML
    private Label creneau1;
    @FXML
    private Label creneau2;
    @FXML
    private Label creneau3;
    @FXML
    private Label creneau4;
    @FXML
    private Label creneau5;
    @FXML
    private Label creneau6;
    @FXML
    private Label creneau7;
    @FXML
    private Label creneau8;
    @FXML
    private Label creneau9;
    @FXML
    private Label creneau10;

    @FXML
    private DatePicker DateTaches;

    private int nbtaches = 0;

    // GET THE OBJECT CURRENT USER
    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * LISTENER TO THE DATE PICKER CHANGE IN ORDER TO DETECT THE TASKS AND THE
     * APPROPRIATE PROJECT
     */
    @FXML
    private void handleDateRealisation() {

        // BRING THE DATE FROM DATEPICKER
        LocalDate date = DateTaches.getValue();

        // ERASE ALL THE FRONT THAT WAS THERE BEFORE
        EraseAll();

        // SET THE RIGHT DATE IN THE LABEL OF DATE
        SetLabelDate(date);

        // BRING THE NUMBER OF TASKS TO 0 AGAIN
        nbtaches = 0;

        // LOOK FOR THE RIGHT PROJECT TO WHICH IT BELONGS
        for (Projet project : utilisateur.getProjets_en_cours()) {

            LocalDate start = project.getPeriode().getStartDate();
            LocalDate end = project.getPeriode().getEndDate();

            boolean isInPeriod = (date.isAfter(start) || date.isEqual(start)) &&
                    (date.isBefore(end) || date.isEqual(end));

            if (isInPeriod) {

                // Create a new instance of Tasks of that project
                ArrayList<Tache> Tasks = new ArrayList<>(project.getTaches());

                for (Tache task : Tasks) {
                    if (task instanceof TacheDecomposable) {
                        TacheDecomposable decomposableTask = (TacheDecomposable) task;

                        for (Tache subtask : decomposableTask.getSousTaches()) {
                            if (subtask.getJour().equals(date)) {
                                // INCREMENT THE NUMBER OF TASKS REPRESENTED IN THE FRONT
                                nbtaches++;
                                // SET THE TASK IN THE FRONT
                                setTaskFront(nbtaches, subtask.getTitre(), subtask.getGreneauTache(),
                                        subtask.getEtatRealisation());
                            }
                        }
                    } else {
                        if (task.getJour().equals(date)) {
                            // INCREMENT THE NUMBER OF TASKS REPRESENTED IN THE FRONT
                            nbtaches++;
                            // SET THE TASK IN THE FRONT
                            setTaskFront(nbtaches, task.getTitre(), task.getGreneauTache(), task.getEtatRealisation());
                        }
                    }
                }
            }
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
     * 
     * 
     * SET TEH LABEL OF THE RIGHT DATE IN THE LABEL OF DATE
     */
    public void EraseAll() {
        creneau1.setText(null);
        creneau2.setText(null);
        creneau3.setText(null);
        creneau4.setText(null);
        creneau5.setText(null);
        creneau6.setText(null);
        creneau7.setText(null);
        creneau8.setText(null);
        creneau9.setText(null);

        statut1.setText(null);
        statut2.setText(null);
        statut3.setText(null);
        statut4.setText(null);
        statut5.setText(null);
        statut6.setText(null);
        statut7.setText(null);
        statut8.setText(null);
        statut9.setText(null);

        nom1.setText(null);
        nom2.setText(null);
        nom3.setText(null);
        nom4.setText(null);
        nom5.setText(null);
        nom6.setText(null);
        nom7.setText(null);
        nom8.setText(null);
        nom9.setText(null);

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
     */
    public void SetLabelDate(LocalDate date) {

        // Format the date into the desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH);
        String formattedDate = date.format(outputFormatter);

        // SET THE CURRENTDATE TO THE FRONT
        actualdate.setText(formattedDate);
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
     */

    public void setTaskFront(int nb, String title, Creneau creneau, EtatRealisation etat) {
        switch (nb) {
            case 1:
                creneau1.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut1.setText(etat.toString());
                nom1.setText(title);
                break;

            case 2:
                creneau2.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut2.setText(etat.toString());
                nom2.setText(title);
                break;

            case 3:
                creneau3.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut3.setText(etat.toString());
                nom3.setText(title);
                break;

            case 4:
                creneau4.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut4.setText(etat.toString());
                nom4.setText(title);
                break;

            case 5:
                creneau5.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut5.setText(etat.toString());
                nom5.setText(title);
                break;

            case 6:
                creneau6.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut6.setText(etat.toString());
                nom6.setText(title);
                break;

            case 7:
                creneau7.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut7.setText(etat.toString());
                nom7.setText(title);
                break;

            case 8:
                creneau8.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut8.setText(etat.toString());
                nom8.setText(title);
                break;

            case 9:
                creneau9.setText(creneau.getDebut().toString() + " - " + creneau.getFin().toString());
                statut9.setText(etat.toString());
                nom9.setText(title);
                break;

            default:
                break;
        }
    }

    @FXML
    private void handleBack() {
        Stage currentStage = (Stage) DateTaches.getScene().getWindow();
        currentStage.close();
    }

}
