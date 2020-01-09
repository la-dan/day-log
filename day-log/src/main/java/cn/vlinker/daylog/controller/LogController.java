package cn.vlinker.daylog.controller;

import cn.vlinker.daylog.common.Result;
import cn.vlinker.daylog.common.ResultFunctionModel;
import cn.vlinker.daylog.model.Log;
import cn.vlinker.daylog.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/log")
public class LogController {

    private final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @PostMapping("add")
    public Result add(@RequestBody Log log) {
        return new ResultFunctionModel(() -> {
            logger.info("==================用户上交日报==================");
            return logService.save(log);
        }, log).execute();
    }

    @GetMapping
    public Result update(int type){
        return new ResultFunctionModel(() -> {
            logger.info("==================管理员提交日报==================");
            return logService.upload(type);
        }, type).execute();
    }

    @GetMapping("getDate")
    public Result getDate(String name){
        return new ResultFunctionModel(() -> {
            logger.info("==================用户获取日志提交情况==================");
            return logService.getDate(name);
        }, name).execute();
    }
}
