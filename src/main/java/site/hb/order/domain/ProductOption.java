package site.hb.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 商品规格选项模板实体
 */
@Data
public class ProductOption {

    /**
     * 主键id
     */
    private String id;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 规格选项组名称
     * 例如：温度、糖度、配料等
     */
    private String optionGroupName;

    /**
     * 规格选项名称
     * 例如：常温、热、冰、少糖、加珍珠等
     */
    private String optionName;

    /**
     * 规格选项加价
     */
    private BigDecimal additionalPrice;

    /**
     * 是否默认选中：0-否，1-是
     */
    private Integer isDefault;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}