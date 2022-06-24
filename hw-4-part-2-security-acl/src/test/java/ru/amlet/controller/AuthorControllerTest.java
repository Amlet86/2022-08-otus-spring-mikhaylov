package ru.amlet.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.amlet.service.AuthorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorController.class)
@DisplayName("Класс AuthorController")
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;
    @MockBean
    private UserDetailsService userDetailsService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Не авторизованных пользователей с /authors отправляет на /login")
    public void authorsPageShouldNotOkWithAnyBody() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    @DisplayName("Доступ на /authors открыт для авторизованного пользователя")
    public void authorsPageShouldOkWithUser() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ANYROLE"})
    @DisplayName("Доступ на /authors/create закрыт для пользователя с authorities отличными от ROLE_ADMIN")
    public void authorsCreatePageShouldNotOkWithAuthorityRoleAny() throws Exception {
        mockMvc.perform(get("/authors/create"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Доступ на /authors/create открыт для пользователя с authorities ROLE_ADMIN")
    public void authorsCreatePageShouldOkWithAuthorityRoleAdmin() throws Exception {
        mockMvc.perform(get("/authors/create"))
                .andExpect(status().isOk());
    }

}
