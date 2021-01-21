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

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}