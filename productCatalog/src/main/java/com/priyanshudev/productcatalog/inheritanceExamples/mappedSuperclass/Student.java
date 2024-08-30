package com.priyanshudev.productcatalog.inheritanceExamples.mappedSuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "student_ms")
public class Student extends User{
    private String course;
    private String grade;
}
