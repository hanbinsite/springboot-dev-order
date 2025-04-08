package site.hb.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.hb.order.domain.ProductSku;

import java.util.List;

@Mapper
public interface ProductSkuMapper {

    /**
     * 插入新的商品SKU
     * @param productSku 商品SKU对象
     * @return 影响的行数
     */
    int insert(ProductSku productSku);

    /**
     * 根据ID更新商品SKU
     * @param productSku 商品SKU对象
     * @return 影响的行数
     */
    int updateById(ProductSku productSku);

    /**
     * 根据ID删除商品SKU
     * @param id 商品SKU ID
     * @return 影响的行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 根据商品ID删除所有关联的SKU
     * @param productId 商品ID
     * @return 影响的行数
     */
    int deleteByProductId(@Param("productId") String productId);

    /**
     * 根据ID查询商品SKU
     * @param id 商品SKU ID
     * @return 商品SKU对象
     */
    ProductSku selectById(@Param("id") String id);

    /**
     * 根据商品ID查询关联的所有SKU
     * @param productId 商品ID
     * @return 商品SKU列表
     */
    List<ProductSku> selectByProductId(@Param("productId") String productId);

    /**
     * 批量插入商品SKU
     * @param productSkus 商品SKU列表
     * @return 影响的行数
     */
    int batchInsert(@Param("list") List<ProductSku> productSkus);

    /**
     * 更新商品SKU库存
     * @param id SKU ID
     * @param stock 变动库存数量（正数增加，负数减少）
     * @return 影响的行数
     */
    int updateStock(@Param("id") String id, @Param("stock") Integer stock);

    /**
     * 更新商品SKU销量
     * @param id SKU ID
     * @param salesVolume 变动销量（正数增加，负数减少）
     * @return 影响的行数
     */
    int updateSalesVolume(@Param("id") String id, @Param("salesVolume") Integer salesVolume);
}