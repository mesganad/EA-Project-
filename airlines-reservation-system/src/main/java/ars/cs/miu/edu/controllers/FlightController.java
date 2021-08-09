package ars.cs.miu.edu.controllers;

import ars.cs.miu.edu.models.Flight;
import ars.cs.miu.edu.models.Flight;
import ars.cs.miu.edu.services.FlightServiceImpl;
import ars.cs.miu.edu.services.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightServiceImpl flightsService;


    @GetMapping("/")
    public ResponseEntity<List<Flight>> findAll() {
        List<Flight> flightList = flightsService.findAll();
        if(flightList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getOneFlight(@PathVariable long id){
        Flight flight= flightsService.findOne(id);
        if(flight==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable long id, @RequestBody Flight flight){
        Flight updatedFlight=null;
        Flight tobeUpdateFlight =flightsService.findOne(id);
        if(tobeUpdateFlight==null){
            updatedFlight= flightsService.update(flight);
        }else {
            flight.setId(tobeUpdateFlight.getId());
            updatedFlight= flightsService.update(flight);
        }
        return  ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  deleteFlight(@PathVariable long id){
        flightsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
    Flight addedFlight= flightsService.add(flight);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedFlight.getId())
                .toUri();

        return ResponseEntity.created(uri).body(addedFlight);

}
}


