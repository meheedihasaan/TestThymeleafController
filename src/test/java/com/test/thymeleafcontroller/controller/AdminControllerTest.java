package com.test.thymeleafcontroller.controller;

import com.test.thymeleafcontroller.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Import(SecurityConfig.class )
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminController adminController;

    //To check if application context starts or not
    @Test
    public void contextLoads() {
        assertThat(adminController).isNotNull();
    }

    //To check if anonymous user gets redirected to login page or not while accessing '/admin' url
    @Test
    public void shouldRedirectAnonymousUserToLogin() throws Exception {
        this.mockMvc
                .perform(get("/admin"))
                .andExpect(status().is3xxRedirection());
    }

    //To check if authenticated user can access '/admin' url or not
    @Test
    @WithMockUser(username = "user")
    public void shouldAllowAccessForAuthenticatedUser() throws Exception {
        this.mockMvc
                .perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeExists("msg"));
    }

    //An alternative to @WithMockUser to check if authenticated user can access '/admin' url or not
    @Test
    public void shouldAllowAccessForAuthenticatedUserAlternative() throws Exception {
        this.mockMvc
                .perform(get("/admin")
                    .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeExists("msg"));
    }

}
