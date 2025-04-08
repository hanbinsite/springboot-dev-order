package site.hb.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.hb.order.domain.ProductOption;

import java.util.List;

@Mapper
public interface ProductOptionMapper {

    /**
     * 插入新的商品选项
     * @param productOption 商品选项对象
     * @return 影响的行数
     */
    int insert(ProductOption productOption);

    /**
     * 根据ID更新商品选项
     * @param productOption 商品选项对象
     * @return 影响的行数
     */
    int updateById(ProductOption productOption);

    /**
     * 根据ID删除商品选项
     * @param id 商品选项ID
     * @return 影响的行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 根据商品ID删除所有关联的选项
     * @param productId 商品ID
     * @return 影响的行数
     */
    int deleteByProductId(@Param("productId") String productId);

    /**
     * 根据ID查询商品选项
     * @param id 商品选项ID
     * @return 商品选项对象
     */
    ProductOption selectById(@Param("id") String id);

    /**
     * 根据商品ID查询关联的所有选项
     * @param productId 商品ID
     * @return 商品选项列表
     */
    List<ProductOption> selectByProductId(@Param("productId") String productId);

    /**
     * 批量插入商品选项
     * @param productOptions 商品选项列表
     * @return 影响的行数
     */
    int batchInsert(@Param("list") List<ProductOption> productOptions);
}