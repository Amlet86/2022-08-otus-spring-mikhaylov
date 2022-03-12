package ru.amlet;

import org.springframework.context.annotation.*;
import ru.amlet.service.QuizServiceImpl;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class AppStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppStarter.class);
        context.getBean(QuizServiceImpl.class).conducting();
        context.close();
    }

    @Bean
    public InputStream inputStream() {
        return System.in;
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }
}
