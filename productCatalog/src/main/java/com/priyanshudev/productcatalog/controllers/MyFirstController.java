package com.priyanshudev.productcatalog.controllers;

import com.priyanshudev.productcatalog.services.MyFirstService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class MyFirstController {
    private MyFirstService myFirstService;
    public MyFirstController(MyFirstService myFirstService) {
        this.myFirstService = myFirstService;

    }

    @GetMapping("/")
    public String sayHi() {
        return "say Hi There!";
    }

}
