package ru.amlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/books_page")
    public String booksPage() {
        return "books";
    }

}
