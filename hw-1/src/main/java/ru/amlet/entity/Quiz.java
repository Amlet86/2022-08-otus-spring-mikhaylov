package ru.amlet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quiz {

    private final int lowestPassingScore;

    private final Player player;

    private boolean win;

    private int score;

    public Quiz(int lowestPassingScore) {
        this.lowestPassingScore = lowestPassingScore;
        this.player = new Player();
    }

    public boolean isWin() {
        return score > lowestPassingScore;
    }

}
