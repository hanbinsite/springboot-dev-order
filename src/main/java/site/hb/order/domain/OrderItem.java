package site.hb.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 订单商品关联实体
 */
@Data
public class OrderItem {

    /**
     * 主键id
     */
    private String id;

    /**
     * 订单id，关联order表
     */
    private String orderId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品SKU ID
     */
    private String skuId;

    /**
     * 商品名称（冗余，便于查询展示）
     */
    private String productName;

    /**
     * 商品主图（冗余，便于查询展示）
     */
    private String productImage;

    /**
     * SKU规格信息（JSON格式，如：[{"规格组名称":"规格值名称"},...]）
     */
    private String specifications;

    /**
     * 商品单价（SKU价格）
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 小计金额（单价 * 数量）
     */
    private BigDecimal subtotalAmount;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}