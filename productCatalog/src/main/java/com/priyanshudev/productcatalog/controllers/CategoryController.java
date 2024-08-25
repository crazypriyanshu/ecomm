package com.priyanshudev.productcatalog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @GetMapping("/categories")
    public String getAllCategories() {
        return "All Categories";
    }

    @GetMapping("category/{:id}")
    public String getCategoryById(String id) {
        return "Category Details of " + id;
    }






}
