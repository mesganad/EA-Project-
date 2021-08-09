package ars.cs.miu.edu.controllers;

import ars.cs.miu.edu.models.Address;
import ars.cs.miu.edu.models.Address;
import ars.cs.miu.edu.services.AddressServiceImpl;
import ars.cs.miu.edu.services.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressServiceImpl addresssService;


    @GetMapping("/")
    public ResponseEntity<List<Address>> findAll() {
        List<Address> addressList = addresssService.findAll();
        if(addressList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getOneAddress(@PathVariable long id){
        Address address= addresssService.findOne(id);
        if(address==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable long id, @RequestBody Address address){
        Address updatedAddress=null;
        Address tobeUpdateAddress =addresssService.findOne(id);
        if(tobeUpdateAddress==null){
            updatedAddress= addresssService.update(address);
        }else {
            address.setId(tobeUpdateAddress.getId());
            updatedAddress= addresssService.update(address);
        }
        return  ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  deleteAddress(@PathVariable long id){
        addresssService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address){
        Address addedAddress= addresssService.add(address);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedAddress.getId())
                .toUri();

        return ResponseEntity.created(uri).body(addedAddress);

    }
}


