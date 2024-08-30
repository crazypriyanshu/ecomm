package com.priyanshudev.productcatalog.inheritanceExamples.joinedTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mentor_jt extends User_jt {
    private String mentor_name;
    private String mentor_address;
}
