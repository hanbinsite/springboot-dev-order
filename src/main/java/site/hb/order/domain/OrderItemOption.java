package site.hb.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 订单商品规格选项实体
 */
@Data
public class OrderItemOption {

    /**
     * 主键id
     */
    private String id;

    /**
     * 关联的订单商品id
     */
    private String orderItemId;

    /**
     * 规格选项名称
     * 例如：温度、糖度、配料等
     */
    private String optionName;

    /**
     * 规格选项值
     * 例如：常温、少糖、加珍珠等
     */
    private String optionValue;

    /**
     * 规格选项加价
     * 某些选项可能会增加价格，如加料
     */
    private BigDecimal additionalPrice;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}