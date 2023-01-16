package com.test.thymeleafcontroller.controller;

import com.test.thymeleafcontroller.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@Import(SecurityConfig.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerController customerController;

    //To check if application context starts or not
    @Test
    public void contextLoads() {
        assertThat(customerController).isNotNull();
    }

    //To check if anonymous user can access customers page or not
    @Test
    public void shouldAllowAccessForAnonymousUser() throws Exception {
        this.mockMvc
                .perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers"))
                .andExpect(model().attributeExists("customer"))  //Check if customer attribute exists in the model or not
                .andExpect(model().attributeExists("customers"));
    }

    //To test createCustomer method
    @Test
    public void shouldCreateNewCustomer() throws Exception {
        this.mockMvc
                .perform(post("/customers")
                    .param("name", "mehedi")
                    .param("number", "C01")
                    .param("email",  "mehedi@gmail.com")
                    .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("location", "/customers"));
    }

}
