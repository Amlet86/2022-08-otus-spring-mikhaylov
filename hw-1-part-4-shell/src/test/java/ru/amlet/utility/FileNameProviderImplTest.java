package ru.amlet.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Имплементация класса FileNameProvider")
public class FileNameProviderImplTest {

    @MockBean
    private LocaleProvider localeProvider;
    @Autowired
    private FileNameProvider fileNameProvider;

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
