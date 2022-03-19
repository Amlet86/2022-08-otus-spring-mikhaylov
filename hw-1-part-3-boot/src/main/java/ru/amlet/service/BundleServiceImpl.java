package ru.amlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class BundleServiceImpl implements BundleService {

    private final LocalizationService localizationService;
    private final String bundleName;

    public BundleServiceImpl(LocalizationService localization,
                             @Value("${bundle.name}") String bundleName) {
        this.localizationService = localization;
        this.bundleName = bundleName;
    }

    @Override
    public ResourceBundle getLocaleBundle() {
        return ResourceBundle.getBundle(bundleName, localizationService.getLocale());
    }
}
