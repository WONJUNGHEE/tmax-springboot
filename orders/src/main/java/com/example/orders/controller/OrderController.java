package com.example.orders.controller;

import com.example.orders.dto.OrderDto;
import com.example.orders.entity.OrderEntity;
import com.example.orders.service.OrdersService;
import com.example.orders.vo.RequestOrder;
import com.example.orders.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class OrderController {
    Environment env;
    OrdersService orderService;

    public OrderController(Environment env, OrdersService orderService) {
        this.env = env;
        this.orderService = orderService;
    }

    @PostMapping(value = "/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder orderDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createDto =orderService.createOrder(orderDto);
        ResponseOrder returnValue = modelMapper.map(createDto,ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    @GetMapping(value = "/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId){
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ModelMapper().map(v,ResponseOrder.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
