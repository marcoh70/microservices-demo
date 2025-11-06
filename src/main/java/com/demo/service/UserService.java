package com.demo.service;

import com.demo.dto.CustomerRequestDto;
import com.demo.dto.CustomerResponseDto;
import com.demo.entity.Customer;
import com.demo.exceptions.CustomerAlreadyExistException;
import com.demo.repository.CustomerRepository;
import com.demo.utils.ConvertToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerResponseDto saveUser(CustomerRequestDto customerRequestDto) {

        Customer customerdb = customerRepository.findByEmail(customerRequestDto.getEmail()).orElse(new Customer());

        if (customerdb.getId() == null) {
            Customer customer = ConvertToDto.converToCustomer(customerRequestDto);
            customer.setPassword(passwordEncoder.encode(customerRequestDto.getPassword()));
            customerdb = customerRepository.save(customer);
            return ConvertToDto.converToCustomerDto(customerdb);
        } else {
            throw new CustomerAlreadyExistException("Customer Already exist with: " + customerRequestDto.getEmail());
        }
    }
}
