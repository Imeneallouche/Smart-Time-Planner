package TimePlanner.Backend.Models;

import java.time.LocalDate;

public class Periode {

    private LocalDate startDate;
    private LocalDate endDate;

    // private
    private ArrayList<ArrayList<Creneau>> CreneauxLibres = new ArrayList<>();

    public Periode(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;
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
}
