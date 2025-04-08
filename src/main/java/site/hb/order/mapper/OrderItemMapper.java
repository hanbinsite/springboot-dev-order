package site.hb.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.hb.order.domain.OrderItem;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    /**
     * 插入新的订单项
     * @param orderItem 订单项对象
     * @return 影响的行数
     */
    int insert(OrderItem orderItem);

    /**
     * 根据ID更新订单项
     * @param orderItem 订单项对象
     * @return 影响的行数
     */
    int updateById(OrderItem orderItem);

    /**
     * 根据ID删除订单项
     * @param id 订单项ID
     * @return 影响的行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 根据订单ID删除所有订单项
     * @param orderId 订单ID
     * @return 影响的行数
     */
    int deleteByOrderId(@Param("orderId") String orderId);

    /**
     * 根据ID查询订单项
     * @param id 订单项ID
     * @return 订单项对象
     */
    OrderItem selectById(@Param("id") String id);

    /**
     * 根据订单ID查询所有订单项
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<OrderItem> selectByOrderId(@Param("orderId") String orderId);

    /**
     * 批量插入订单项
     * @param orderItems 订单项列表
     * @return 影响的行数
     */
    int batchInsert(@Param("list") List<OrderItem> orderItems);
}