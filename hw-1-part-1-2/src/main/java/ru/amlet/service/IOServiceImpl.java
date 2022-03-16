package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final Scanner input;
    private final PrintStream output;

    public IOServiceImpl(InputStream inputStream, PrintStream outputStream) {
        this.input = new Scanner(inputStream);
        this.output = outputStream;
    }

    @Override
    public void writeString(String s) {
        output.println(s);
    }

    @Override
    public String readString() {
        return input.nextLine();
    }

}
