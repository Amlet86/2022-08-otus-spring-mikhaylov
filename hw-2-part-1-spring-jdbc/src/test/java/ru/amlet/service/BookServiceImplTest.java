package ru.amlet.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.amlet.dao.BookDao;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Имплементация класса BookService")
public class BookServiceImplTest {

    public final static int EXPECTED_BOOKS_COUNT = 1;
    @MockBean
    AuthorService authorService;
    @MockBean
    GenreService genreService;
    @MockBean
    BookDao bookDao;
    @Autowired
    BookService bookService;

    @Test
    @DisplayName("Метод createBook создаёт книгу и возвращает её id")
    void shouldCreateBook() {
        var author = new Author(1, "authorName");
        given(authorService.findByName("authorName"))
                .willReturn(author);
        var genre = new Genre(1, "genreName");
        given(genreService.findByName("genreName"))
                .willReturn(genre);
        var book = Book.builder().name("bookName").author(author).genre(genre).build();
        given(bookDao.createBook(book))
                .willReturn(1L);
        var id = bookService.createBook("bookName", author.getName(), genre.getName());
        assertEquals(1, id);
    }

    @Test
    @DisplayName("Метод findById находит книгу по id и возвращает её")
    void shouldFindBookById() {
        var author = new Author(1, "authorName");
        var genre = new Genre(1, "genreName");
        var expectedBook = Book.builder().id(1).name("bookName").author(author).genre(genre).build();
        given(bookDao.getById(expectedBook.getId()))
                .willReturn(expectedBook);
        var actualBook = bookService.findById(1);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Метод findByName находит книгу по name и возвращает её")
    void shouldFindBookByName() {
        var author = new Author(1, "authorName");
        var genre = new Genre(1, "genreName");
        var expectedBook = Book.builder().id(1).name("bookName").author(author).genre(genre).build();
        given(bookDao.getByName(expectedBook.getName()))
                .willReturn(expectedBook);
        var actualBook = bookService.findByName("bookName");
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Метод findAll находит все книги и возвращает их")
    void shouldFindAllBooks() {
        var author = new Author(1, "authorName");
        var genre = new Genre(1, "genreName");
        var expectedBooksList = List.of(Book.builder().id(1).name("bookName").author(author).genre(genre).build());
        given(bookDao.getAll())
                .willReturn(expectedBooksList);
        var actualBooksList = bookService.findAll();
        assertThat(actualBooksList).hasSameElementsAs(expectedBooksList);
    }

    @Test
    @DisplayName("Метод count должен вернуть число книг содержащихся в БД")
    void shouldReturnCountOfBooks() {
        given(bookDao.count())
                .willReturn(EXPECTED_BOOKS_COUNT);
        var actualCount = bookService.count();
        assertEquals(EXPECTED_BOOKS_COUNT, actualCount);
    }
}
