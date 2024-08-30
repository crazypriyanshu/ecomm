package com.priyanshudev.productcatalog.inheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car extends Vehicle{
    private String color;

}
