package cn.vlinker.daylog.service;

import cn.vlinker.daylog.model.Log;
import cn.vlinker.daylog.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author ladan
 */
@Service
public class LogService {

    @Value("${spring.mail.to}")
    private String toEmail;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private EmailService emailService;

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public Log save(Log log) {
        return logRepository.save(log);
    }

    public void upload(LocalDate begin, LocalDate end) {
        List<Log> logs = logRepository.findByDay(begin, end);
        if (logs.size() < 1) {
            return;
        }
        StringBuffer data = new StringBuffer();
        logs.forEach(item -> {
            data.append("日期：" + item.getDay().format(FORMATTER) + "\n");
            data.append("姓名：" + item.getName() + "\n");
            data.append("项目名称：" + item.getProject() + "\n");
            data.append("工作内容：\n");
            data.append(item.getWork() + "\n");
            data.append("\n");
        });
        emailService.sendAttachmentMail(toEmail, "本次周期日报", data.toString());
    }

    public void check(LocalDate begin, LocalDate end, int i) {
        List<Map<String, Object>> list = logRepository.groupByUserCountName(begin, end);
        list.forEach(item -> {
            String email = item.get("email").toString();
            int count = item.get("total") != null ? Integer.parseInt(item.get("total").toString()) : 0;
            if (count < i) {
                emailService.sendAttachmentMail(email, "日报提交截止提醒", "本次周期还缺"
                        + (i - count) + "篇日报未提交,日报提交截止时间于"
                        + LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50)).format(dateTimeFormatter) + ",请在时间截止前提交.");
            }
        });
    }

    public String upload(int type) {
        LocalDate end;
        LocalDate begin;
        switch (type) {
            case 1:
                end = LocalDate.now();
                begin = end.minusDays(2);
                upload(begin, end);
                return "SUCCESS";
            case 2:
                end = LocalDate.now();
                begin = end.minusDays(1);
                upload(begin, end);
                return "SUCCESS";
            default:
                return "";
        }
    }

    public List<Map<String, Object>> getDate(String name) {
        return logRepository.findByName(name);
    }
}
