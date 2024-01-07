package com.leviathan.shop.controller;

import com.leviathan.shop.entity.*;
import com.leviathan.shop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void initialize() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

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
    public void getAllOrders() throws Exception {
        Mockito.when(orderService.getAll()).thenReturn(Collections.singletonList(order));
        mockMvc.perform(MockMvcRequestBuilders.get("/order/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(order.getId()));
    }

    @Test
    public void getOrder() throws Exception {
        Mockito.when(orderService.get(1L)).thenReturn(order);
        mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}", order.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(order.getId()));
    }

    @Test
    public void getOrderByUserId() throws Exception {
        Mockito.when(orderService.getAllByUserId(1L)).thenReturn(Collections.singletonList(order));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/order/user/{id}", order.getUser().getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(order.getUser().getId()));
    }

    @Test
    public void getOrderByClothesId() throws Exception {
        Mockito.when(orderService.getAllByClothesId(1L)).thenReturn(Collections.singletonList(order));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/order/clothes/{id}", order.getClothes().getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(order.getClothes().getId()));
    }

    @Test
    public void deleteOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete/{id}", order.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
