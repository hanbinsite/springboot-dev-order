package site.hb.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.hb.order.domain.OrderItemOption;

import java.util.List;

@Mapper
public interface OrderItemOptionMapper {

    /**
     * 插入新的订单项选项
     * @param orderItemOption 订单项选项对象
     * @return 影响的行数
     */
    int insert(OrderItemOption orderItemOption);

    /**
     * 根据ID更新订单项选项
     * @param orderItemOption 订单项选项对象
     * @return 影响的行数
     */
    int updateById(OrderItemOption orderItemOption);

    /**
     * 根据ID删除订单项选项
     * @param id 订单项选项ID
     * @return 影响的行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 根据订单项ID删除所有选项
     * @param orderItemId 订单项ID
     * @return 影响的行数
     */
    int deleteByOrderItemId(@Param("orderItemId") String orderItemId);

    /**
     * 根据ID查询订单项选项
     * @param id 订单项选项ID
     * @return 订单项选项对象
     */
    OrderItemOption selectById(@Param("id") String id);

    /**
     * 根据订单项ID查询所有选项
     * @param orderItemId 订单项ID
     * @return 订单项选项列表
     */
    List<OrderItemOption> selectByOrderItemId(@Param("orderItemId") String orderItemId);

    /**
     * 批量插入订单项选项
     * @param orderItemOptions 订单项选项列表
     * @return 影响的行数
     */
    int batchInsert(@Param("list") List<OrderItemOption> orderItemOptions);
}