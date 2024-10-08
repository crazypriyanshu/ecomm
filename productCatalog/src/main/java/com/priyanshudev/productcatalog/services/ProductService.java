package com.priyanshudev.productcatalog.services;

import com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi.ProxyProductDto;
import com.priyanshudev.productcatalog.exceptions.NotFoundException;
import com.priyanshudev.productcatalog.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Optional<Product> getSingleProduct(Long id) throws NotFoundException;

    List<Product> getAllProducts() throws NotFoundException;

    Product addNewProduct(ProxyCreateProductDto product) throws NotFoundException;

    Product updateProduct(Long id, ProxyProductDto product) throws NotFoundException;

    boolean deleteProduct(Long id);


    // Product object has only those fields that needs to be updated
    // everything else is null
}
