package com.example.productservicemorningbatch.models;

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
    private Category category;
    private String description;
    private String image;
}
