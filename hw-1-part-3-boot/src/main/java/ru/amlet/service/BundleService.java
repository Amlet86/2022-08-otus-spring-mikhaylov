package ru.amlet.service;

import java.util.ResourceBundle;

public interface BundleService {

    ResourceBundle getLocaleBundle();

    String getBundleObject(String objectName);
}
