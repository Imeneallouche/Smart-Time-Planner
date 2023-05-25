package TimePlanner.Backend.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import TimePlanner.Backend.Models.EtatRealisation;
import TimePlanner.Backend.Models.Projet;
import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Services.ProjectManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HistoriqueController implements Initializable {

    @FXML
    private Label total;

    @FXML
    private Label unscheduled;

    @FXML
    private Label accomplished;

    @FXML
    private Label excellent;

    @FXML
    private Label verygood;

    @FXML
    private Label good;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Projet project = ProjectManager.getInstance().getProject();

        total.setText(project.getTaches().size() + "");

        int nbunscheduled = 0;
        int nbaccomplished = 0;
        for (Tache task : project.getTaches()) {
            if (!task.getScheduled()) {
                nbunscheduled++;
            }
            if (task.getEtatRealisation() == EtatRealisation.COMPLETED) {
                nbaccomplished++;
            }
        }
        unscheduled.setText(nbunscheduled + "");
        accomplished.setText(nbaccomplished + "");
        excellent.setText((nbaccomplished % 3) + "");
        verygood.setText((nbaccomplished % 2) + "");
        good.setText((nbaccomplished) + "");
    }

}
