package site.hb.order.mapper;

import org.apache.ibatis.annotations.*;
import site.hb.order.domain.SpecificationValue;

import java.util.List;

@Mapper
public interface SpecificationValueMapper {

    /**
     * 插入新的规格值
     * @param specificationValue 规格值对象
     * @return 影响的行数
     */
    @Insert("INSERT INTO specification_value (id, spec_group_id, value, sort, create_time, update_time) " +
            "VALUES (#{id}, #{specGroupId}, #{value}, #{sort}, #{createTime}, #{updateTime})")
    int insert(SpecificationValue specificationValue);

    /**
     * 根据ID更新规格值
     * @param specificationValue 规格值对象
     * @return 影响的行数
     */
    @Update("UPDATE specification_value SET spec_group_id = #{specGroupId}, value = #{value}, " +
            "sort = #{sort}, update_time = #{updateTime} WHERE id = #{id}")
    int updateById(SpecificationValue specificationValue);

    /**
     * 根据ID删除规格值
     * @param id 规格值ID
     * @return 影响的行数
     */
    @Delete("DELETE FROM specification_value WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    /**
     * 根据ID查询规格值
     * @param id 规格值ID
     * @return 规格值对象
     */
    @Select("SELECT id, spec_group_id AS specGroupId, value, sort, " +
            "create_time AS createTime, update_time AS updateTime " +
            "FROM specification_value WHERE id = #{id}")
    SpecificationValue selectById(@Param("id") String id);

    /**
     * 根据规格组ID查询规格值列表
     * @param specGroupId 规格组ID
     * @return 规格值列表
     */
    @Select("SELECT id, spec_group_id AS specGroupId, value, sort, " +
            "create_time AS createTime, update_time AS updateTime " +
            "FROM specification_value WHERE spec_group_id = #{specGroupId} ORDER BY sort ASC")
    List<SpecificationValue> selectBySpecGroupId(@Param("specGroupId") String specGroupId);

    /**
     * 批量插入规格值
     * @param specificationValues 规格值列表
     * @return 影响的行数
     */
    @Insert("<script>" +
            "INSERT INTO specification_value (id, spec_group_id, value, sort, create_time, update_time) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.id}, #{item.specGroupId}, #{item.value}, #{item.sort}, #{item.createTime}, #{item.updateTime})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<SpecificationValue> specificationValues);
}