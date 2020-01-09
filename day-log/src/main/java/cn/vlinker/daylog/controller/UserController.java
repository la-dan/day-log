package cn.vlinker.daylog.controller;

import cn.vlinker.daylog.common.Result;
import cn.vlinker.daylog.common.ResultFunctionModel;
import cn.vlinker.daylog.model.User;
import cn.vlinker.daylog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public Result add(@RequestBody User user, @RequestParam String auth) {
        return new ResultFunctionModel(() -> {
            logger.info("==================用户在进行注册==================");
            return userService.add(user, auth);
        }, user, auth).execute();
    }

    @GetMapping("get")
    public Result get(String mac) {
        return new ResultFunctionModel(() -> {
            logger.info("==================用户正在使用客户端==================");
            return userService.get(mac);
        }, mac).execute();
    }
}
