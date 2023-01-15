package com.test.thymeleafcontroller.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @NotEmpty(message = "Customer name is required.")
    @Size(min = 4, max = 50, message = "Customer name should be between 4 to 50 characters.")
    private String name;

    @NotEmpty(message = "Customer number is required.")
    @Size(min = 2, max = 4, message = "Customer number should be between 2 to 4 characters.")
    private String number;

    @NotEmpty(message = "Customer email is required.")
    private String email;

}
