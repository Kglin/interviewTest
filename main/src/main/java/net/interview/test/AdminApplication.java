package net.interview.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */

@SpringBootApplication
@ComponentScan("net.interview")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
