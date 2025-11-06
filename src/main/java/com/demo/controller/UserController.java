package com.demo.controller;


import com.demo.dto.CustomerRequestDto;
import com.demo.dto.CustomerResponseDto;
import com.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "")
    public ResponseEntity<CustomerResponseDto> registerUser(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok(userService.saveUser(customerRequestDto));
    }
}
