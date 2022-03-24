package ru.amlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import ru.amlet.service.QuizServiceImpl;

@ShellComponent
@SpringBootApplication
public class QuizStarter {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(QuizStarter.class, args);
        ctx.getBean(QuizServiceImpl.class).conducting();
    }

}
