package com.priyanshudev.productcatalog.proxyServer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private Long productId;
    private String title;
    private Double price;
    private String description;
    private String[] imageUrl;
    private Category category;
}
