package TimePlanner.Backend.Models;

import java.time.LocalDateTime;

public class Creneau {
    private LocalDateTime debut;
    private LocalDateTime fin;

    public Creneau(LocalDateTime debut, LocalDateTime fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

}