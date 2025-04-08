package site.hb.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.hb.order.domain.ProductSpecGroup;

import java.util.List;

@Mapper
public interface ProductSpecGroupMapper {

    /**
     * 插入新的商品规格组关联
     * @param productSpecGroup 商品规格组关联对象
     * @return 影响的行数
     */
    int insert(ProductSpecGroup productSpecGroup);

    /**
     * 根据ID更新商品规格组关联
     * @param productSpecGroup 商品规格组关联对象
     * @return 影响的行数
     */
    int updateById(ProductSpecGroup productSpecGroup);

    /**
     * 根据ID删除商品规格组关联
     * @param id 商品规格组关联ID
     * @return 影响的行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 根据商品ID删除所有关联的规格组
     * @param productId 商品ID
     * @return 影响的行数
     */
    int deleteByProductId(@Param("productId") String productId);

    /**
     * 根据ID查询商品规格组关联
     * @param id 商品规格组关联ID
     * @return 商品规格组关联对象
     */
    ProductSpecGroup selectById(@Param("id") String id);

    /**
     * 根据商品ID查询关联的所有规格组
     * @param productId 商品ID
     * @return 商品规格组关联列表
     */
    List<ProductSpecGroup> selectByProductId(@Param("productId") String productId);

    /**
     * 批量插入商品规格组关联
     * @param productSpecGroups 商品规格组关联列表
     * @return 影响的行数
     */
    int batchInsert(@Param("list") List<ProductSpecGroup> productSpecGroups);
}