package cn.vlinker.logclient;

import cn.vlinker.logclient.panel.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogClientApplication.class, args);
        Main.main(new String[0]);
    }

}
