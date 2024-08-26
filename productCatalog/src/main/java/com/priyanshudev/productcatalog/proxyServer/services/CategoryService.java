package com.priyanshudev.productcatalog.proxyServer.services;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    String getAllCategories();

    String getCategoryById(int id);
}
