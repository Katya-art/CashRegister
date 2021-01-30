package com.KaterynaKravchenko.controller;

import com.KaterynaKravchenko.entity.Product;
import com.KaterynaKravchenko.service.ProductService;
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
    private ProductService productService;

    @GetMapping("/addProducts")
    public String open() {
        return "addProducts";
    }

    @PostMapping("/addProducts")
    public String addProduct(Product product, Model model) {
        if (!productService.addProduct(product)) {
            model.addAttribute("message", "Product exist");
            model.addAttribute("product", productService.findProduct(product));
            return "addProducts";
        }
        return "redirect:/main";
    }

    @GetMapping("/productList")
    public String productList(Model model) {
        model.addAttribute("products", productService.findAllProducts());
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
        productService.saveProduct(product, quantity);
        return "redirect:/productList";
    }
}
