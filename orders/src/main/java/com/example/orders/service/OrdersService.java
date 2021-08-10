package com.example.orders.service;

import com.example.orders.dto.OrderDto;
import com.example.orders.entity.OrderEntity;

public interface OrdersService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
