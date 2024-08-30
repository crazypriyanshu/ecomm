package com.priyanshudev.productcatalog.inheritanceExamples.joinedTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ta_jt extends User_jt{
    private String stream;
    private String specialization;
}
