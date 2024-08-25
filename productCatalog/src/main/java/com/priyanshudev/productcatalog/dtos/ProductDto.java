package com.priyanshudev.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private String title;
    private String description;
    private int price;
}
