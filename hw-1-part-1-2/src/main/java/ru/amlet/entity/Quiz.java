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

    public Quiz(Player player, int lowestPassingScore) {
        this.player = player;
        this.lowestPassingScore = lowestPassingScore;
    }

    public boolean isWin() {
        return score > lowestPassingScore;
    }

    public void incrementScore(int increment) {
        score += increment;
    }

}
