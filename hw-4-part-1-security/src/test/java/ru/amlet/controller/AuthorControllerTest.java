package ru.amlet.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.amlet.service.AuthorService;
import ru.amlet.service.BookService;
import ru.amlet.service.GenreService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
@DisplayName("Класс AuthorController")
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Доступ на /authors закрыт для не авторизованных пользователей")
    public void authorsPageShoulNotOkWithUser() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser
    @Test
    @DisplayName("Доступ на /authors открыт для для пользователя user с ролью USER")
    public void authorsPageShouldOkWithUser() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk());
    }
}
