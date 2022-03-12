package ru.amlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.amlet.service.QuizServiceImpl;

import java.io.InputStream;
import java.io.PrintStream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		ctx.getBean(QuizServiceImpl.class).conducting();
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
