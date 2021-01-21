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

import planner.Task;
import planner.FireChiefCrews;

/** Class which handles the schedule use case 
 * 
 * - Assumptions & data about tasks :
 *      0. Starting from day 1
 *      1. sweeping daily => returned every time (days 1, 2, 3...)
 *      2. empty the trash once every two days => returned on odd days (1, 3, 5, ...)
 *      3. wash the fire engine => weekly, but one week per month is specifically used for servicing and no washing
 *      4. servicing the fire engine => once a month
 *      5. crews work alternatively (even week => crew 0, else crew 1)
*/
public class Planner {

    FireChiefCrews resources;

    // Constructor
    public Planner() {
        this.resources = new FireChiefCrews(14, 2); // configured using the spec scenario
    }

    // The schedule for one day is represented as a hashmap -- { <Day> : { <Crew> : Crew ID; { n x (<Task> : <Crew.Members>) } } }
    public HashMap<String, HashMap<String, ArrayList<Integer>>> getScheduleOn(LocalDate dateTime) {

        HashMap<String, HashMap<String, ArrayList<Integer>>> schedule = new HashMap<String, HashMap<String, ArrayList<Integer>>>();
        
        // TODO


        return schedule;
    }

}