package ru.amlet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {

    private Player player;

    private int score;

    public Test() {
        this.player = new Player();
    }

}
