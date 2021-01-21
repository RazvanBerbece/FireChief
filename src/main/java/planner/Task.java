package planner;

import java.time.LocalDate;
import java.util.ArrayList;

/** Represents a task (using the spec) */
public class Task {
    
    private int id;
    private String description;
    private ArrayList<Integer> assignedTo;
    private LocalDate startDate;
    private int frequency; // 1 = daily, 2 = second day, 3 = weekly, 4 = monthly

    // Constructor
    public Task(int id, String description, ArrayList<Integer> assignedTo, LocalDate startDate, int frequency) {
        this.id = id;
        this.description = description;
        this.assignedTo = new ArrayList<Integer>();
        this.assignedTo = assignedTo;
        this.startDate = startDate;
        this.frequency = frequency;
    }

    // Getters
    public int getID() {
        return this.id;
    }
    public String getDescription() {
        return this.description;
    }
    public ArrayList<Integer> getAssignedTo() {
        return this.assignedTo;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public int getFrequency() {
        return this.frequency;
    }

}
