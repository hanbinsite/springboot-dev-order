package site.hb.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import site.hb.order.utils.OrderIdGeneratorUtils;

@SpringBootTest
@Slf4j
public class OrderNoTest {


    @Test
    void testGenerate() {
        log.info("进入测试生成订单号");
        OrderIdGeneratorUtils generator = new OrderIdGeneratorUtils(2);
        for (int i = 0; i < 5; i++) {
            log.info(generator.generateOrderId());
        }

    }

}
