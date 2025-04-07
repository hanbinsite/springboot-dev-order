package site.hb.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import site.hb.order.domain.Order;

@Mapper
public interface OrderMapper {


    /**
     * 新增订单
     * @param order 订单
     * @return int
     */
    @Insert("INSERT INTO order (`id`, `order_no`, `user_id`, )")
    int insertOrder(Order order);

}
