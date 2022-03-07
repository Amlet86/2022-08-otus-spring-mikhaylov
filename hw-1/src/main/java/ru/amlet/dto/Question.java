package ru.amlet.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {

    @CsvBindByName(column = "question")
    private String question;

    @CsvBindByName(column = "first answer")
    private String firstAnswer;

    @CsvBindByName(column = "second answer")
    private String secondAnswer;

    @CsvBindByName(column = "third answer")
    private String thirdAnswer;

}
