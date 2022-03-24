package ru.amlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.utility.LocaleProvider;

import java.util.ResourceBundle;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final LocaleProvider localeProvider;
    private final String bundleName;

    public LocalizationServiceImpl(LocaleProvider localization,
                                   @Value("${bundle.name}") String bundleName) {
        this.localeProvider = localization;
        this.bundleName = bundleName;
    }

    @Override
    public String getLocalizedMessage(String messageKey) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName, localeProvider.getLocale());
        return (String) resourceBundle.getObject(messageKey);
    }
}
