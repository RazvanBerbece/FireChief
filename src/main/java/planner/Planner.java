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
import java.time.format.DateTimeFormatter;

/** Class which handles the schedule use case 
 * 
 * - Assumptions & data about tasks :
 *      0. Starting from day 1
 *      1. sweeping daily => returned every time (days 1, 2, 3...)
 *      2. empty the trash once every two days => returned on odd days (1, 3, 5, ...)
 *      3. wash the fire engine => weekly, but one week per month is specifically used for servicing and no washing (?)
 *      4. (LAST WEEK OF MONTH) servicing the fire engine => once a month
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
        
        // Date and time helper objects
        int day = dateTime.getDayOfMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");

        // Build tasks
        // Assumption (using task ids) : 1 -> needs two people, 2 -> needs two people,  3 -> needs two people, 4 -> needs 1 person
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        int evenWeek = 1;
        // get the crew that's currently working in the given week
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
        tasks.add(new Task(1, descriptions.get(0), this.resources.requestMembers(2), dateTime.format(formatter), 1)); // sweeping 
        if (day % 2 != 0) { // emptying trash
            tasks.add(new Task(2, descriptions.get(1), this.resources.requestMembers(2), dateTime.format(formatter), 2));
        }

        // WASHING & SERVICING LOGIC HERE
        // Assumption : last week of the month is used for servicing the engines
        // Potential Enhancement : (1 x engine services) + (1 x engine washed) at end of month
        if (day > 23 && this.resources.getServicedEnginesCheck() < 2) {
            if (dateTime.getDayOfWeek().toString().equals("MONDAY")) { // service engine 1
                tasks.add(new Task(4, descriptions.get(3) + "1", this.resources.requestMembers(1), dateTime.format(formatter), 4));
                this.resources.toggleServicedEnginesCheck();
            }
            else if (dateTime.getDayOfWeek().toString().equals("SUNDAY")) { // service engine 2
                tasks.add(new Task(4, descriptions.get(3) + "2", this.resources.requestMembers(1), dateTime.format(formatter), 4));
                this.resources.toggleServicedEnginesCheck();
            }
        }
        // Assumptions : engines are washed at the beggining (1) and ending (2) of each week 
        // => no specific engine is washed twice by the same crew
        else { // wash if not serviced 
            if (dateTime.getDayOfWeek().toString().equals("MONDAY")) { // washing engine 1
                tasks.add(new Task(3, descriptions.get(2) + "1", this.resources.requestMembers(2), dateTime.format(formatter), 3));
            }
            else if (dateTime.getDayOfWeek().toString().equals("SUNDAY")) { // washing engine 2
                tasks.add(new Task(3, descriptions.get(2) + "2", this.resources.requestMembers(2), dateTime.format(formatter), 3));
            }
        }

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