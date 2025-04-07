package site.hb.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class Order {

    /**
     * 订单id
     */
    private String id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal actualAmount;

    /**
     * 折扣金额
     */
    private BigDecimal discountAmount;

    /**
     * 支付状态
     */
    private String status;

    /**
     * 支付类型
     */
    private String paymentType;

    /**
     * 三方支付订单号
     */
    private String paymentNo;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单最后更新时间
     */
    private Date updateTime;

    /**
     * 订单下单时间
     */
    private Date orderTime;

    /**
     * 订单支付时间
     */
    private Date paymentTime;

    /**
     * 订单发货时间
     */
    private Date shippingTime;

    /**
     * 订单完成时间
     */
    private Date completionTime;


}
