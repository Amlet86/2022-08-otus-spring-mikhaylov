package ru.amlet.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {

    @CsvBindByName(column = "number")
    private int number;

    @CsvBindByName(column = "question")
    private String question;

    @CsvBindByName(column = "first answer")
    private String firstAnswer;

    @CsvBindByName(column = "is first correct")
    private boolean isFirstCorrect;

    @CsvBindByName(column = "second answer")
    private String secondAnswer;

    @CsvBindByName(column = "is second correct")
    private boolean isSecondCorrect;

    @CsvBindByName(column = "third answer")
    private String thirdAnswer;

    @CsvBindByName(column = "is third correct")
    private boolean isThirdCorrect;

}
