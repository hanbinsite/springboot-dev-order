package site.hb.order.domain;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class SpecificationGroup {
    
    /**
     * 主规格ID
     */
    private String id;

    /**
     * 主规格名称（如：尺寸、颜色、口味等）
     */
    private String name;

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

    /**
     * 规格值列表
     */
    private List<SpecificationValue> values;
}