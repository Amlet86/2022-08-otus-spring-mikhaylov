package ru.amlet.utility;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class QuizLocalization {

    private final MessageSource messageSource;
    private final Locale locale;

    public QuizLocalization(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String propertyLocalization(String property) {
        return messageSource.getMessage(property, null, locale);
    }

}
