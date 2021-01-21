package planner;

import java.util.*;

/** Class that holds all resources related to the crews and what they manage */
public class FireChiefCrews {

    private int membersCount; // number of available members
    private ArrayList<ArrayList<Integer>> crews; // crews : lists of numbers which represent members
    private int enginesCount; // number of engines
    private Stack<Integer> availableMembers;
    private int servicedEnginesThisMonth;

    // Constructor
    public FireChiefCrews(int members, int engines) {
        this.membersCount = members;
        this.enginesCount = engines;
        this.servicedEnginesThisMonth = 0;

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

        this.availableMembers = new Stack<Integer>();
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
    public int getServicedEnginesCheck() {
        return this.servicedEnginesThisMonth;
    }

    // Returns an ArrayList of available members
    public ArrayList<Integer> requestMembers(int requirement) {

        ArrayList<Integer> members = new ArrayList<Integer>(); // will be returned

        for (int i = 0; i < requirement; i++) {
            if (this.availableMembers.isEmpty()) {
                return null; // not enough members available
            } 
            else {
                members.add(this.availableMembers.pop());
            }
        }
        return members;

    }

    // Setters
    // pushes the members in the given crew in the resource stack
    public void initAvailableStack(ArrayList<Integer> crew) {
        for (Integer member : crew) {
            this.availableMembers.push(member);
        }
    }
    public void toggleServicedEnginesCheck() {
        switch(this.servicedEnginesThisMonth) {
            case 0:
                this.servicedEnginesThisMonth++;
                break;
            case 1:
                this.servicedEnginesThisMonth++;
                break;
            case 2:
                this.servicedEnginesThisMonth = 0;
                break;
        }
    }

}
