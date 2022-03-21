package amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.amlet.service.BundleServiceImpl;
import ru.amlet.utility.LocaleProvider;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Имплементация класса BundleService")
public class BundleServiceImplTest {

    @Mock
    private LocaleProvider localeProvider;

    private BundleServiceImpl bundleService;

    private final String bundleGreeting = "greeting.acquaintance";

    @BeforeEach
    void setUp() {
        String bundleName = "messages";
        bundleService = new BundleServiceImpl(localeProvider, bundleName);
    }

    @Test
    @DisplayName("метод getLocaleBundle, для en Locale, находит bundle")
    void getLocaleEnBundleShouldReturnNotNullObject() {
        given(localeProvider.getLocale())
                .willReturn(Locale.ENGLISH);
        assertNotNull(bundleService.getLocaleBundle());
    }

    @Test
    @DisplayName("метод getLocaleBundle, для ru Locale, находит bundle")
    void getLocaleRuBundleShouldReturnNotNullObject() {
        given(localeProvider.getLocale())
                .willReturn(new Locale("ru"));
        assertNotNull(bundleService.getLocaleBundle());
    }

    @Test
    @DisplayName("метод getLocaleBundle возвращает PropertyResourceBundle")
    void getLocaleEnBundleShouldReturnResourceBundle() {
        given(localeProvider.getLocale())
                .willReturn(Locale.ENGLISH);
        assertEquals(PropertyResourceBundle.class, bundleService.getLocaleBundle().getClass());
    }

    @Test
    @DisplayName("ResourceBundle Locale.En должен содержать bundleGreeting=greeting.acquaintance")
    void resourceEnBundleShouldContainsBundleGreeting() {
        given(localeProvider.getLocale())
                .willReturn(Locale.ENGLISH);
        ResourceBundle resourceBundle = bundleService.getLocaleBundle();
        assertNotNull(resourceBundle.getObject(bundleGreeting));
    }

    @Test
    @DisplayName("ResourceBundle Locale.Ru должен содержать bundleGreeting=greeting.acquaintance")
    void resourceRuBundleShouldContainsBundleGreeting() {
        given(localeProvider.getLocale())
                .willReturn(new Locale("ru"));
        ResourceBundle resourceBundle = bundleService.getLocaleBundle();
        assertNotNull(resourceBundle.getObject(bundleGreeting));
    }
}
