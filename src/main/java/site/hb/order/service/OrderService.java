package site.hb.order.service;

/**
 * 订单处理
 */
public interface OrderService {


    /**
     * 取消订单
     * @param orderNo 订单编号
     * @return Boolean
     */
    Boolean cancleOrder(String orderNo);


}
