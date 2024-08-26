package com.priyanshudev.productcatalog.proxyServer.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product extends BaseModel{
    private Long productId;
    private String title;
    private int price;
    private String description;
    private String[] imageUrl;
    private Category category;
}
