package com.epam.KaterynaKravchenko.controller;

import com.epam.KaterynaKravchenko.domain.Product;
import com.epam.KaterynaKravchenko.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('MERCHANDISER')")
public class ProductsController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/addProducts")
    public String open() { return "addProducts"; }

    @PostMapping("/addProducts")
    public String addProduct(Product product, Model model) {
        Product productFromDB = productRepo.findByNameENOrNameUAOrCode(product.getNameEN(), product.getNameUA(),
                product.getCode());
        if (productFromDB != null) {
            model.addAttribute("message", "Product exist");
            model.addAttribute("product", productFromDB);
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

    @GetMapping("/product/{product}")
    public String productEditForm(@PathVariable Product product, Model model) {
        model.addAttribute("product", product);

        return "productEdit";
    }

    @PostMapping("/productList")
    public String productSave(
            @RequestParam Long quantity,
            @RequestParam("productId") Product product
    ) {
        product.setQuantity(quantity);
        productRepo.save(product);
        return "redirect:/productList";
    }
}
