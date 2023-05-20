package TimePlanner.Backend.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projet {
    private String nom;
    private Periode period;
    private List<Tache> taches;

    public Projet(String nom, LocalDate dateDebut, LocalDate dateFinPrevues) {
        this.period = new Periode(dateDebut, dateFinPrevues);
        this.nom = nom;
        this.taches = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Periode getDateDebut() {
        return period;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public void ajouterTache(Tache tache) {
        this.taches.add(tache);
    }

    public void retirerTache(Tache tache) {
        this.taches.remove(tache);
    }

    public boolean estTermine() {
        for (Tache tache : taches) {
            if (tache.getEtatRealisation() != EtatRealisation.COMPLETED) {
                return false;
            }
        }
        return true;
    }
}
