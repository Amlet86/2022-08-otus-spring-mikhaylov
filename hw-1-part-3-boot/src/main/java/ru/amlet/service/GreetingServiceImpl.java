package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Player;

import java.util.ResourceBundle;

@Service
public class GreetingServiceImpl implements GreetingService {

    private final BundleService bundleService;
    private final IOService ioService;
    private final static String BUNDLE_KEY = "greeting.acquaintance";

    public GreetingServiceImpl(BundleService bundleService,
                               IOService ioService) {
        this.bundleService = bundleService;
        this.ioService = ioService;
    }

    @Override
    public Player greetingAndAcquaintance() {
        ResourceBundle resourceBundle = bundleService.getLocaleBundle();
        String greetingAndAcquaintance = (String) resourceBundle.getObject(BUNDLE_KEY);
        ioService.writeString(greetingAndAcquaintance);
        String name = ioService.readString();
        return new Player(name);
    }

}
