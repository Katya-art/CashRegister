package com.KaterynaKravchenko.service;

import com.KaterynaKravchenko.entity.Order;
import com.KaterynaKravchenko.entity.Product;
import com.KaterynaKravchenko.entity.OrderDetails;
import com.KaterynaKravchenko.repos.OrderRepo;
import com.KaterynaKravchenko.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    private Set<OrderDetails> orderProducts = new HashSet<>();
    private long orderPrice = 0;

    public Set<OrderDetails> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderDetails> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String addToOrder(String nameEN, String nameUA, Long id, Long quantity) {
        Product productFromBD = productRepo.findByNameENOrNameUAOrId(nameEN, nameUA, id);
        if (productFromBD == null) {
            return "Can't found such product";
        }
        if (productFromBD.getQuantity() < quantity) {
            return "This product is not in the given quality";
        }
        OrderDetails newOrder = new OrderDetails();
        newOrder.setProductId(productFromBD.getId());
        newOrder.setQuantity(quantity);
        orderProducts.add(newOrder);
        setOrderPrice(getOrderPrice() + productFromBD.getPrice() * quantity);
        productFromBD.setQuantity(productFromBD.getQuantity() - quantity);
        productRepo.save(productFromBD);
        return "Product was successfully added to order";
    }

    public boolean saveOrder(Order order) {
        order.setOrderDetails(getOrderProducts());
        order.setTotalPrice(getOrderPrice());
        orderRepo.save(order);
        setOrderProducts(new HashSet<>());
        return true;
    }

    public Iterable<Order> findAllOrders() {
        return orderRepo.findAll();
    }
}
