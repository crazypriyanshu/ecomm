package com.priyanshudev.productcatalog.inheritanceExamples.cascadeTypes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private String city;
    private String state;
    private int zip;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}
