package com.KaterynaKravchenko.repos;

import com.KaterynaKravchenko.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
    Product findByNameENOrNameUAOrId(String nameEN, String nameUA, Long id);
    Product findByNameEN(String nameEN);
    Product findByNameUA(String nameUA);
}
