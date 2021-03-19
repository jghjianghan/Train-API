package com.proifh.trainmanager.controller;

import java.util.List;

import com.proifh.trainmanager.model.Train;
import com.proifh.trainmanager.repository.TrainRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TrainController {
    @Autowired
    TrainRepository trainRepository;
    
    @GetMapping("/trains")
    public ResponseEntity<List<Train>> getAllTrains(@RequestParam(required = false) String title) {
        try { 
            List<Train> tutorials = new ArrayList<Train>();
            
            if (title == null) {
                trainRepository.findAll().forEach(tutorials::add);
            }else{
                trainRepository.findByTitleContaining(title).forEach(tutorials::add);
            }
            
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
