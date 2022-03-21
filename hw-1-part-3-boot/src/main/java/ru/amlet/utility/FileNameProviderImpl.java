package ru.amlet.utility;

import java.util.HashMap;
import java.util.Locale;

public class FileNameProviderImpl implements FileNameProvider {

    private final LocaleProvider localeProvider;
    private final HashMap<String, String> name;

    public FileNameProviderImpl(LocaleProvider localeProvider,
                                HashMap<String, String> name) {
        this.localeProvider = localeProvider;
        this.name = name;
    }

    @Override
    public String getQuestionsFileName() {
        Locale locale = localeProvider.getLocale();
        return name.get(locale.toLanguageTag());
    }
}
