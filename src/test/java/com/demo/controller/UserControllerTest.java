package com.demo.controller;

import com.demo.dto.CustomerRequestDto;
import com.demo.dto.CustomerResponseDto;
import com.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void registerUserTest() throws Exception {

        Mockito.when(userService.saveUser(getCustomerRequestDto())).thenReturn(getCustomerResponseDto());
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(getCustomerRequestDto())))
                .andExpect(status().isOk());
        Mockito.verify(userService, Mockito.times(1)).saveUser(getCustomerRequestDto());
    }

    public CustomerRequestDto getCustomerRequestDto() {
        CustomerRequestDto customerRequestDto =  new CustomerRequestDto();
        customerRequestDto.setEmail("test@email.com");
        customerRequestDto.setRole("user");
        customerRequestDto.setUserName("test");
        customerRequestDto.setPassword("test123");
        return customerRequestDto;
    }

    public CustomerResponseDto getCustomerResponseDto() {
        CustomerResponseDto customerResponseDto =  new CustomerResponseDto();
        customerResponseDto.setEmail("test@email.com");
        customerResponseDto.setRole("user");
        customerResponseDto.setUserName("test");
        return customerResponseDto;
    }
}
