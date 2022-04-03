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
        given(authorService.find(author))
                .willReturn(author);
        var genre = new Genre(1,"genreName");
        given(genreService.find(genre))
                .willReturn(genre);
        var book = Book.builder().id(1).name("bookName").author(author).genre(genre).build();
        given(bookDao.createBook(book))
                .willReturn(1L);
        var id = bookService.createBook(book);
        assertEquals(1, id);
    }

    @Test
    @DisplayName("Метод find находит книгу и возвращает её")
    void shouldFindBook() {
        var author = new Author(1, "authorName");
        var genre = new Genre(1,"genreName");
        var expectedBook = Book.builder().id(1).name("bookName").author(author).genre(genre).build();
        given(bookDao.getById(expectedBook.getId()))
                .willReturn(expectedBook);
        given(bookDao.getByName(expectedBook.getName()))
                .willReturn(expectedBook);
        var actualBook = bookService.find(expectedBook);
        assertEquals(expectedBook, actualBook);
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
