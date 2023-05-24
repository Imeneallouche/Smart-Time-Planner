package TimePlanner.Backend.Services;

import java.util.ArrayList;
import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Models.TacheDecomposable;

public class TaskManager {

    private static TaskManager instance;
    private ArrayList<Tache> taches;

    private TaskManager() {
        // Private constructor to prevent instantiation
        taches = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public ArrayList<Tache> getTasks() {
        return taches;
    }

    public void setTasks(ArrayList<Tache> tasks) {
        this.taches = tasks;
    }

    public void addTask(Tache task) {
        // Check if the task is an instance of TacheDecomposable
        if (task instanceof TacheDecomposable) {
            TacheDecomposable complexTask = (TacheDecomposable) task;
            this.taches.add(complexTask);
        } else {
            this.taches.add(task);
        }

    }

}
