package com.example.demo.service;

import com.example.demo.model.StackOverflowWebSite;
import com.example.demo.persistence.StackOverflowWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class StackOverflowService {
    private static List<StackOverflowWebSite> items = new ArrayList<>();
    @Autowired
    private StackOverflowWebsiteRepository stackOverflowWebsiteRepository;
    static {
        items.add(new StackOverflowWebSite("stackoverflow", "https://stackoverflow.com/",
               "http://cdn.sstatic.net/Sites/stackoverflow/img/favicon.ico", "StackExchange",
                "for professional and enthusiast programmers"));
        items.add(new StackOverflowWebSite("serverfault", "https://serverfault.com/",
                "http://cdn.sstatic.net/Sites/serverfault/img/favicon.ico", "StackExchange",
                "for system and network administrators"));
    }
    public List<StackOverflowWebSite> findAll() {
        return stackOverflowWebsiteRepository.findAll();
    }

    @PostConstruct
    public void init(){
        stackOverflowWebsiteRepository.saveAll(items);
    }
}
