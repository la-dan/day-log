package cn.vlinker.daylog.controller;

import cn.vlinker.daylog.common.Result;
import cn.vlinker.daylog.common.ResultFunctionModel;
import cn.vlinker.daylog.service.AuthCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("getCode")
public class CodeController {

    @Autowired
    private AuthCodeService authCodeService;

    private final Logger logger = LoggerFactory.getLogger(CodeController.class);

    @GetMapping("")
    public Result getCode(String owner, String mac, String email) {
        return new ResultFunctionModel(() -> {
            logger.info("==================用户获取了验证码==================");
            return authCodeService.sendEmail(mac, email);
        }, owner, mac, email).execute();
    }

}
