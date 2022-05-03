package ru.amlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.amlet.dto.AuthorDto;
import ru.amlet.entity.Author;
import ru.amlet.exception.AuthorException;
import ru.amlet.service.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String findAll(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors/authors";
    }

    @GetMapping("/authors/create")
    public String createPage() {
        return "authors/createAuthor";
    }

    @Validated
    @PostMapping("/authors/create")
    public String createAuthor(@Valid @ModelAttribute("author") AuthorDto authorDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "authors/createAuthor";
        }
        Author author = authorDto.toDomainObject();
        authorService.createAuthor(author.getName());
        return "redirect:/authors";
    }

    @GetMapping("authors/find")
    public String findByName(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Author> authors = authorService.findByName(name);
        model.addAttribute("authors", authors);
        return "authors/findAuthor";
    }

    @GetMapping("/authors/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        Optional<Author> author = Optional.ofNullable(authorService.findById(id).orElseThrow(AuthorException::new));
        model.addAttribute("author", author.get());
        return "authors/editAuthor";
    }

    @Validated
    @PostMapping("/authors/edit")
    public String saveAuthor(@Valid @ModelAttribute("author") AuthorDto authorDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "authors/editAuthor";
        }
        Author author = authorDto.toDomainObject();
        authorService.updateAuthor(author.getId(), author.getName());
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete")
    public String deletePage(@RequestParam("id") int id, Model model) {
        Optional<Author> author = Optional.ofNullable(authorService.findById(id).orElseThrow(AuthorException::new));
        model.addAttribute("author", author.get());
        return "authors/deleteAuthor";
    }

    @PostMapping("/authors/delete")
    public String deleteAuthor(@RequestParam("id") long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

    @GetMapping("/authors/count")
    public String count(Model model) {
        long count = authorService.count();
        model.addAttribute("countAuthors", count);
        return "authors/countAuthors";
    }
}
