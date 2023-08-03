package pl.maks.carrental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarRentalApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Start the application");
        SpringApplication.run(CarRentalApplication.class, args);
        LOGGER.info("Application run");
    }

}
