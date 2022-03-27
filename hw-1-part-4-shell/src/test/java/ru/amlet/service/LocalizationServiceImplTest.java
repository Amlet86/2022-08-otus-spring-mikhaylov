package ru.amlet.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.amlet.utility.LocaleProvider;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Имплементация класса BundleService")
public class LocalizationServiceImplTest {

    @MockBean
    private LocaleProvider localeProvider;
    @Autowired
    private LocalizationService localizationService;

    private final String bundleGreeting = "greeting.acquaintance";

    @Test
    @DisplayName("getLocalizedMessage должен возвращать значение для Locale.En bundleGreeting=greeting.acquaintance")
    void getLocalizedMessageShouldReturnEnValue() {
        given(localeProvider.getLocale())
                .willReturn(Locale.ENGLISH);
        assertNotNull(localizationService.getLocalizedMessage(bundleGreeting));
    }

    @Test
    @DisplayName("getLocalizedMessage должен возвращать значение для Locale.Ru bundleGreeting=greeting.acquaintance")
    void getLocalizedMessageShouldReturnRuValue() {
        given(localeProvider.getLocale())
                .willReturn(new Locale("ru"));
        assertNotNull(localizationService.getLocalizedMessage(bundleGreeting));
    }

}
