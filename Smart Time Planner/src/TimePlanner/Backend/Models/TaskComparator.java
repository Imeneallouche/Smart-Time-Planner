package TimePlanner.Backend.Models;

import java.time.LocalDate;
import java.util.Comparator;

public class TaskComparator implements Comparator<Tache> {

    private static final int DURATION_WEIGHT = 1;
    private static final int PRIORITY_WEIGHT = 20;
    private static final int DEADLINE_WEIGHT = 30;

    @Override
    public int compare(Tache task1, Tache task2) {

        double score1 = calculateScore(task1);
        double score2 = calculateScore(task2);

        // Sort in ascending order based on score
        return Double.compare(score1, score2);
    }

    private double calculateScore(Tache task) {

        int priorityScore = task.getPriorite() * PRIORITY_WEIGHT;
        double durationScore = task.getDureeTache() * DURATION_WEIGHT / 30;
        int deadlineScore = task.getDateLimite().compareTo(LocalDate.now()) * DEADLINE_WEIGHT;

        // Calculate the overall score
        return priorityScore + durationScore + deadlineScore;
    }
}
