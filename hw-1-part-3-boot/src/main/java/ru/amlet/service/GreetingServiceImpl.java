package ru.amlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Player;

@Service
public class GreetingServiceImpl implements GreetingService {

    private final BundleService bundleService;
    private final IOService ioService;
    private final String bundleGreeting;

    public GreetingServiceImpl(BundleService bundleService,
                               IOService ioService,
                               @Value("${bundle.greeting}") String bundleGreeting) {
        this.bundleService = bundleService;
        this.ioService = ioService;
        this.bundleGreeting = bundleGreeting;
    }

    @Override
    public Player greetingAndAcquaintance() {
        String greetingAndAcquaintance = bundleService.getBundleObject(bundleGreeting);
        ioService.writeString(greetingAndAcquaintance);
        String name = ioService.readString();
        return new Player(name);
    }

}
