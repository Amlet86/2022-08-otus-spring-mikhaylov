package ru.amlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.amlet.service.QuizServiceImpl;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

@SpringBootApplication
public class QuizStarter {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(QuizStarter.class, args);
        ctx.getBean(QuizServiceImpl.class).conducting();
    }

}
