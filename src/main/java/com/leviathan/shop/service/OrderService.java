package com.leviathan.shop.service;

import com.leviathan.shop.dto.OrderDto;
import com.leviathan.shop.entity.Order;
import com.leviathan.shop.exception.NotFoundException;
import com.leviathan.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ClothesService clothesService;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order get(Long id) throws NotFoundException {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order with id '" + id + "' doesn't exist!")
        );
    }

    public List<Order> getAllByUserId(Long id) {
        return orderRepository.findByUserId(id);
    }

    public List<Order> getAllByClothesId(Long id) {
        return orderRepository.findByClothesId(id);
    }

    public Order add(OrderDto orderDto) throws NotFoundException {
        return orderRepository.save(
                Order.builder()
                        .user(userService.get(orderDto.getUserId()))
                        .clothes(clothesService.get(orderDto.getClothesId()))
                        .address(orderDto.getAddress())
                        .build()
        );
    }

    public Order update(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
