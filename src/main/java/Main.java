import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import planner.Planner;
import planner.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@EnableAutoConfiguration
public class Main {

    // tests whether the server is listening
    @RequestMapping("/test")
    public String TestConn() {
        return "Server listening on port :8080 ...";
    }

    // date will be sent as a String of format dd-mm-yyyy from the client and will be parsed here 
    @RequestMapping("/schedule")
    public HashMap<String, ArrayList<Task>> GetScheduleForDay(@RequestParam(name = "date") String date) {

        // query param parsing as LocalDate object ( HAS TO BE SANITISED ON CLIENT )
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTime = LocalDate.parse(date, formatter);

        // planner logic
        Planner planner = new Planner();

        // response logic
        ArrayList<Task> scheduled = planner.getScheduleOn(dateTime);
        HashMap<String, ArrayList<Task>> response = new HashMap<String, ArrayList<Task>>();
        response.put("Schedule", scheduled);

        return response;
    }

    // same as above, but handles intervals of time
    @RequestMapping("/schedule/multiple")
    public HashMap<String, ArrayList<ArrayList<Task>>> GetScheduleForInterval(@RequestParam(name = "dateStart") String dateStart, @RequestParam(name = "dateEnd") String dateEnd) {
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTimeStart = LocalDate.parse(dateStart, formatter);
        LocalDate dateTimeEnd = LocalDate.parse(dateEnd, formatter);
    
        Planner planner = new Planner();
        ArrayList<ArrayList<Task>> scheduled = planner.getScheduleOnInterval(dateTimeStart, dateTimeEnd);
    
        HashMap<String, ArrayList<ArrayList<Task>>> response = new HashMap<String, ArrayList<ArrayList<Task>>>();
        response.put("Scheduled", scheduled);

        return response;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}