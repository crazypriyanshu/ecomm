package com.priyanshudev.productcatalog.inheritanceExamples.singleTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book extends MyProduct{
    private String author;
}
