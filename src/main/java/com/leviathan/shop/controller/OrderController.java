package com.leviathan.shop.controller;

import com.leviathan.shop.dto.OrderDto;
import com.leviathan.shop.entity.Order;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "get all orders")
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "get order by id")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @Operation(summary = "get order by user id")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getAllByUserId(id), HttpStatus.OK);
    }

    @Operation(summary = "get order by clothes id")
    @GetMapping("/clothes/{id}")
    public ResponseEntity<List<Order>> getOrdersByClothesId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getAllByClothesId(id), HttpStatus.OK);
    }

    @Operation(summary = "add order")
    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody @Valid OrderDto orderDto) throws NotFoundException {
        return new ResponseEntity<>(orderService.add(orderDto), HttpStatus.OK);
    }

    @Operation(summary = "update order")
    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody @Valid Order order) {
        return new ResponseEntity<>(orderService.update(order), HttpStatus.OK);
    }

    @Operation(summary = "delete order by id")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
        return HttpStatus.OK;
    }
}
