package com.atguigu.gmall0422.service;

import com.atguigu.gmall0422.bean.*;

import java.util.List;

public interface ManageService {
    /**
     * 查询所有一级分类数据
     * @return
     */
    List<BaseCatalog1> getCatalog1();
    /**
     * 根据一级分类id查询二级分类数据
     * @return
     */
    List<BaseCatalog2> getCatalog2(String catalog1Id);
    /**
     * 根据二级分类id查询三级分类数据
     * @return
     */
    List<BaseCatalog3> getCatalog3(String catalog2Id);
    /**
     * 根据三级分类id查询平台属性
     * @return
     */
    List<BaseAttrInfo> getAttrInfoList(String catalog3Id);

    /**
     * 保存平台属性和属性值
     */
    void saveBaseAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 根据平台属性id获取平台属性值
     * @param attrId
     * @return
     */
    List<BaseAttrValue> getAttrValueList(String attrId);

    /**
     * 根据平台属性id获取属性对象
     * @param attrId
     * @return
     */
    BaseAttrInfo getBaseAttrInfo(String attrId);

    /**
     * 根据三级分类id查询SpuInfo列表
     * @param catalog3Id
     * @return
     */
    List<SpuInfo> getSpuInfoList(String catalog3Id);

    /**
     * 根据SpuInfo属性查询列表
     * @param spuInfo
     * @return
     */
    List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);

    /**
     * 获取所有销售属性
     * @return
     */
    List<BaseSaleAttr> getBaseSaleAttrList();

    /**
     * 保存商品信息，销售属性和属性值
     * @param spuInfo
     */
    void saveSpuInfo(SpuInfo spuInfo);

    /**
     * 根据spuId查询图片列表
     * @param spuId
     * @return
     */
    List<SpuImage> getSpuImageList(String spuId);

    /**
     * 根据spuId获取销售属性集合对象
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrList(String spuId);

    /**
     * 保存sku
     * @param skuInfo
     */
    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 根据skuId获取sku
     * @param skuId
     * @return
     */
    SkuInfo getSkuInfo(String skuId);

    /**
     * 根据skuId获取sku图片集合
     * @param skuId
     * @return
     */
    List<SkuImage> getSkuImageList(String skuId);

    /**
     * 根据业务需求查询销售属性对象集合
     * @param skuInfo
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(SkuInfo skuInfo);

    /**
     * 根据spuId查所有的销售属性集合
     * @param spuId
     * @return
     */
    List<SkuSaleAttrValue> getSkuSaleAttrValueListBySpu(String spuId);


    /**
     * 通过skuId查询商品价格
     * @param skuId
     * @return
     */
    SkuInfo getSkuInfoDB(String skuId);

    /**
     * 根据平台属性值id查询平台属性集合
     * @param attrValueIdList
     * @return
     */
    List<BaseAttrInfo> getAttrInfoList(List<String> attrValueIdList);

}
