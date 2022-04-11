package ru.amlet.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.service.AuthorService;
import ru.amlet.service.TransformerService;

import java.util.List;
import java.util.Optional;

@Component
@ShellComponent
public class AuthorController {

    private final AuthorService authorService;
    private final TransformerService transformerService;

    public AuthorController(AuthorService authorService, TransformerService transformerService) {
        this.authorService = authorService;
        this.transformerService = transformerService;
    }

    @ShellMethod(value = "Create new author", key = {"cna", "createNewAuthor"})
    public String createAuthor(String name) {
        Author author = authorService.createAuthor(name);
        return transformerService.authorTransform(author);
    }

    @ShellMethod(value = "Find author by id", key = {"fai", "findAuthorById"})
    public String findById(long id) {
        Optional<Author> author = authorService.findById(id);
        return author.map(transformerService::authorTransform)
                .orElse(String.format("Author id: %s not found.", id));
    }

    @ShellMethod(value = "Find author by name", key = {"fan", "findAuthorByName"})
    public String findByName(String name) {
        List<Author> authors = authorService.findByName(name);
        return transformerService.authorsTransform(authors);
    }

    @ShellMethod(value = "Find all authors", key = {"faa", "findAllAuthors"})
    public String findAll() {
        List<Author> authors = authorService.findAll();
        return transformerService.authorsTransform(authors);
    }

    @ShellMethod(value = "Update author", key = {"ua", "updateAuthor"})
    public void updateAuthor(long id, String name) {
        authorService.updateAuthor(id, name);
    }

    @ShellMethod(value = "Delete author", key = {"da", "deleteAuthor"})
    public void deleteAuthor(long id) {
        authorService.deleteAuthor(id);
    }

    @ShellMethod(value = "Count authors", key = {"cas", "countAuthors"})
    public long count() {
        return authorService.count();
    }
}
