package ru.amlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Player;

import java.util.ResourceBundle;

@Service
public class GreetingServiceImpl implements GreetingService {

    private final BundleService bundleService;
    private final IOService ioService;
    private final String bundleKey;

    public GreetingServiceImpl(BundleService bundleService,
                               IOService ioService,
                               @Value("${bundle.key}") String bundleKey) {
        this.bundleService = bundleService;
        this.ioService = ioService;
        this.bundleKey = bundleKey;
    }

    @Override
    public Player greetingAndAcquaintance() {
        ResourceBundle resourceBundle = bundleService.getLocaleBundle();
        String greetingAndAcquaintance = (String) resourceBundle.getObject(bundleKey);
        ioService.writeString(greetingAndAcquaintance);
        String name = ioService.readString();
        return new Player(name);
    }

}
