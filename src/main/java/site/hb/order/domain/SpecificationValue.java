package site.hb.order.domain;

import lombok.Data;
import java.util.Date;

@Data
public class SpecificationValue {
    
    /**
     * 规格值ID
     */
    private String id;

    /**
     * 所属主规格ID
     */
    private String specGroupId;

    /**
     * 规格值名称（如：大杯、中杯、小杯）
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}