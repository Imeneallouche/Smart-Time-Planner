package TimePlanner.Backend.Models;

import java.time.LocalDate;

public class Tache {
    private String titre;
    private String description;
    private int dureeTache;
    private int priorite;
    private LocalDate dateLimite;
    private EtatRealisation etatRealisation;
    private String categorie;
    // private Creneau creneauTache;

    // private List<Tache> sousTaches;
    public Tache(String titre, String description, int dureeTache, int priorite, LocalDate dateLimite,
            String categorie) {
        this.titre = titre;
        this.description = description;
        this.dureeTache = dureeTache;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.categorie = categorie;

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
