package ru.amlet.utility;

import ru.amlet.service.LocalizationService;

import java.util.HashMap;
import java.util.Locale;

public class FileNameProviderImpl implements FileNameProvider {

    private final LocalizationService localizationService;
    private final HashMap<String, String> name;

    public FileNameProviderImpl(LocalizationService localizationService,
                                HashMap<String, String> name) {
        this.localizationService = localizationService;
        this.name = name;
    }

    @Override
    public String getQuestionsFileName() {
        Locale locale = localizationService.getLocale();
        return name.get(locale.toLanguageTag());
    }
}
