package com.leviathan.shop.repository;

import com.leviathan.shop.entity.*;
import com.leviathan.shop.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryTest {
    @Mock
    private OrderRepository orderRepository;

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
    public void findAll() {
        Mockito.when(orderRepository.findAll())
                .thenReturn(Collections.singletonList(order));
        Assertions.assertEquals(1, orderRepository.findAll().size());
        Assertions.assertEquals(order, orderRepository.findAll().get(0));
    }

    @Test
    public void find() throws Exception {
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
        Assertions.assertEquals(
                order, orderRepository.findById(1L).orElseThrow(
                        () -> new NotFoundException("\"Order with id '\" + id + \"' doesn't exist!\"")
                )
        );
    }

    @Test
    public void findAllByUserId() {
        Mockito.when(orderRepository.findByUserId(Mockito.anyLong()))
                .thenReturn(Collections.singletonList(order));
        Assertions.assertEquals(1, orderRepository.findByUserId(1L).size());
    }

    @Test
    public void findAllByClothesId() {
        Mockito.when(orderRepository.findByClothesId(Mockito.anyLong()))
                .thenReturn(Collections.singletonList(order));
        Assertions.assertEquals(1, orderRepository.findByClothesId(1L).size());
    }

    @Test
    public void saveAll() {
        Mockito.when(orderRepository.saveAll(Mockito.anyCollection()))
                .thenReturn(Collections.singletonList(order));
        Assertions.assertEquals(1, orderRepository.saveAll(Collections.singletonList(order)).size());
        Assertions.assertEquals(order, orderRepository.saveAll(Collections.singletonList(order)).get(0));
    }
}
