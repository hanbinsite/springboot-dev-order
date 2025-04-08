package site.hb.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 商品基本信息实体
 */
@Data
public class Product {

    /**
     * 商品id
     */
    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品主图
     */
    private String mainImage;

    /**
     * 商品图片集合，JSON格式
     */
    private String images;

    /**
     * 商品基础价格（最低价格，用于展示）
     */
    private BigDecimal basePrice;

    /**
     * 商品分类id
     */
    private String categoryId;

    /**
     * 商品状态：0-下架，1-上架
     */
    private Integer status;

    /**
     * 总库存（所有规格库存总和）
     */
    private Integer totalStock;

    /**
     * 商品销量
     */
    private Integer salesVolume;

    /**
     * 是否启用规格：0-不启用，1-启用
     */
    private Integer hasSpecification;

    /**
     * 是否删除：0-未删除，1-已删除
     */
    private Integer isDel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 商品规格组关联列表
     */
    private List<ProductSpecGroup> productSpecGroups;
    
    /**
     * 商品规格SKU列表
     */
    private List<ProductSku> skuList;
}