package TimePlanner.Backend.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tache {
    private String titre;
    private String description;
    private int dureeTache;
    private int priorite;
    private LocalDate dateLimite;
    private EtatRealisation etatRealisation;
    private String categorie;

    private Creneau creneauTache;

    // private List<Tache> sousTaches;
    public Tache(String titre, String description, int priorite, LocalDate dateLimite) {
        this.titre = titre;
        this.description = description;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.etatRealisation = EtatRealisation.NOT_REALIZED;
        // this.sousTaches = new ArrayList<>();
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public EtatRealisation getEtatRealisation() {
        return etatRealisation;
    }

    public void setEtatRealisation(EtatRealisation etatRealisation) {
        this.etatRealisation = etatRealisation;
    }

    public void modifierDureeEtDateLimite(int dureeSupplementaire, LocalDate nouvelleDateLimite) {
        if (this.etatRealisation == EtatRealisation.IN_PROGRESS || this.etatRealisation == EtatRealisation.DELAYED) {
            this.dateLimite = nouvelleDateLimite;
        } else {
            throw new IllegalStateException(
                    "La tâche n'est pas en cours ou en retard, impossible de modifier la date limite");
        }

        if (dureeSupplementaire > 0) {
            if (this.etatRealisation == EtatRealisation.NOT_REALIZED) {
                this.etatRealisation = EtatRealisation.IN_PROGRESS;
            }
            this.dateLimite = this.dateLimite.plusDays(dureeSupplementaire);
        }
    }

    public void changerEtatRealisation(EtatRealisation nouvelEtat) {
        this.etatRealisation = nouvelEtat;
    }
}
