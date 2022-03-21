package amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.amlet.service.LocalizationService;
import ru.amlet.service.LocalizationServiceImpl;
import ru.amlet.utility.LocaleProvider;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Имплементация класса BundleService")
public class LocalizationServiceImplTest {

    @Mock
    private LocaleProvider localeProvider;
    @Mock
    private LocaleProvider localeProvider2;

    private LocalizationService localizationService;

    private LocalizationService localizationService2;

    private final String bundleGreeting = "greeting.acquaintance";

    @BeforeEach
    void setUp() {
        String bundleName = "messages";
        localizationService = new LocalizationServiceImpl(localeProvider, bundleName);
        localizationService2 = new LocalizationServiceImpl(localeProvider2, bundleName);
    }

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

    @Test
    @DisplayName("getLocalizedMessage должен возвращать разные значения для Locale.Ru и Locale.En")
    void getLocalizedMessageShouldReturnDifferentValuesForEnAndRu() {
        given(localeProvider.getLocale())
                .willReturn(new Locale("ru"));
        given(localeProvider2.getLocale())
                .willReturn(Locale.ENGLISH);
        assertNotEquals(localizationService.getLocalizedMessage(bundleGreeting),
                localizationService2.getLocalizedMessage(bundleGreeting));
    }

}
