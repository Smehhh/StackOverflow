package com.example.demo.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HealthController {
    @RequestMapping("/health")
    public ResponseEntity<String> getStatus(){
        Random random = new Random();
        int rint = random.nextInt(2);
        if (rint == 1){
            return new ResponseEntity<>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
