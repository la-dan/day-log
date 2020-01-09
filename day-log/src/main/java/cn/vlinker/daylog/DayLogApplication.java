package cn.vlinker.daylog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DayLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DayLogApplication.class, args);
    }

}
