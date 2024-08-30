package com.priyanshudev.productcatalog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/categories")
@RestController
public class CategoryController {

    @GetMapping("")
    public String getAllCategories() {
        return "All Categories";
    }

    @GetMapping("/{:id}")
    public String getCategoryById(String id) {
        return "Category Details of " + id;
    }


}
