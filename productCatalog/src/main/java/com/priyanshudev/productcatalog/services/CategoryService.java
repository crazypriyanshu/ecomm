package com.priyanshudev.productcatalog.services;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    String getAllCategories();

    String getCategoryById(int id);
}
