package com.example.demo.web;


import com.example.demo.model.StackOverflowWebSite;
import com.example.demo.service.StackOverflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stackoverflow")
public class StackOverflowController {

    @Autowired
    private StackOverflowService stackOverflowService;

    @RequestMapping
    public List<StackOverflowWebSite> getListOfProviders(){

            return stackOverflowService.findAll();
    }
}
