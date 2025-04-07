package site.hb.order.service.impl;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import site.hb.order.service.OrderService;
import site.hb.order.utils.SnowflakeIdGenerator;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private SnowflakeIdGenerator snowflakeIdGenerator;


    @Override
    public Boolean cancleOrder(String orderNo) {
        log.info("进入用户取消订单操作，订单编号: {}", orderNo);
        return Boolean.TRUE;
    }

    /**
     * 返回订单号
     */
    @Override
    public String createOrder() {
        return String.valueOf(snowflakeIdGenerator.nextId());
    }

}
