package com.priyanshudev.productcatalog.proxyServer.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ProxyCreateProductDto {
    private Long id;
    private String title;
    private int price;
    private String description;
    private int categoryId;
    private String[] images;
    private String categoryName;
}
