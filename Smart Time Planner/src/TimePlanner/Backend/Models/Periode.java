package TimePlanner.Backend.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Periode implements Serializable {

    private LocalDate startDate;
    private LocalDate endDate;

    // private
    private ArrayList<ArrayList<Creneau>> CreneauxLibres;

    public Periode(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;

        this.CreneauxLibres = new ArrayList<>();
    }
    /*
     * 
     * 
     * 
     * 
     * GETTERS
     */

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ArrayList<ArrayList<Creneau>> getCreneaux() {
        return CreneauxLibres;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * SETTERS
     */

    public void setStartDate(LocalDate start) {
        this.startDate = start;
    }

    public void setEndDate(LocalDate end) {
        this.endDate = end;
    }

    public void setCreneaux(ArrayList<ArrayList<Creneau>> creneaux) {
        this.CreneauxLibres = creneaux;
    }
}
