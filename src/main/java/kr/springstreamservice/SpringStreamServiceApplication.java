package kr.springstreamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringStreamServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStreamServiceApplication.class, args);
    }

}
