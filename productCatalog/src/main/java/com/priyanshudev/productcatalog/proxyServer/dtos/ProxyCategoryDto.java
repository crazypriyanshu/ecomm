package com.priyanshudev.productcatalog.proxyServer.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ProxyCategoryDto {
    private int id;
    private String name;
    private String image;
    private String creationAt;
    private String updatedAt;
}
