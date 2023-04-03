package com.myapps.csvupload.util;

import com.myapps.csvupload.model.Product;
import com.myapps.csvupload.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInjector implements ApplicationRunner {

    private final ProductRepository productRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Product product1= new Product("IPhone XII", new BigDecimal(1000));
        Product product2= new Product("Samsung S23", new BigDecimal(1200));
        Product product3= new Product("Redmi XII", new BigDecimal(200));
        Product product4= new Product("OnePlus X", new BigDecimal(600));
        Product product5= new Product("IPhone X", new BigDecimal(800));
        productRepository
                .saveAll(Arrays.asList(product1,product2,product3,product4,product5));
    }
}
