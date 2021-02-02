package com.KaterynaKravchenko.controller;

import com.KaterynaKravchenko.entity.Order;
import com.KaterynaKravchenko.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('CASHIER')")
public class OrderController {
    @Autowired
    private OrderService orderService;

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
            model.addAttribute("message", orderService.addToOrder(nameEN, nameUA, id, quantity));
            model.addAttribute("products", orderService.getOrderProducts());
            return "createOrder";
        }
        if (action.equals("save")) {
            orderService.saveOrder(order);
            return "redirect:/main";
        }
        return "createOrder";
    }

    @GetMapping("/orderList")
    public String orderList(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "orderList";
    }
}
