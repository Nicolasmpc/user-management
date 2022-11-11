package com.nicolasmpc.usermanagement.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolasmpc.usermanagement.Config.SecurityConfig;
import com.nicolasmpc.usermanagement.Model.User;
import com.nicolasmpc.usermanagement.Service.TokenService;
import com.nicolasmpc.usermanagement.Service.UserService;

@WebMvcTest(UserController.class)
@Import({ SecurityConfig.class, TokenService.class })
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    void testIfUserIsCreated() throws Exception {
        User user = new User();
        user.setName("teste nicolas");
        user.setBirthDate(LocalDate.parse("1997-11-12"));

        String json = objectMapper.writeValueAsString(user);

        this.mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void testListUsers() throws Exception {
        this.mvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testIfUpdateIsSucceful() throws Exception{
        User user = new User();
        user.setName("teste nicolas 123123123123");
        user.setBirthDate(LocalDate.parse("1997-11-12"));

        String json = objectMapper.writeValueAsString(user);

        this.mvc.perform(put("/users/1")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testIfGetUserByIdIsSucceful() throws Exception{
        this.mvc.perform(get("/users/1")).andExpect(status().isOk());
    }
}
