package com.store.controller;

import com.store.dto.ProductDTO;
import com.store.dto.StatusDTO;
import com.store.model.Product;
import com.store.model.Status;
import com.store.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/status")
public class StatusController {

    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/get-all")
    public ResponseEntity<List<StatusDTO>> getAllStatus() {
        List<Status> list= statusRepository.findAll();
        List<StatusDTO> statusDTOS=new ArrayList<>();

        for(Status status: list){
            statusDTOS.add(new StatusDTO(status.getName()));
        }

        return ResponseEntity.ok(statusDTOS);

    }


    @PostMapping("/create")
    public ResponseEntity createStatus(@RequestBody StatusDTO statusDTO){
        Status status=new Status(statusDTO);
    statusRepository.save(status);
    return ResponseEntity.ok(HttpStatus.OK);
    }

    //Удаление по названию
    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteProduct(@PathVariable(value = "name") String name ) {
        try {
            statusRepository.delete(statusRepository.findByName(name).get(0));
            return (ResponseEntity.ok(HttpStatus.OK));
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.NOT_FOUND);
        }
    }


}
