package com.example.demo.persistence;

import com.example.demo.model.User;
import com.example.demo.model.UserField;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class UserDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<User> findByUserName(String s) {
        return Optional.ofNullable(mongoTemplate.findOne(query(where(UserField.USER_NAME.field()).is(s)), User.class));
    }

    public void save(@NotNull User user) {
        mongoTemplate.save(user);
    }
}
