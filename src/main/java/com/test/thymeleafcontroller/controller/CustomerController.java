package com.test.thymeleafcontroller.controller;

import com.test.thymeleafcontroller.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final List<Customer> CUSTOMERS = new ArrayList<>();

    @GetMapping
    public String getCustomerView() {
        return "customers";
    }

}
