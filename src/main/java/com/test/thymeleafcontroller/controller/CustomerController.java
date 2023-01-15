package com.test.thymeleafcontroller.controller;

import com.test.thymeleafcontroller.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final List<Customer> CUSTOMERS = new ArrayList<>();

    @GetMapping
    public String getCustomerView(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers", CUSTOMERS);
        return "customers";
    }

    @PostMapping
    public String createCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            model.addAttribute("customer", customer);
            return "customers";
        }

        CUSTOMERS.add(customer);
        redirectAttributes.addFlashAttribute("msg", "Customer added successfully.");
        return "redirect:/customers";
    }

}
