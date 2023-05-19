package TimePlanner.Backend.Models;

import java.io.*;

public class Profile implements Serializable {
    private String nom;
    private String email;
    private String password;
    private String telephone;

    public Profile(String nom, String email, String password, String telephone) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;

        String username = nom.toLowerCase().replace(" ", "");
        serializeProfile("./src/TimePlanner/UsersInformation/" + username + ".ser");
    }

    public Profile() {
        this.nom = null;
        this.email = null;
        this.password = null;
        this.telephone = null;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    public String getNom() {
        return this.nom;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelephone() {
        return this.telephone;
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
     * 
     */

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     * 
     */

    public static void saveProfile(Profile profile) {
        try {
            FileOutputStream fileOut = new FileOutputStream(profile.getNom() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(profile);
            out.close();
            fileOut.close();
            System.out.println("Profile saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save profile: " + e.getMessage());
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
     */

    public static Profile loadProfile(String username) {
        try {
            FileInputStream fileIn = new FileInputStream(username + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Profile profile = (Profile) in.readObject();
            in.close();
            fileIn.close();
            return profile;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load profile: " + e.getMessage());
            return null;
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
     */

    private void serializeProfile(String filepath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized profile object created for " + this.nom + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
