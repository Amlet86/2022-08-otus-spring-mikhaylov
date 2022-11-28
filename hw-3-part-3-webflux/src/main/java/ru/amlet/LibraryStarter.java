package ru.amlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.amlet.changelog.DatabaseChangelog;

@SpringBootApplication
public class LibraryStarter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LibraryStarter.class);
        DatabaseChangelog dbInitializer = context.getBean(DatabaseChangelog.class);
        dbInitializer.insertEntities();
    }
}
