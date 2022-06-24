package ru.amlet.security;

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
import ru.amlet.controller.BookController;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.service.AuthorService;
import ru.amlet.service.BookService;
import ru.amlet.service.GenreService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
@DisplayName("Класс BookController")
public class BookControllerTest {

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private UserDetailsService userDetailsService;
    @Autowired
    private MockMvc mockMvc;

    private static final long BOOK_ID = 1L;
    private static final String BOOK_NAME = "firstBook";
    private static final Author AUTHOR = new Author("firstAuthor");
    private static final Genre GENRE = new Genre("firstGenre");
    private static final Book BOOK = new Book(BOOK_ID, BOOK_NAME, AUTHOR, GENRE);

    @Test
    @DisplayName("Не авторизованных пользователей с /books отправляет на /login")
    public void booksPageShouldNotOkWithUser() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    @DisplayName("Доступ на /books открыт для пользователя user с ролью USER")
    public void booksPageShouldOkWithUser() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ANYROLE"})
    @DisplayName("Доступ на /books/create закрыт для пользователя с authorities отличными от ROLE_ADMIN")
    public void booksCreatePageShouldNotOkWithAuthorityRoleAny() throws Exception {
        mockMvc.perform(get("/books/create"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Доступ на /books/create открыт для пользователя с authorities ROLE_ADMIN")
    public void booksCreatePageShouldOkWithUser() throws Exception {
        mockMvc.perform(get("/books/create"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Доступ на /books/find закрыт для не залогиненного пользователя")
    public void booksFindPageShouldNotOkWithNotAuthorized() throws Exception {
        mockMvc.perform(get("/books/find"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    @DisplayName("Доступ на /books/find открыт для залогиненного пользователя")
    public void booksFindPageShouldOkWithUser() throws Exception {
        mockMvc.perform(get("/books/find"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ANYROLE"})
    @DisplayName("Доступ на /books/edit закрыт для пользователя с authorities отличными от ROLE_ADMIN")
    public void booksEditPageShouldNotOkWithAuthorityRoleAny() throws Exception {
        when(bookService.findById(BOOK_ID)).thenReturn(Optional.of(BOOK));
        mockMvc.perform(get("/books/edit?id=" + BOOK_ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Доступ на /books/edit открыт для пользователя с authorities ROLE_ADMIN")
    public void booksEditPageShouldOkWithAuthorityRoleAdmin() throws Exception {
        when(bookService.findById(BOOK_ID)).thenReturn(Optional.of(BOOK));
        mockMvc.perform(get("/books/edit?id=" + BOOK_ID))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ANYROLE"})
    @DisplayName("Доступ на /books/delete закрыт для пользователя с authorities отличными от ROLE_ADMIN")
    public void booksDeletePageShouldNotOkWithAuthorityRoleAny() throws Exception {
        when(bookService.findById(BOOK_ID)).thenReturn(Optional.of(BOOK));
        mockMvc.perform(get("/books/delete?id=" + BOOK_ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Доступ на /books/delete открыт для пользователя с authorities ROLE_ADMIN")
    public void booksDeletePageShouldOkWithAuthorityRoleAdmin() throws Exception {
        when(bookService.findById(BOOK_ID)).thenReturn(Optional.of(BOOK));
        mockMvc.perform(get("/books/delete?id=" + BOOK_ID))
                .andExpect(status().isOk());
    }

}
