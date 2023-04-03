package com.myapps.csvupload.repository;

import com.myapps.csvupload.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
