package cn.vlinker.daylog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

/**
 * @author ladan
 */
@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public boolean sendAttachmentMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            //执行发送
            javaMailSender.send(simpleMailMessage);
            saveToSend(javaMailSender, simpleMailMessage);
        } catch (Exception e) {
            logger.error("发送邮件异常", e);
            return false;
        }
        return true;
    }

    public void saveToSend(JavaMailSenderImpl javaMailSender, SimpleMailMessage simpleMailMessage) throws Exception {
        Store store = javaMailSender.getSession().getStore("imap");
        store.connect(host, username, password);
        Folder folder = store.getFolder("Sent Messages");
        folder.open(Folder.READ_WRITE);
        MimeMailMessage mimeMailMessage = new MimeMailMessage(javaMailSender.createMimeMessage());
        simpleMailMessage.copyTo(mimeMailMessage);
        MimeMessage message = mimeMailMessage.getMimeMessage();
        message.setFlag(Flags.Flag.SEEN, true);
        folder.appendMessages(new Message[]{message});

        folder.close();
        store.close();
    }
}
