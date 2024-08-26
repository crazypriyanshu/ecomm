package com.priyanshudev.productcatalog.proxyServer.services;

import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyProductDto;
import com.priyanshudev.productcatalog.proxyServer.models.Category;
import com.priyanshudev.productcatalog.proxyServer.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class proxyServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public proxyServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    /*
    * Return a product object with all the details of fetched product
    * Id of category will be null, but name would be correct
    * */
    public Product getSingleProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // getForEntity - (url, returnType, paramsInUrl) ->
        ResponseEntity<ProxyProductDto> response = restTemplate.getForEntity("https://api.escuelajs.co/api/v1/products/{id}", ProxyProductDto.class, id);
        ProxyProductDto productDto = response.getBody();
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImages());
        product.setProductId(productDto.getId());
        product.setCreatedAt(productDto.getCreationAt());
        product.setLastUpdatedAt(productDto.getUpdatedAt());
        Category category = new Category();
        category.setName(productDto.getCategory().getName());
        category.setId(productDto.getCategory().getId());
        category.setCreatedAt(productDto.getCategory().getCreationAt());
        category.setLastUpdatedAt(productDto.getCategory().getUpdatedAt());
        category.setImage(productDto.getCategory().getImage());
        product.setCategory(category);
        return product;
    }


    @Override
    public Product addNewProduct(Long productId, String title, double price, String description, String category, String imageUrl) {
        return null;
    }


}
