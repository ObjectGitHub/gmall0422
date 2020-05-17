package com.atguigu.gmall0422.manage.mapper;

import com.atguigu.gmall0422.bean.SpuSaleAttr;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {
    //根据spuId获取销售属性对象集合，使用xml,自定义sql
    List<SpuSaleAttr> selectSpuSaleAttrList(String spuId);

    /**
     * 查询销售属性对象集合
     * @param skuId
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("skuId") String skuId,@Param("spuId") String spuId);
}
