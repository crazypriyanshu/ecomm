package com.priyanshudev.productcatalog.inheritanceExamples.mappedSuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ta_ms")
public class Ta extends User{
    private String stream;
    private String specialization;
}
