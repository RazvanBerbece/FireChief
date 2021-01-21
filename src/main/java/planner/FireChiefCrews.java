package planner;

import java.util.*;

/** Class that holds all resources related to the crews and what they manage */
public class FireChiefCrews {

    private int membersCount; // number of available members
    private ArrayList<ArrayList<Integer>> crews; // crews : lists of numbers which represent members
    private int enginesCount; // number of engines
    private boolean crewToggle; // this will toggle on each wash (true = crew 0, false = crew 1)

    // Constructor
    public FireChiefCrews(int members, int engines) {
        this.membersCount = members;
        this.enginesCount = engines;
        this.crewToggle = true;

        // crew init
        this.crews = new ArrayList<ArrayList<Integer>>();
        this.crews.add(new ArrayList<Integer>()); // crew 0
        this.crews.add(new ArrayList<Integer>()); // crew 1

        // assign halves to crews[]
        for (int i = 1; i <= this.membersCount / 2; i++) { // crew 0 
            this.crews.get(0).add(i);
        }
        for (int i = (this.membersCount / 2) + 1; i <= this.membersCount; i++) { // crew 1
            this.crews.get(1).add(i);
        }
    }

    // Getters
    public int getMembersCount() {
        return this.membersCount;
    }
    public ArrayList<ArrayList<Integer>> getCrews() {
        return this.crews;
    }
    public int getEnginesCount() {
        return this.enginesCount;
    }
    public boolean getCrewToggle() {
        return this.crewToggle;
    }

    // Setters
    public void toggleCurrentCrew() {
        this.crewToggle = !this.crewToggle;
    }

}
