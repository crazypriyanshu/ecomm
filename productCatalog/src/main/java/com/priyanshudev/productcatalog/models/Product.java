package com.priyanshudev.productcatalog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String title;
    private Double price;
    private String description;
    private String imageUrl;
    // Product : Category :: m: 1
    @ManyToOne
    private Category category;
}
