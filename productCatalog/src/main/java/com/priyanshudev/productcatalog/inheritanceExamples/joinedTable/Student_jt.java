package com.priyanshudev.productcatalog.inheritanceExamples.joinedTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student_jt extends User_jt{
    private String course;
    private String grade;
}
