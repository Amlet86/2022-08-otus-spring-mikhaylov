package ru.amlet.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Имплементация класса BookService")
public class BookServiceImplTest {

    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @Test
    @DisplayName("Метод findById находит книгу по id и возвращает её")
    void shouldFindBookById() {
        var author = new Author(1, "authorName");
        var genre = new Genre(1, "genreName");
        var expectedBook = Book.builder().id(1).name("bookName").author(author).genre(genre).build();
        given(bookRepository.findById(expectedBook.getId()))
                .willReturn(Optional.of(expectedBook));
        var actualBook = bookService.findById(1);
        assertEquals(expectedBook, actualBook.get());
    }

    @Test
    @DisplayName("Метод findByName находит книгу по name и возвращает её")
    void shouldFindBookByName() {
        var author = new Author(1, "authorName");
        var genre = new Genre(1, "genreName");
        var expectedBook = Book.builder().id(1).name("bookName").author(author).genre(genre).build();
        given(bookRepository.findByName(expectedBook.getName()))
                .willReturn(List.of(expectedBook));
        var actualBook = bookService.findByName("bookName");
        assertThat(actualBook).contains(expectedBook);
    }

    @Test
    @DisplayName("Метод findAll находит все книги и возвращает их")
    void shouldFindAllBooks() {
        var author = new Author(1, "authorName");
        var genre = new Genre(1, "genreName");
        var expectedBooksList = List.of(Book.builder().id(1).name("bookName").author(author).genre(genre).build());
        given(bookRepository.findAll())
                .willReturn(expectedBooksList);
        var actualBooksList = bookService.findAll();
        assertThat(actualBooksList).hasSameElementsAs(expectedBooksList);
    }

    @Test
    @DisplayName("Метод count должен вернуть число книг содержащихся в БД")
    void shouldReturnCountOfBooks() {
        given(bookRepository.count())
                .willReturn(1L);
        var actualCount = bookService.count();
        assertEquals(1L, actualCount);
    }
}
