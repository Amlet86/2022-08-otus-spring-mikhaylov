package ru.amlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import ru.amlet.service.QuizServiceImpl;

@SpringBootApplication
public class QuizStarter {

    public static void main(String[] args) {
        SpringApplication.run(QuizStarter.class, args);
    }

}
