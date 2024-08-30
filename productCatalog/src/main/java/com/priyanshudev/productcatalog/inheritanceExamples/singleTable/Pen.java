package com.priyanshudev.productcatalog.inheritanceExamples.singleTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pen extends MyProduct{
    private String color;
}
