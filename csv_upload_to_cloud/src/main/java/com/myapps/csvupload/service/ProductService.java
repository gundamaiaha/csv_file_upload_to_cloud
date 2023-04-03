package com.myapps.csvupload.service;

import com.myapps.csvupload.model.Product;
import com.myapps.csvupload.repository.ProductRepository;
import com.myapps.csvupload.service.FileUploadService;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FileUploadService fileUploadService;


    public void exportProducts() {
        try {
            List<Product> products = getProducts();
            File productsCsv = generateProductsCsv(products);
            fileUploadService.uploadFileToCloud(productsCsv);

            System.out.println("productsCsv = " + productsCsv);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        CompletableFuture.supplyAsync(() -> getProducts())
//                .thenApply(products -> {
//                    try {
//                        System.out.println("in try");
//                        return generateProductsCsv(products);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                }).thenApply(fileName -> {
//                    System.out.println("File Name -->" + fileName);
//                    return null;
//                });


    }


    private List<Product> getProducts() {
        log.info("in getProducts");
        return productRepository.findAll();

    }

    private File generateProductsCsv(List<Product> products) throws IOException {
        log.info("in generateProductsCsv");
        File file = Files.createFile(Path.of("products_" + Instant.now() + ".csv")).toFile();
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        String[] headers = {"Product ID", "Product Name", "Price", "Created At", "Updated At"};
        csvWriter.writeNext(headers);
        products.forEach(product -> {
            String[] productArr = new String[5];
            productArr[0] = String.valueOf(product.getId());
            productArr[1] = product.getName();
            productArr[2] = String.valueOf(product.getPrice());
            productArr[3] = String.valueOf(product.getCreatedAt());
            productArr[4] = String.valueOf(product.getUpdatedAt());
            csvWriter.writeNext(productArr);
        });
        csvWriter.close();
        fileWriter.close();
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        return file;
    }

}
