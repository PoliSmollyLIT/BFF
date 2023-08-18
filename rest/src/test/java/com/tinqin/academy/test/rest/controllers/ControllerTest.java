package com.tinqin.academy.test.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.academy.persistence.models.Cart;
import com.tinqin.academy.persistence.models.Role;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.repositories.CartRepository;
import com.tinqin.academy.persistence.repositories.UserRepository;
import com.tinqin.academy.rest.BffApplication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = {CartController.class})

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class ControllerTest {

    /* @Autowired
    private MockMvc mockMvc;

    private final GetCartOperation getCartOperation;

    private User user;
    private Cart cart;

    @BeforeEach
    public void setup(){
        user = User.builder()
                .firstName("pesho")
                .lastName("petrov")
                .email("pesho@gmail.com")
                .password("1234")
                .role(Role.USER)
                .build();

    }

    @Test
    public void getItem_Successfully() throws Exception {

        userRepository.save(user);

        cart = Cart.builder()
                .user(user.getId())
                .build();
        cartRepository.save(cart);

        this.mockMvc.perform(
            get("/cart/{id}", cart.getId())
                )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['cartId']").value(cart.getId()))
                .andExpect(jsonPath("$['user']").value(cart.getUser()));
    }
    */

}
