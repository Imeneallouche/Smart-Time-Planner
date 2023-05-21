package TimePlanner.Backend.Models;

import java.io.Serializable;
import java.time.LocalTime;

public class Creneau implements Serializable {
    private LocalTime debut;
    private LocalTime fin;

    public Creneau(LocalTime debut, LocalTime fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public LocalTime getDebut() {
        return debut;
    }

    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

}