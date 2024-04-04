package com.example.productservicemorningbatch.inheritancepresentation.singletable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TA extends User{
    private int numberOfSessions;
    private double avgRating;
}
