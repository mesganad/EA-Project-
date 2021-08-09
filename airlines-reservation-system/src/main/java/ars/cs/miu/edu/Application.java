package ars.cs.miu.edu;

import ars.cs.miu.edu.controllers.FlightController;
import ars.cs.miu.edu.models.Flight;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        ApplicationContext context= SpringApplication.run(Application.class, args);
//        FlightController flightController=context.getBean(FlightController.class);
//        List<Flight> flights= flightController.getAllFlights();
//        flights.forEach(System.out::println);
    }
}
