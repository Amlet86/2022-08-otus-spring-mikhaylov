package ru.amlet.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
public class Question {

    private int number;

    private String question;

    private Map answers;

}
