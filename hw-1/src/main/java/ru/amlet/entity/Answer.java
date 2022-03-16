package ru.amlet.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
public class Answer {

    private final String textAnswer;

    private boolean correct;

    public Answer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public Answer(String textAnswer, boolean correct) {
        this.textAnswer = textAnswer;
        this.correct = correct;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Answer otherAnswer = (Answer) obj;
        return StringUtils.equalsIgnoreCase(this.textAnswer, otherAnswer.textAnswer);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.textAnswer != null ? this.textAnswer.hashCode() : 0);
        return hash;
    }

}
