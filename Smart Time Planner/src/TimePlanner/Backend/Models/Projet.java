package TimePlanner.Backend.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Projet implements Serializable {
    private String nom;
    private Periode period;
    private ArrayList<Tache> taches;

    public Projet(String nom, LocalDate dateDebut, LocalDate dateFinPrevues) {
        this.period = new Periode(dateDebut, dateFinPrevues);
        this.nom = nom;
        this.taches = new ArrayList<Tache>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Periode getPeriode() {
        return period;
    }

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<Tache> taches) {
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
