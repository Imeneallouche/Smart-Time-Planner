package TimePlanner.Backend.Services;

import TimePlanner.Backend.Models.Projet;

public class ProjectManager {

    private static ProjectManager instance;
    private Projet project;

    private ProjectManager() {
        // Private constructor to prevent instantiation
    }

    public static ProjectManager getInstance() {
        if (instance == null) {
            instance = new ProjectManager();
        }
        return instance;
    }

    public Projet getProject() {
        return this.project;
    }

    public void setProject(Projet proj) {
        this.project = proj;
    }

}
