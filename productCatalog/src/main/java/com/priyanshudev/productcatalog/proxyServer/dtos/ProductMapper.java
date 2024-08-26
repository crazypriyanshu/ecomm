package com.priyanshudev.productcatalog.proxyServer.dtos;

import com.priyanshudev.productcatalog.proxyServer.models.Product;

public class ProductMapper {
    public static ProxyProductDto toDto(Product product) {
        ProxyProductDto productDto = new ProxyProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public static Product toEntity(ProxyProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
