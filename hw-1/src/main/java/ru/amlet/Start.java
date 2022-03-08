package ru.amlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.amlet.dto.Question;
import ru.amlet.service.QuestionService;

import java.util.List;

public class Start {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        List<Question> questions = service.getQuestions();
        questions.forEach(question -> System.out.println(question.getQuestion()));
        context.close();
    }
}
