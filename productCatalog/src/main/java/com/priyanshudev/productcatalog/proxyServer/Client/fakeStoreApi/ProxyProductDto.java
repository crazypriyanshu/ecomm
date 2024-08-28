package com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi;

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
    private int price;
    private String description;
    private String[] images;
    private Date creationAt;
    private Date updatedAt;
    private ProxyCategoryDto category;
}
