package com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProxyCategoryDto {
    private int id;
    private String name;
    private String image;
    private String creationAt;
    private String updatedAt;
}
