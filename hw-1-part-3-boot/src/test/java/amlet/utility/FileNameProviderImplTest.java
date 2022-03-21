package amlet.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.amlet.utility.LocaleProvider;
import ru.amlet.utility.FileNameProvider;
import ru.amlet.utility.FileNameProviderImpl;

import java.util.HashMap;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Имплементация класса FileNameProvider")
public class FileNameProviderImplTest {

    @Mock
    private LocaleProvider localeProvider;

    private FileNameProvider fileNameProvider;

    @BeforeEach
    void setUp() {
        HashMap<String, String> name = new HashMap<>();
        name.put("en", "qa_en.csv");
        name.put("ru", "qa_ru.csv");
        fileNameProvider = new FileNameProviderImpl(localeProvider, name);
    }

    @Test
    @DisplayName("метод getQuestionsFileName возвращает не null для en Locale")
    void getQuestionsFileNameEnShouldReturnNotNull() {
        given(localeProvider.getLocale())
                .willReturn(Locale.ENGLISH);
        assertNotNull(fileNameProvider.getQuestionsFileName());
    }

    @Test
    @DisplayName("метод getQuestionsFileName возвращает не null для ru Locale")
    void getQuestionsFileNameRuShouldReturnNotNull() {
        given(localeProvider.getLocale())
                .willReturn(new Locale("ru"));
        assertNotNull(fileNameProvider.getQuestionsFileName());
    }

    @Test
    @DisplayName("метод getQuestionsFileName возвращает объект String для en Locale")
    void getQuestionsFileNameEnShouldReturnStringClass() {
        given(localeProvider.getLocale())
                .willReturn(Locale.ENGLISH);
        assertEquals(String.class, fileNameProvider.getQuestionsFileName().getClass());
    }

    @Test
    @DisplayName("метод getQuestionsFileName возвращает объект String для ru Locale")
    void getQuestionsFileNameRuShouldReturnStringClass() {
        given(localeProvider.getLocale())
                .willReturn(new Locale("ru"));
        assertEquals(String.class, fileNameProvider.getQuestionsFileName().getClass());
    }

}
