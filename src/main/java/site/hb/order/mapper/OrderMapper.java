package site.hb.order.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import site.hb.order.domain.Order;

@Mapper
public interface OrderMapper {


    /**
     * 新增订单
     * @param order 订单
     * @return int
     */
    @Insert("INSERT INTO order (`id`, `order_no`, `user_id`, `total_amount`, `actual_amount`, `discount_amount`, `status`, " +
    " `create_time`, `update_time`, `order_time`) VALUE ( #{id}, #{orderNo}, #{userId}, #{totalAmount}, #{actualAmount}, #{discountAmount}, " +
    " #{status}, #{create_time}, #{update_time}, #{order_time} )")
    int insertOrder(Order order);

    /**
     * 查询订单详情
     * @param orderNo
     * @return Order
     */
    @Results(value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "orderNo", column = "order_no"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "totalAmount", column = "total_amount"),
        @Result(property = "actualAmount", column = "actual_amount"),
        @Result(property = "discountAmount", column = "discount_amount"),
        @Result(property = "paymentType", column = "payment_type"),
        @Result(property = "paymentNo", column = "payment_no"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time"),
        @Result(property = "orderTime", column = "order_time"),
        @Result(property = "paymentTime", column = "payment_time"),
        @Result(property = "shippingTime", column = "shipping_time"),
        @Result(property = "completionTime", column = "completion_time"),
        @Result(property = "isDel", column = "is_del"),
        @Result(property = "delTime", column = "del_time")
    })
    @Select("SELECT * FROM order WHERE is_del = 0 AND `user_id` = #{userId} AND `order_no` = #{orderNo}")
    Order infoOrder(String orderNo, String userId);

    /**
     * 删除订单
     * @param orderNo
     * @param userId
     * @return
     */
    @Update("UPDATE order SET is_del = 1, del_time = #{delTime} WHERE  `is_del` = 0 AND `user_id` = #{userId} AND `order_no` = #{orderNo}")
    int delOrder(String orderNo, String userId, Date delTime);


    /**
     * 订单支付
     * @param orderNo 订单号
     * @param userId 用户id
     * @param paymentType 支付方式
     * @param paymentNo 三方支付流水号
     * @param paymentTime 支付时间
     * @param updateTime 更新时间
     * @return int
     */
    @Update("UPDATE order SET payment_type = #{paymentType}, payment_no = #{paymentNo}, " +
    " payment_time = #{paymentTime}, update_time = #{updateTime} WHERE `is_del` = 0 AND `user_id` = #{userId} AND `order_no` = #{orderNo}")
    int paymentOrder(String orderNo, String userId, int paymentType, String paymentNo, Date paymentTime, Date updateTime);

}
