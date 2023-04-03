package com.myapps.csvupload.controller;

import com.myapps.csvupload.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Value("${cloud-service}")
    private String cloudService;


    @GetMapping("/export")
    public String exportProducts() {
        productService.exportProducts();
        return "Products exported successfully to : "+cloudService;
    }


}
