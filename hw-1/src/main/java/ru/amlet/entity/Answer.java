package ru.amlet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {

    private String textAnswer;

    private boolean correct;

    public Answer(String textAnswer, boolean correct) {
        this.textAnswer = textAnswer;
        this.correct = correct;
    }
}
