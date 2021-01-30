package com.KaterynaKravchenko.service;

import com.KaterynaKravchenko.entity.Product;
import com.KaterynaKravchenko.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public boolean addProduct(Product product) {
        if (findProduct(product) != null) {
            return false;
        }
        productRepo.save(product);
        return true;
    }

    public Product findProduct(Product product) {
        return productRepo.findByNameENOrNameUAOrId(product.getNameEN(), product.getNameUA(), product.getId());
    }

    public Iterable<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public boolean saveProduct(Product product, Long quantity) {
        product.setQuantity(quantity);
        productRepo.save(product);
        return true;
    }

}
