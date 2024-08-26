package com.priyanshudev.productcatalog.proxyServer.dtos;

import com.priyanshudev.productcatalog.proxyServer.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;

}
