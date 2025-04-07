package site.hb.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.hb.order.common.result.ResultUtil;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping({"/", "index"})    
    public Object index() {
        return ResultUtil.success(1, "欢迎访问");
    }
    
}
