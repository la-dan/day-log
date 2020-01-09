package cn.vlinker.daylog;

import cn.vlinker.daylog.service.LogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DayLogApplicationTests {

    @Autowired
    private LogService service;

    @Test
    void contextLoads() {
    }

}
