package com.KaterynaKravchenko.service;

import com.KaterynaKravchenko.entity.Product;
import com.KaterynaKravchenko.entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    private Set<OrderDetails> orderDetails = new HashSet<>();

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public boolean addToOrder(String nameEN, String nameUA, Long id, Long quantity) {
        Product productFromBD = productService.findProduct(nameEN, nameUA, id);
        if (productFromBD == null) {
            return false;
        }
        OrderDetails newOrder = new OrderDetails();
        newOrder.setProductId(productFromBD.getId());
        newOrder.setQuantity(quantity);
        orderDetails.add(newOrder);
        return true;
    }
}
