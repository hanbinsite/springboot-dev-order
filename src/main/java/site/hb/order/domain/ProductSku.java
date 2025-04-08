package site.hb.order.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductSku {
    
    /**
     * SKU ID
     */
    private String id;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * SKU编码
     */
    private String skuCode;

    /**
     * 规格组合（JSON格式，如：[{"规格组ID":"规格值ID"},...]）
     */
    private String specCombination;

    /**
     * SKU价格
     */
    private BigDecimal price;

    /**
     * SKU库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer salesVolume;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}