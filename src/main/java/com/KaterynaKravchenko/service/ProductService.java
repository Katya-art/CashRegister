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
        Product productFromDB = findProduct(product.getNameEN(), product.getNameUA(), product.getId());
        if (productFromDB != null) {
            return false;
        }
        productRepo.save(product);
        return true;
    }

    public Product findProduct(String nameEN, String nameUA, Long id) {
        return productRepo.findByNameENOrNameUAOrId(nameEN, nameUA, id);
    }

    public Iterable<Product> findAllProducts(){
        return productRepo.findAll();
    }

}
