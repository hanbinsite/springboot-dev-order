package site.hb.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.hb.order.common.result.ResultUtil;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/")
@Slf4j
public class IndexController {

    @GetMapping({"/", "index"})    
    public Object index() {
        log.info("测试输出日志");
        return ResultUtil.success(1, "欢迎访问");
    }
    
}
