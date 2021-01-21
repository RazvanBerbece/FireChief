package planner;

import java.sql.Date;

/** Represents a task (using the spec) */
public class Task {
    
    private int id;
    private String description;
    private int assignedTo;
    private Date startDate;
    private int frequency; // 1 = daily, 2 = second day, 3 = weekly, 4 = monthly

    // Constructor
    public Task(int id, String description, int assignedTo, Date startDate, int frequency) {
        this.id = id;
        this.description = description;
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
    public int getAssignedTo() {
        return this.assignedTo;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    public int getFrequency() {
        return this.frequency;
    }

}
