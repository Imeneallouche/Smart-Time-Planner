package TimePlanner.Backend.Models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private Profile profile;

    private ArrayList<Projet> projets_en_cours;

    private ArrayList<String> badge_gagnes;

    private int seuil_minimal;
    private int duree_maximale;

    private double rendement_journee;

    private ArrayList<String> taches_journalieres;

    private ArrayList<String> categorie_taches;
    private HashMap<String, Integer> duree_travaillee;

    private int nbGoodBadges;
    private int nbVeryGoodBadges;
    private int nbExcellentBadges;

    // Constructeur
    public Utilisateur(String nom, String email, String password, String telephone) {

        this.profile = new Profile(nom, email, password, telephone);

        this.badge_gagnes = new ArrayList<String>();
        this.seuil_minimal = 0;
        this.duree_maximale = 0;
        this.rendement_journee = 0;

        this.taches_journalieres = new ArrayList<String>();
        this.projets_en_cours = new ArrayList<Projet>();
        this.categorie_taches = new ArrayList<String>();
        this.duree_travaillee = new HashMap<String, Integer>();

        this.nbGoodBadges = 0;
        this.nbVeryGoodBadges = 0;
        this.nbExcellentBadges = 0;

        String username = nom.toLowerCase().replace(" ", "");
        serializeProfile("./src/TimePlanner/UsersInformation/" + username + ".ser");
    }

    public Utilisateur() {

    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * SERIALIZATION
     */
    private void serializeProfile(String filepath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized profile object created for " + this.profile.getNom() + ".");
        } catch (IOException e) {
            e.printStackTrace();
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
     * GETTERS AND SETTERS
     */

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /*
     * 
     * 
     * 
     */

    public ArrayList<String> getBadge_gagnes() {
        return badge_gagnes;
    }

    public void setBadge_gagnes(ArrayList<String> badge_gagnes) {
        this.badge_gagnes = badge_gagnes;
    }

    /*
    * 
    * 
    * 
    */

    public int getSeuil_minimal() {
        return seuil_minimal;
    }

    public void setSeuil_minimal(int seuil_minimal) {
        if (seuil_minimal >= 0) {
            this.seuil_minimal = seuil_minimal;
        } else {
            System.out.println("Error: Seuil minimal must be a positive integer.");
        }
    }

    /*
    * 
    * 
    * 
    */

    public int getDuree_maximale() {
        return duree_maximale;
    }

    public void setDuree_maximale(int duree_maximale) {
        if (duree_maximale >= 0) {
            this.duree_maximale = duree_maximale;
        } else {
            System.out.println("Error: Duree maximale must be a positive integer.");
        }
    }

    /*
    * 
    * 
    * 
    */

    public double getRendement_journee() {
        return rendement_journee;
    }

    public void setRendement_journee(double rendement_journee) {
        if (rendement_journee >= 0) {
            this.rendement_journee = rendement_journee;
        } else {
            System.out.println("Error: Rendement journee must be a positive number.");
        }
    }

    /*
    * 
    * 
    * 
    */

    public ArrayList<String> getTaches_journalieres() {
        return taches_journalieres;
    }

    public void setTaches_journalieres(ArrayList<String> taches_journalieres) {
        this.taches_journalieres = taches_journalieres;
    }

    /*
    * 
    * 
    * 
    */

    public ArrayList<Projet> getProjets_en_cours() {
        return projets_en_cours;
    }

    public void setProjets_en_cours(ArrayList<Projet> projets_en_cours) {
        this.projets_en_cours = projets_en_cours;
    }

    /*
    * 
    * 
    * 
    */

    public ArrayList<String> getCategorie_taches() {
        return categorie_taches;
    }

    public void setCategorie_taches(ArrayList<String> categorie_taches) {
        this.categorie_taches = categorie_taches;
    }

    /*
    * 
    * 
    * 
    */

    public HashMap<String, Integer> getDuree_travaillee() {
        return duree_travaillee;
    }

    public void setDuree_travaillee(HashMap<String, Integer> duree_travaillee) {
        this.duree_travaillee = duree_travaillee;
    }

    /*
    * 
    * 
    * 
    */

    public int getNbGoodBadges() {
        return nbGoodBadges;
    }

    public void setNbGoodBadges(int nbGoodBadges) {
        this.nbGoodBadges = nbGoodBadges;
    }

    /*
    * 
    * 
    * 
    */

    public int getNbVeryGoodBadges() {
        return nbVeryGoodBadges;
    }

    public void setNbVeryGoodBadges(int nbVeryGoodBadges) {
        this.nbVeryGoodBadges = nbVeryGoodBadges;
    }

    /*
    * 
    * 
    * 
    */

    public int getNbExcellentBadges() {
        return nbExcellentBadges;
    }

    public void setNbExcellentBadges(int nbExcellentBadges) {
        this.nbExcellentBadges = nbExcellentBadges;
    }

    /*
    * 
    * 
    * 
    */

    public void ajouterBadge(String badge) {
        badge_gagnes.add(badge);
        if (badge.equals("good")) {
            nbGoodBadges++;
        } else if (badge.equals("very good")) {
            nbVeryGoodBadges++;
        } else if (badge.equals("excellent")) {
            nbExcellentBadges++;
        }
    }

    public void ajouterProjet(Projet projet) {
        this.projets_en_cours.add(projet);
    }
}
