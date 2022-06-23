package ru.amlet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.amlet.dto.AuthorDto;
import ru.amlet.entity.Author;
import ru.amlet.exception.AuthorException;
import ru.amlet.service.AuthorService;

import javax.validation.Valid;
import java.util.List;

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
                             BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "authors/createAuthor";
        }
        Author author = authorDto.toDomainObject();
        authorService.createAuthor(author.getName(),authentication);
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
        Author author = authorService.findById(id).orElseThrow(AuthorException::new);
        model.addAttribute("author", author);
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
        Author author = authorService.findById(id).orElseThrow(AuthorException::new);
        model.addAttribute("author", author);
        return "authors/deleteAuthor";
    }

    @PostMapping("/authors/delete")
    public String deleteAuthor(@RequestParam("id") long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

}
