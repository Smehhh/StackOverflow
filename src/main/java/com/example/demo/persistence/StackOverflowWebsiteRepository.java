package com.example.demo.persistence;

import com.example.demo.model.StackOverflowWebSite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StackOverflowWebsiteRepository extends MongoRepository<StackOverflowWebSite, String> {
    List<StackOverflowWebSite> findByWebsite(String website); //SPRING CAN DO THIS BY HIS OWN
   /* public List<StackOverflowWebSite> findAll() {
        return mongoTemplate.findAll(StackOverflowWebSite.clуass);
    }*/
}
