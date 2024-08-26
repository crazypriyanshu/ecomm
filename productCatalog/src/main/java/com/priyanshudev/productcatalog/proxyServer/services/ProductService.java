package com.priyanshudev.productcatalog.proxyServer.services;

import com.priyanshudev.productcatalog.proxyServer.models.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product getSingleProduct(Long id);

    Product addNewProduct(Long productId,
                             String title,
                             double price,
                             String description,
                             String category,
                             String imageUrl);


    // Product object has only those fields that needs to be updated
    // everything else is null
}
