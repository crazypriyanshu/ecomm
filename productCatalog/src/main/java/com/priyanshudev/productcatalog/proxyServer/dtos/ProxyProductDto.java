package com.priyanshudev.productcatalog.proxyServer.dtos;

import com.priyanshudev.productcatalog.proxyServer.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ProxyProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Date creationAt;
    private Date updatedAt;
    private String[] images;
    private ProxyCategoryDto category;
    private Long categoryId;
}
