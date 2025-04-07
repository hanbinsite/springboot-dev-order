package site.hb.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import site.hb.order.common.result.Result;
import site.hb.order.common.result.ResultUtil;
import site.hb.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;


    public Result<Object> createOrder() {
        return ResultUtil.success(orderService.createOrder());
    }




}
