package ru.amlet.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Player {

    private String name;

    private int score;
}
