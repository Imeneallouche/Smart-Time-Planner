package TimePlanner.Backend.Services;

import TimePlanner.Backend.Models.Utilisateur;

public class DataManager {

    private static DataManager instance;
    private Utilisateur utilisateur;

    private DataManager() {
        // Private constructor to prevent instantiation
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
