package site.hb.order.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import site.hb.order.service.OrderService;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public Boolean cancleOrder(String orderNo) {
        log.info("进入用户取消订单操作，订单编号: {}", orderNo);
        return Boolean.TRUE;
    }

}
