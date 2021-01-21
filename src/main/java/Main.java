import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import planner.Planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public String GetScheduleForDay(@RequestParam(name = "date") String date) {

        // Query param parsing as LocalDate object ( NOT SANITISED YET )
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTime = LocalDate.parse(date, formatter);

        // TODO -- planner
        Planner planner = new Planner();
        // planner.getScheduleOn(dateTime);

        return "Schedule for day : " + dateTime.format(formatter);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}