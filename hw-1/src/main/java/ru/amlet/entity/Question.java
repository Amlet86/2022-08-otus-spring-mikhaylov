package ru.amlet.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Question {

    private int number;

    private String question;

    private List<Answer> answers;

    public Question() {
        this.answers = new LinkedList<>();
    }
}
