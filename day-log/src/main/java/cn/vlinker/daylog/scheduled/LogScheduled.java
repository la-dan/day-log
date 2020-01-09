package cn.vlinker.daylog.scheduled;


import cn.vlinker.daylog.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author ladan
 */
@Component
public class LogScheduled {

    private Logger logger = LoggerFactory.getLogger(LogScheduled.class);

    @Autowired
    private LogService logService;

    @Scheduled(cron = "0 50 17 ? * WED")
    public void update1() {
        LocalDate end = LocalDate.now();
        LocalDate begin = end.minusDays(2);
        logService.upload(begin, end);
        logger.info("现在时间为: {}", LocalDate.now());
    }

    @Scheduled(cron = "0 50 17 ? * FRI")
    public void update2() {
        LocalDate end = LocalDate.now();
        LocalDate begin = end.minusDays(1);
        logService.upload(begin, end);
        logger.info("现在时间为: {}", LocalDate.now());
    }

    @Scheduled(cron = "0 50 17 ? * SUN")
    public void update3() {
        LocalDate end = LocalDate.now();
        LocalDate begin = end.minusDays(1);
        logService.upload(begin, end);
        logger.info("现在时间为: {}", LocalDate.now());
    }

    @Scheduled(cron = "0 10 17 ? * WED")
    public void check1() {
        LocalDate end = LocalDate.now();
        LocalDate begin = end.minusDays(2);
        logService.check(begin, end, 3);
        logger.info("现在时间为: {}", LocalDate.now());
    }

    @Scheduled(cron = "0 10 17 ? * FRI")
    public void check2() {
        LocalDate end = LocalDate.now();
        LocalDate begin = end.minusDays(1);
        logService.check(begin, end, 2);
        logger.info("现在时间为: {}", LocalDate.now());
    }

}
