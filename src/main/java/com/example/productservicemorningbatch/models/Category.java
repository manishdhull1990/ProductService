package com.example.productservicemorningbatch.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
//    @Id
//    private long id;
    @OneToMany(mappedBy ="category",cascade = {CascadeType.REMOVE})
    private List<Product> products;
    private String title;
}
