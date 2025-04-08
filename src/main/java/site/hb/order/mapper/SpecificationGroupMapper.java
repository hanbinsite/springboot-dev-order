package site.hb.order.mapper;

import org.apache.ibatis.annotations.*;
import site.hb.order.domain.SpecificationGroup;

import java.util.List;

@Mapper
public interface SpecificationGroupMapper {

    /**
     * 插入新的规格组
     * @param specificationGroup 规格组对象
     * @return 影响的行数
     */
    @Insert("INSERT INTO specification_group (id, name, sort, create_time, update_time) " +
            "VALUES (#{id}, #{name}, #{sort}, #{createTime}, #{updateTime})")
    int insert(SpecificationGroup specificationGroup);

    /**
     * 根据ID更新规格组
     * @param specificationGroup 规格组对象
     * @return 影响的行数
     */
    @Update("UPDATE specification_group SET name = #{name}, sort = #{sort}, update_time = #{updateTime} " +
            "WHERE id = #{id}")
    int updateById(SpecificationGroup specificationGroup);

    /**
     * 根据ID删除规格组
     * @param id 规格组ID
     * @return 影响的行数
     */
    @Delete("DELETE FROM specification_group WHERE id = #{id}")
    int deleteById(@Param("id") String id);

    /**
     * 根据ID查询规格组
     * @param id 规格组ID
     * @return 规格组对象
     */
    @Select("SELECT id, name, sort, create_time AS createTime, update_time AS updateTime " +
            "FROM specification_group WHERE id = #{id}")
    SpecificationGroup selectById(@Param("id") String id);

    /**
     * 查询所有规格组
     * @return 规格组列表
     */
    @Select("SELECT id, name, sort, create_time AS createTime, update_time AS updateTime " +
            "FROM specification_group ORDER BY sort ASC")
    List<SpecificationGroup> selectAll();

    /**
     * 批量插入规格组
     * @param specificationGroups 规格组列表
     * @return 影响的行数
     */
    @Insert("<script>" +
            "INSERT INTO specification_group (id, name, sort, create_time, update_time) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.id}, #{item.name}, #{item.sort}, #{item.createTime}, #{item.updateTime})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<SpecificationGroup> specificationGroups);
}