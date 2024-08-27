package com.priyanshudev.productcatalog.proxyServer.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class Category extends BaseModel{

    private Long categoryId;
    private String name;
    private String image;
    private LocalDate creationAt;
    private LocalDate updatedAt;

}
