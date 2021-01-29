package com.KaterynaKravchenko.repos;

import com.KaterynaKravchenko.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {
}
