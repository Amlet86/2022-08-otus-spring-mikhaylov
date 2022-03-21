package ru.amlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.utility.LocaleProvider;

import java.util.ResourceBundle;

@Service
public class BundleServiceImpl implements BundleService {

    private final LocaleProvider localeProvider;
    private final String bundleName;

    public BundleServiceImpl(LocaleProvider localization,
                             @Value("${bundle.name}") String bundleName) {
        this.localeProvider = localization;
        this.bundleName = bundleName;
    }

    @Override
    public ResourceBundle getLocaleBundle() {
        return ResourceBundle.getBundle(bundleName, localeProvider.getLocale());
    }

    @Override
    public String getBundleObject(String objectName) {
        ResourceBundle resourceBundle = getLocaleBundle();
        return (String) resourceBundle.getObject(objectName);
    }
}
