package com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi;

import com.priyanshudev.productcatalog.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;

}
