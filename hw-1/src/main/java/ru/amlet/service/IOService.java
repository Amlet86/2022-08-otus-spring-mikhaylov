package ru.amlet.service;

import ru.amlet.entity.Player;
import ru.amlet.entity.Question;

public interface IOService {

    void printQuestion(Question question);

    String readAnswer();

    void printResult(Player player, boolean isWin);

}
