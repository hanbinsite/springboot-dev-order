package site.hb.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.hb.order.domain.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 插入新的商品
     * @param product 商品对象
     * @return 影响的行数
     */
    int insert(Product product);

    /**
     * 根据ID更新商品
     * @param product 商品对象
     * @return 影响的行数
     */
    int updateById(Product product);

    /**
     * 根据ID删除商品（逻辑删除）
     * @param id 商品ID
     * @return 影响的行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 根据ID查询商品
     * @param id 商品ID
     * @return 商品对象
     */
    Product selectById(@Param("id") String id);

    /**
     * 查询商品列表
     * @param status 商品状态（可选）
     * @param categoryId 分类ID（可选）
     * @return 商品列表
     */
    List<Product> selectList(@Param("status") Integer status, @Param("categoryId") String categoryId);

    /**
     * 更新商品库存
     * @param id 商品ID
     * @param stock 变动库存数量（正数增加，负数减少）
     * @return 影响的行数
     */
    int updateStock(@Param("id") String id, @Param("stock") Integer stock);

    /**
     * 更新商品销量
     * @param id 商品ID
     * @param salesVolume 变动销量（正数增加，负数减少）
     * @return 影响的行数
     */
    int updateSalesVolume(@Param("id") String id, @Param("salesVolume") Integer salesVolume);
}