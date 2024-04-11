package com.example.productservicemorningbatch.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
//    @Id
//    private long id;
    private String title;
    private Double price;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Category category;
    private String description;
    private String image;
}
