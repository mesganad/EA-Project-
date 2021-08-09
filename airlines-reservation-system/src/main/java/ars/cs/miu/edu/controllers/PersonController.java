package ars.cs.miu.edu.controllers;

import ars.cs.miu.edu.models.*;
//import ars.cs.miu.edu.services.PassengerServiceImpl;
import ars.cs.miu.edu.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> findAllPersons() {
        List<Person> personList = personService.findAll();
        if(personList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/passengers")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> passengerList = personService.findAll();
        List<Person> resultListOfPassenger = new ArrayList<>();
        for(Person person: passengerList){
            if(person.getRole()==Role.PASSENGER){
                resultListOfPassenger.add(person);
            }
        }
        if(resultListOfPassenger.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultListOfPassenger);
    }

    @GetMapping("/passengers/{id}")
    public ResponseEntity<Person> getOnePassenger(@PathVariable long id){
        Person passenger= personService.findOne(id);
        if(passenger==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passenger);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Person> updatePassenger(@PathVariable long id, @RequestBody Passenger passenger){
//        Passenger updatedPassenger=null;
//        if(passenger.getType().equalsIgnoreCase("Passenger")){
//            passenger= (Passenger)passenger;
//            passenger.setRole(Role.PASSENGER);
//        }
//        else if(passenger.getType().equalsIgnoreCase("Admin")){
//            passenger= (Admin)passenger;
//            passenger.setRole(Role.ADMIN);
//        }
//        else if(passenger.getType().equalsIgnoreCase("Agent")){
//            passenger= (Agent)passenger;
//            passenger.setRole(Role.AGENT);
//        }

//        Passenger tobeUpdatePassenger =personService.findOne(id);
//        if(tobeUpdatePassenger==null){
//            updatedPassenger= personService.add(passenger);
//        }else {
//            passenger.setId(tobeUpdatePassenger.getId());
//            updatedPassenger= personService.update(passenger);
//        }
//        return  ResponseEntity.ok(updatedPassenger);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object>  deletePassenger(@PathVariable long id){
//        personService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

    @PostMapping("/persons")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
//        Passenger addedPassenger;
//        if(passenger.getType().equalsIgnoreCase("Passenger")){
//            passenger= (Passenger)passenger;
//            passenger.setRole(Role.PASSENGER);
//        }
//        else if(passenger.getType().equalsIgnoreCase("Admin")){
//            passenger= (Admin)passenger;
//            passenger.setRole(Role.ADMIN);
//        }
//        else if(passenger.getType().equalsIgnoreCase("Agent")){
//            passenger= (Agent)passenger;
//            passenger.setRole(Role.AGENT);
//        }
//        addedPassenger= personService.add(passenger);
        if(person.getRole().equals("PASSENGER")){
            Passenger passenger = (Passenger) person;
            personService.add(passenger);
        }
        else if(person.getRole().equals("ADMIN")){
            Admin admin = (Admin)  person;
            personService.add(admin);
        }

        return ResponseEntity.ok(person);

    }
}