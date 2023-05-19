package TimePlanner.Backend.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projet {
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFinPrevues;
    private List<Tache> taches;

    public Projet(String nom, LocalDate dateDebut, LocalDate dateFinPrevues) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFinPrevues = dateFinPrevues;
        this.taches = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFinPrevues() {
        return dateFinPrevues;
    }

    public void setDateFinPrevues(LocalDate dateFinPrevues) {
        this.dateFinPrevues = dateFinPrevues;
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
