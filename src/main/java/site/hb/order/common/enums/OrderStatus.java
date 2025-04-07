package site.hb.order.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    /**
     * 已取消状态，包括超时取消与用户主动取消
     */
    CANCELLED("0", "已取消"),

    /**
     * 待支付状态，订单创建成功的状态
     */
    PENDING_PAYMENT("1", "待支付"),
    
    /**
     * 订单已支付，待发货状态
     */
    PAID("2", "已支付"),

    /**
     * 订单已发货，未到用户的状态
     */
    SHIPPING("3", "已发货"),

    /**
     * 订单已经送达
     */
    DELIVERED("4", "已送达"),

    /**
     * 订单已完成
     */
    COMPLETE("5", "完成")
    ;


    private String code;

    private String name;





}
