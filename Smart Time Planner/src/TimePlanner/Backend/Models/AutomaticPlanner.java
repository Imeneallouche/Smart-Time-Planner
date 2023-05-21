package TimePlanner.Backend.Models;

import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class AutomaticPlanner implements Serializable {

    private List<Tache> taches;
    private List<Projet> projets;
    private List<Utilisateur> utilisateurs;
    private Map<String, Integer> statistiques;

    public AutomaticPlanner() {
        this.taches = new ArrayList<Tache>();
        this.projets = new ArrayList<Projet>();
        this.utilisateurs = new ArrayList<Utilisateur>();
        this.statistiques = new HashMap<String, Integer>();
    }

    public void ajouterTache(Tache tache) {
        taches.add(tache);
    }

    public void ajouterProjet(Projet projet) {
        projets.add(projet);
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public void supprimerTache(Tache tache) {
        taches.remove(tache);
    }

    public void supprimerProjet(Projet projet) {
        projets.remove(projet);
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {
        utilisateurs.remove(utilisateur);
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void ajouterStatistique(String type, int valeur) {
        if (statistiques.containsKey(type)) {
            statistiques.put(type, statistiques.get(type) + valeur);
        } else {
            statistiques.put(type, valeur);
        }
    }

    public int getStatistique(String type) {
        if (statistiques.containsKey(type)) {
            return statistiques.get(type);
        } else {
            return 0;
        }
    }
}
