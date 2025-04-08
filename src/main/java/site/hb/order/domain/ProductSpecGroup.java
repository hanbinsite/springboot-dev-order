package site.hb.order.domain;

import lombok.Data;
import java.util.Date;

@Data
public class ProductSpecGroup {
    
    /**
     * ID
     */
    private String id;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 规格组ID
     */
    private String specGroupId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}