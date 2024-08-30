package com.priyanshudev.productcatalog.inheritanceExamples.mappedSuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mentor_ms")
public class Mentor extends User {
    private String mentor_name;
    private String mentor_address;
}
