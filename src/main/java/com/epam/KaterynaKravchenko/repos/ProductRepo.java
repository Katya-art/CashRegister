package com.epam.KaterynaKravchenko.repos;

import com.epam.KaterynaKravchenko.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByNameEN(String nameEN);
    Product findByNameUA(String nameUA);
    Product findByCode(String code);
}
