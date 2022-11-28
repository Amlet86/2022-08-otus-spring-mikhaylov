package ru.amlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenreController {

    @GetMapping("/genres_page")
    public String genresPage() {
        return "genres";
    }

}
