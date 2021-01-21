/** SPECS
 * 
 * - Resources :
 *      2 x crews (7 member each => 14 members) (each members represented as a number 1...14)
 *      2 x fire engines
 * 
 * - Constraints :
 *      Same fire engine is washed by alternating teams
 *      Fire engine MUST NOT be washed same week it is serviced
 */

package planner;

import java.util.*;
import java.time.LocalDate;

/** Class which handles the schedule use case 
 * 
 * - Assumptions & data about tasks :
 *      0. Starting from day 1
 *      1. sweeping daily => returned every time (days 1, 2, 3...)
 *      2. empty the trash once every two days => returned on odd days (1, 3, 5, ...)
 *      3. (FIRST WEEK OF MONTH) wash the fire engine => weekly, but one week per month is specifically used for servicing and no washing
 *      4. (LAST OR SECOND TO LAST WEEK OF MONTH) servicing the fire engine => once a month
 *      5. crews work alternatively (odd week => crew 0, else crew 1)
*/
public class Planner {

    private FireChiefCrews resources;

    // Constructor
    public Planner() {
        this.resources = new FireChiefCrews(14, 2); // configured using the spec scenario
    }

    // The schedule for one day is represented as a list of maps TASK : [MEMBERS]
    public ArrayList<Task> getScheduleOn(LocalDate dateTime) {
        
        int day = dateTime.getDayOfMonth();

        // Build tasks
        // Assumption (using task ids) : 1 -> needs two people, 2 -> needs two people,  3 -> needs two people, 4 -> needs 1 person
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        int evenWeek = 1;
        // get the crew that's currently working this week
        if ((day > 1 && day < 8) || (day > 14 && day < 22)) { // these are odd weeks
            evenWeek = 0;
        }
        switch(evenWeek) {
            case 1:
                this.resources.initAvailableStack(this.resources.getCrews().get(1));
                break;
            case 0:
                this.resources.initAvailableStack(this.resources.getCrews().get(0));
                break;
        }

        // List of all task descriptions
        ArrayList<String> descriptions = new ArrayList<String>();
        descriptions.add("Sweep the Floors");
        descriptions.add("Empty the Trash");
        descriptions.add("Wash Engine ");
        descriptions.add("Service Engine ");

        // initialise task list for given date
        tasks.add(new Task(1, descriptions.get(0), this.resources.requestMembers(2), dateTime, 1)); // sweeping 
        if (day % 2 != 0) { // emptying trash
            tasks.add(new Task(2, descriptions.get(1), this.resources.requestMembers(2), dateTime, 2));
        }
        // WASHING & SERVICING LOGIC HERE -- TODO

        return tasks;

    }

    // The schedule for multiple days (uses the getScheduleOn() method)
    public ArrayList<ArrayList<Task>> getScheduleOnInterval(LocalDate start, LocalDate end) {
        
        ArrayList<ArrayList<Task>> tasks = new ArrayList<ArrayList<Task>>();

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            tasks.add(getScheduleOn(date));
        }

        return tasks;

    }

}