package TimePlanner.Backend.Models;

import java.util.*;

public class Utilisateur {
    // Attributs
    private int identifiant;
    private String nom;
    private String prenom;
    private String mot_de_passe;
    private ArrayList<String> badge_gagnes;
    private int seuil_minimal;
    private int duree_maximale;
    private double rendement_journee;
    private Date date_debut_periode;
    private ArrayList<String> taches_journalieres;
    private ArrayList<String> projets_en_cours;
    // private ArrayList<Planning> plannings;
    private ArrayList<String> categorie_taches;
    private HashMap<String, Integer> duree_travaillee;
    private int nbGoodBadges;
    private int nbVeryGoodBadges;
    private int nbExcellentBadges;

    // Constructeur
    public Utilisateur(int identifiant, String nom, String prenom, String adresse_email, String mot_de_passe) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.badge_gagnes = new ArrayList<String>();
        this.seuil_minimal = 0;
        this.duree_maximale = 0;
        this.rendement_journee = 0;
        this.date_debut_periode = new Date();
        this.taches_journalieres = new ArrayList<String>();
        this.projets_en_cours = new ArrayList<String>();
        // this.plannings = new ArrayList<Planning>();
        this.categorie_taches = new ArrayList<String>();
        this.duree_travaillee = new HashMap<String, Integer>();
        this.nbGoodBadges = 0;
        this.nbVeryGoodBadges = 0;
        this.nbExcellentBadges = 0;
    }

    // Getters et Setters
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public ArrayList<String> getBadge_gagnes() {
        return badge_gagnes;
    }

    public void setBadge_gagnes(ArrayList<String> badge_gagnes) {
        this.badge_gagnes = badge_gagnes;
    }

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

    public Date getDate_debut_periode() {
        return date_debut_periode;
    }

    public void setDate_debut_periode(Date date_debut_periode) {
        this.date_debut_periode = date_debut_periode;
    }

    public ArrayList<String> getTaches_journalieres() {
        return taches_journalieres;
    }

    public void setTaches_journalieres(ArrayList<String> taches_journalieres) {
        this.taches_journalieres = taches_journalieres;
    }

    public ArrayList<String> getProjets_en_cours() {
        return projets_en_cours;
    }

    public void setProjets_en_cours(ArrayList<String> projets_en_cours) {
        this.projets_en_cours = projets_en_cours;
    }

    /*
     * public ArrayList<Planning> getPlannings() {
     * return plannings;
     * }
     * 
     * public void setPlannings(ArrayList<Planning> plannings) {
     * this.plannings = plannings;
     * }
     */

    public ArrayList<String> getCategorie_taches() {
        return categorie_taches;
    }

    public void setCategorie_taches(ArrayList<String> categorie_taches) {
        this.categorie_taches = categorie_taches;
    }

    public HashMap<String, Integer> getDuree_travaillee() {
        return duree_travaillee;
    }

    public void setDuree_travaillee(HashMap<String, Integer> duree_travaillee) {
        this.duree_travaillee = duree_travaillee;
    }

    public int getNbGoodBadges() {
        return nbGoodBadges;
    }

    public void setNbGoodBadges(int nbGoodBadges) {
        this.nbGoodBadges = nbGoodBadges;
    }

    public int getNbVeryGoodBadges() {
        return nbVeryGoodBadges;
    }

    public void setNbVeryGoodBadges(int nbVeryGoodBadges) {
        this.nbVeryGoodBadges = nbVeryGoodBadges;
    }

    public int getNbExcellentBadges() {
        return nbExcellentBadges;
    }

    public void setNbExcellentBadges(int nbExcellentBadges) {
        this.nbExcellentBadges = nbExcellentBadges;
    }

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

    public void afficherBadges() {
        System.out.println("Badges gagnés : ");
        for (String badge : badge_gagnes) {
            System.out.println("- " + badge);
        }
    }

}
