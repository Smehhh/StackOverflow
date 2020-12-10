package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document //document for mongo
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StackOverflowWebSite {

    @Id
    private String id;
    private String website;

    private String iconImageUrl;
    private String title;
    private String description;

}
