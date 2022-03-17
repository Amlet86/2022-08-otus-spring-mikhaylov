package ru.amlet.utility;

import lombok.Getter;

@Getter
public enum CorrectAnswer {

    EN("Putin"),
    RU("Путин");

    private final String answer;

    CorrectAnswer(String answer) {
        this.answer = answer;
    }
}
