package site.hb.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import site.hb.order.common.result.ResultUtil;
import site.hb.order.utils.HybridIdGenerator;
import site.hb.order.utils.SnowflakeIdGenerator;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/")
@Slf4j
public class IndexController {



    @Resource
    private HybridIdGenerator hybridIdGenerator;

    @Resource
    private SnowflakeIdGenerator snowflakeIdGenerator;




    @GetMapping({"/", "index"})    
    public Object index() {
        log.info("测试输出日志");
        return ResultUtil.success(1, "欢迎访问", snowflakeIdGenerator.nextCompositeId());
    }
    
}
