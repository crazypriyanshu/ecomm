package com.priyanshudev.productcatalog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

    private String name;
    private String description;
    private LocalDate creationAt;
    private LocalDate updatedAt;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
