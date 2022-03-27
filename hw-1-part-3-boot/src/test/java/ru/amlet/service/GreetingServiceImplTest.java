package ru.amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.amlet.entity.Player;
import ru.amlet.service.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Имплементация класса GreetingService")
public class GreetingServiceImplTest {

    @Mock
    private LocalizationService bundleService;
    @Mock
    private IOService ioService;

    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        String bundleGreeting = "greeting.acquaintance";
        greetingService = new GreetingServiceImpl(bundleService, ioService, bundleGreeting);
    }

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
