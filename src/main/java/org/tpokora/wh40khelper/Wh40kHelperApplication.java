package org.tpokora.wh40khelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
}
)
public class Wh40kHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(Wh40kHelperApplication.class, args);
    }
}
