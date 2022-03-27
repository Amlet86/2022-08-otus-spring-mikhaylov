package ru.amlet.utility;

import lombok.Getter;

import java.util.Locale;

@Getter
public class LocaleProviderImpl implements LocaleProvider {

    private final Locale locale;

    public LocaleProviderImpl(Locale locale) {
        this.locale = locale;
    }

}
