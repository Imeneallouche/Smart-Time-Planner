package TimePlanner.Backend.Models;

import java.io.Serializable;
import java.time.LocalDate;

public class Tache implements Serializable {
    // for the manual planification
    private long serialVersionUID = 622491746546313510L;

    private String titre;
    private String description;
    private String categorie;
    private LocalDate Jour;
    private Creneau creneauTache;
    private EtatRealisation etatRealisation;
    private boolean scheduled;

    // for the automatic planification
    private LocalDate dateLimite;
    private int dureeTache;
    private int priorite;

    // CONSTRUCTOR FOR THE MANUAL PLANIFICATION
    public Tache(String titre, String description, String categorie, LocalDate jour, Creneau creneau,
            EtatRealisation etat, boolean scheduled) {
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.Jour = jour;
        this.creneauTache = creneau;
        this.etatRealisation = etat;
        this.scheduled = scheduled;
        // this.etatRealisation = EtatRealisation.NOT_REALIZED;
    }

    // CONSTRUCTOR FOR THE AUTOMATIC PLANIFICATION 5TO BE REVIEWED)
    public Tache(String titre, String description, int dureeTache, int priorite, LocalDate dateLimite,
            String categorie, boolean schedule) {
        this.titre = titre;
        this.description = description;
        this.dureeTache = dureeTache;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.categorie = categorie;
        this.scheduled = schedule;

        this.etatRealisation = EtatRealisation.NOT_REALIZED;
        // this.sousTaches = new ArrayList<>();
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * GETTERS
     */

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getDureeTache() {
        return dureeTache;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public EtatRealisation getEtatRealisation() {
        return etatRealisation;
    }

    public int getPriorite() {
        return priorite;
    }

    public String getCategorie() {
        return categorie;
    }

    public Creneau getGreneauTache() {
        return this.creneauTache;
    }

    public LocalDate getJour() {
        return this.Jour;
    }

    public boolean getScheduled() {
        return this.scheduled;
    }
    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * SETTERS
     */

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDureeTache(int dureeTache) {
        this.dureeTache = dureeTache;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setEtatRealisation(EtatRealisation etatRealisation) {
        this.etatRealisation = etatRealisation;
    }

    public void setCreneauTache(Creneau creneau) {
        this.creneauTache = creneau;
    }

    public void setJour(LocalDate jour) {
        this.Jour = jour;
    }

    public void setScheduled(boolean schedule) {
        this.scheduled = schedule;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * OTHER FUNCTIONS
     */

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
