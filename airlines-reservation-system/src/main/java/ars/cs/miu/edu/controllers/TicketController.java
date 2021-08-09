package ars.cs.miu.edu.controllers;

import ars.cs.miu.edu.models.Ticket;
import ars.cs.miu.edu.models.Ticket;
import ars.cs.miu.edu.services.TicketServiceImpl;
import ars.cs.miu.edu.services.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketServiceImpl ticketsService;


    @GetMapping("/")
    public ResponseEntity<List<Ticket>> findAll() {
        List<Ticket> ticketList = ticketsService.findAll();
        if(ticketList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticketList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getOneTicket(@PathVariable long id){
        Ticket ticket= ticketsService.findOne(id);
        if(ticket==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable long id, @RequestBody Ticket ticket){
        Ticket updatedTicket=null;
        Ticket tobeUpdateTicket =ticketsService.findOne(id);
        if(tobeUpdateTicket==null){
            updatedTicket= ticketsService.update(ticket);
        }else {
            ticket.setId(tobeUpdateTicket.getId());
            updatedTicket= ticketsService.update(ticket);
        }
        return  ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  deleteTicket(@PathVariable long id){
        ticketsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
        Ticket addedTicket= ticketsService.add(ticket);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedTicket.getId())
                .toUri();

        return ResponseEntity.created(uri).body(addedTicket);

    }
}


