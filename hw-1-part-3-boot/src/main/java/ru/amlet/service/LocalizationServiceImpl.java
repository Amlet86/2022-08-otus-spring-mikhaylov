package ru.amlet.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Getter
public class LocalizationServiceImpl implements LocalizationService {

    private final Locale locale;

    public LocalizationServiceImpl(Locale locale) {
        this.locale = locale;
    }

}
