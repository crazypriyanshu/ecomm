package com.priyanshudev.productcatalog.proxyServer.Client;

import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyProductDto;
import com.priyanshudev.productcatalog.proxyServer.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<ProxyProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProxyProductDto[]> products = restTemplate.getForEntity(
                "https://api.escuelajs.co/api/v1/products",
                ProxyProductDto[].class
        );

        return Arrays.asList(products.getBody());
    }


//    Optional<ProxyProductDto> getSingleProduct(Long id) throws NotFoundException {
//
//
//    }
}
