package TimePlanner.Backend.Models;

import java.time.LocalDate;
import java.util.Comparator;

class TaskComparator implements Comparator<Tache> {

    private static final int DURATION_WEIGHT = 1;
    private static final int DEADLINE_WEIGHT = 5;
    private static final int PRIORITY_WEIGHT = 10;

    @Override
    public int compare(Tache task1, Tache task2) {

        int score1 = calculateScore(task1);
        int score2 = calculateScore(task2);

        // Sort in ascending order based on score
        return Integer.compare(score1, score2);
    }

    private int calculateScore(Tache task) {

        int priorityScore = task.getPriorite() * PRIORITY_WEIGHT;
        int durationScore = task.getDureeTache() * DURATION_WEIGHT;
        int deadlineScore = task.getDateLimite().compareTo(LocalDate.now()) * DEADLINE_WEIGHT;

        // Calculate the overall score
        return priorityScore + durationScore + deadlineScore;
    }
}
