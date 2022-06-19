package ru.amlet.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
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

@WebMvcTest(BookController.class)
@ContextConfiguration(classes = BookController.class)
@DisplayName("Класс BookController")
public class BookControllerTest {

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @Autowired
    private MockMvc mockMvc;

    private static final long BOOK_ID = 1L;
    private static final String BOOK_NAME = "firstBook";
    private static final Author AUTHOR = new Author("firstAuthor");
    private static final Genre GENRE = new Genre("firstGenre");
    private static final Book BOOK = new Book(BOOK_ID, BOOK_NAME, AUTHOR, GENRE);

    @Test
    @DisplayName("Доступ на /books закрыт для не авторизованных пользователей")
    public void booksPageShouldNotOkWithUser() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser
    @Test
    @DisplayName("Доступ на /books открыт для пользователя user с ролью USER")
    public void booksPageShouldOkWithUser() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    @DisplayName("Доступ на /books/create открыт для пользователя user с ролью USER")
    public void booksCreatePageShouldOkWithUser() throws Exception {
        mockMvc.perform(get("/books/create"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    @DisplayName("Доступ на /books/find открыт для пользователя user с ролью USER")
    public void booksFindPageShouldOkWithUser() throws Exception {
        mockMvc.perform(get("/books/find"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    @DisplayName("Доступ на /books/edit открыт для пользователя user с ролью USER")
    public void booksEditPageShouldOkWithUser() throws Exception {
        when(bookService.findById(BOOK_ID)).thenReturn(Optional.of(BOOK));
        mockMvc.perform(get("/books/edit?id=" + BOOK_ID))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    @DisplayName("Доступ на /books/delete открыт для пользователя user с ролью USER")
    public void booksDeletePageShouldOkWithUser() throws Exception {
        when(bookService.findById(BOOK_ID)).thenReturn(Optional.of(BOOK));
        mockMvc.perform(get("/books/delete?id=" + BOOK_ID))
                .andExpect(status().isOk());
    }

}
