package com.epam.KaterynaKravchenko.controller;

import com.epam.KaterynaKravchenko.domain.Product;
import com.epam.KaterynaKravchenko.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PreAuthorize("hasAuthority('MERCHANDISER')")
public class ProductsController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/addProducts")
    public String open() { return "addProducts"; }

    @PostMapping("/addProducts")
    public String addProduct(Product product, Model model) {

        if (productRepo.findByNameEN(product.getNameEN()) != null) {
            model.addAttribute("message1", "Product with this parameter already exists!");
            return "addProducts";
        }

        if (productRepo.findByNameUA(product.getNameUA()) != null) {
            model.addAttribute("message2", "Product with this parameter already exists!");
            return "addProducts";
        }

        if (productRepo.findByCode(product.getCode()) != null) {
            model.addAttribute("message3", "Product with this parameter already exists!");
            return "addProducts";
        }

        productRepo.save(product);
        return "redirect:/main";
    }

    @GetMapping("/productList")
    public String productList(Model model){
        model.addAttribute("products", productRepo.findAll());
        return "productList";
    }
}
