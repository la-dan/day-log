package cn.vlinker.daylog.service;

import cn.vlinker.daylog.model.AuthCode;
import cn.vlinker.daylog.repository.AuthCodeRepository;
import cn.vlinker.daylog.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthCodeService {

    @Autowired
    private AuthCodeRepository authCodeRepository;

    @Autowired
    private EmailService emailService;

    public AuthCode save(AuthCode code) {
        return authCodeRepository.save(code);
    }

    public Object sendEmail(String mac, String email) {
        String code = CodeUtil.randomGenerate(6);
        AuthCode authCode = new AuthCode();
        authCode.setCode(code);
        authCode.setMac(mac);
        authCode = this.save(authCode);
        emailService.sendAttachmentMail(email, "验证码", code);
        return authCode;
    }

}
