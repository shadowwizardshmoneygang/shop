package com.leviathan.shop.service;

import com.leviathan.shop.dto.OrderDto;
import com.leviathan.shop.entity.*;
import com.leviathan.shop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @Mock
    private ClothesService clothesService;

    @InjectMocks
    private OrderService orderService;

    private final Order order = Order.builder()
            .id(1L)
            .user(
                    User.builder()
                            .id(1L)
                            .name("Anton")
                            .phone("88005353535")
                            .build()
            )
            .clothes(
                    Clothes.builder()
                            .id(1L)
                            .title("Magnolia")
                            .description("Suckless shoes")
                            .collection(
                                    Collection.builder()
                                            .id(1L)
                                            .title("Religion")
                                            .description("God is dead")
                                            .build()
                            )
                            .category(
                                    Category.builder()
                                            .id(1L)
                                            .title("Shoes")
                                            .build()
                            )
                            .amount(100)
                            .price(BigDecimal.valueOf(100))
                            .build()
            )
            .address("The White House 1600 Pennsylvania Avenue NW Washington, DC 20500")
            .build();

    @Test
    public void testGetAllCategories() {
        Mockito.when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));
        Assertions.assertEquals(order, orderService.getAll().get(0));
    }

    @Test
    public void testGetCategory() throws Exception {
        Mockito.when(orderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(order));
        Assertions.assertEquals(order, orderService.get(1L));
    }

    @Test
    public void testGetClothesByCollectionId() {
        Mockito.when(orderRepository.findByUserId(Mockito.any(Long.class)))
                .thenReturn(Collections.singletonList(order));
        Assertions.assertEquals(order, orderService.getAllByUserId(1L).get(0));
    }

    @Test
    public void testGetClothesByCategoryId() {
        Mockito.when(orderRepository.findByClothesId(Mockito.any(Long.class)))
                .thenReturn(Collections.singletonList(order));
        Assertions.assertEquals(order, orderService.getAllByClothesId(1L).get(0));
    }

    @Test
    public void testAddClothes() throws Exception {
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Assertions.assertEquals(order, orderService.add(new OrderDto()));
    }

    @Test
    public void testUpdateClothes() {
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Assertions.assertEquals(order, orderService.update(order));
    }
}
