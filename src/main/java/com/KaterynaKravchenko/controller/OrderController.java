package com.KaterynaKravchenko.controller;

import com.KaterynaKravchenko.entity.Order;
import com.KaterynaKravchenko.repos.OrderRepo;
import com.KaterynaKravchenko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;

@Controller
@PreAuthorize("hasAuthority('CASHIER')")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/createOrder")
    public String newOrder() {
        return "createOrder";
    }

    @PostMapping("/createOrder")
    public String addOrder(
            @RequestParam String action,
            @RequestParam String nameEN,
            @RequestParam String nameUA,
            @RequestParam Long id,
            @RequestParam Long quantity,
            Model model,
            Order order
    ) {
        if (action.equals("add")) {
            if (!orderService.addToOrder(nameEN, nameUA, id, quantity)) {
                model.addAttribute("message", "Product not founded");
                return "createOrder";
            }
            model.addAttribute("products", orderService.getOrderDetails());
            return "createOrder";
        }
        if (action.equals("save")) {
            order.setOrderDetails(orderService.getOrderDetails());
            orderRepo.save(order);
            orderService.setOrderDetails(new HashSet<>());
            return "redirect:/main";
        }
        return "createOrder";
    }
}
