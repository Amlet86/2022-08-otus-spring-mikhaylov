package ru.amlet.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.amlet.entity.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Имплементация класса GreetingService")
public class GreetingServiceImplTest {

    @MockBean
    private LocalizationService bundleService;
    @MockBean
    private IOService ioService;
    @Autowired
    private GreetingService greetingService;

    @Test
    @DisplayName("метод greetingAndAcquaintance возвращает не null")
    void greetingAndAcquaintanceShouldReturnNotNullObject() {
        given(bundleService.getLocalizedMessage("greeting.acquaintance"))
                .willReturn("what is your name?");
        given(ioService.readString())
                .willReturn("Amlet");
        assertNotNull(greetingService.greetingAndAcquaintance());
    }

    @Test
    @DisplayName("метод greetingAndAcquaintance возвращает объект класса Player")
    void greetingAndAcquaintanceShouldReturnPlayerClass() {
        given(bundleService.getLocalizedMessage("greeting.acquaintance"))
                .willReturn("what is your name?");
        given(ioService.readString())
                .willReturn("Amlet");
        assertEquals(Player.class, greetingService.greetingAndAcquaintance().getClass());
    }

    @Test
    @DisplayName("метод greetingAndAcquaintance возвращает Player с именем")
    void greetingAndAcquaintanceShouldReturnPlayerWithName() {
        given(bundleService.getLocalizedMessage("greeting.acquaintance"))
                .willReturn("what is your name?");
        given(ioService.readString())
                .willReturn("Amlet");
        Player player = greetingService.greetingAndAcquaintance();
        assertNotNull(player.getName());
    }

}
