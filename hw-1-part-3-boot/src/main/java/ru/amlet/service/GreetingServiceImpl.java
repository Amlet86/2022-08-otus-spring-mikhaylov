package ru.amlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Player;

@Service
public class GreetingServiceImpl implements GreetingService {

    private final LocalizationService localizationService;
    private final IOService ioService;
    private final String bundleGreeting;

    public GreetingServiceImpl(LocalizationService localizationService,
                               IOService ioService,
                               @Value("${bundle.greeting}") String bundleGreeting) {
        this.localizationService = localizationService;
        this.ioService = ioService;
        this.bundleGreeting = bundleGreeting;
    }

    @Override
    public Player greetingAndAcquaintance() {
        String greetingAndAcquaintance = localizationService.getLocalizedMessage(bundleGreeting);
        ioService.writeString(greetingAndAcquaintance);
        String name = ioService.readString();
        return new Player(name);
    }

}
