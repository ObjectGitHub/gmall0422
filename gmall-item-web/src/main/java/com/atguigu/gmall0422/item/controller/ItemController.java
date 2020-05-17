package com.atguigu.gmall0422.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall0422.bean.SkuImage;
import com.atguigu.gmall0422.bean.SkuInfo;
import com.atguigu.gmall0422.bean.SkuSaleAttrValue;
import com.atguigu.gmall0422.bean.SpuSaleAttr;
import com.atguigu.gmall0422.config.LoginRequire;
import com.atguigu.gmall0422.service.ListService;
import com.atguigu.gmall0422.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@Controller
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private static String adb = "";
    @Reference
    private ManageService manageService;

    @Reference
    private ListService listService;

    @RequestMapping("{skuId}.html")
    @LoginRequire   //该注解表示访问商品详情的时候必须要登录
    public String getItem(@PathVariable String skuId , HttpServletRequest request){
        adb = "sss";
        LOGGER.info(adb);
        System.out.println("skuId:"+skuId);
        HashMap<String, String> map = new HashMap<>();
        String key = "";
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);

        //可以将skuInfo.price 从数据库中获取
        SkuInfo skuInfoDB = manageService.getSkuInfoDB(skuId);


        List<SpuSaleAttr> spuSaleAttrList = manageService.getSpuSaleAttrListCheckBySku(skuInfo);
        //查询所有的商品属性值组成的商品id{"113|116":"33"}
        List<SkuSaleAttrValue> skuSaleAttrValueList = manageService.getSkuSaleAttrValueListBySpu(skuInfo.getSpuId());
        if(skuSaleAttrValueList!=null && skuSaleAttrValueList.size()>0){
            //拼接相同skuId属性值 格式为113|116
            for (int i = 0; i <skuSaleAttrValueList.size() ; i++) {
                //获取集合中的每一条数据
                SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueList.get(i);
                //第一次拼接113
                //第二次拼接113|
                //第三次拼接113|116
                if(key.length()>0){
                    key += "|";
                }
                key += skuSaleAttrValue.getSaleAttrValueId();
                if((i+1)==skuSaleAttrValueList.size() || !skuSaleAttrValue.getSkuId().equals(skuSaleAttrValueList.get(i+1).getSkuId())){
                    map.put(key, skuSaleAttrValue.getSkuId());
                    key = "";
                }
            }
        }

        listService.incrHotScore(skuId);
        //然后将map转为js字符串
        String valuesSkuJson  = JSON.toJSONString(map);
        LOGGER.info(valuesSkuJson);
        request.setAttribute("valuesSkuJson",valuesSkuJson);
        request.setAttribute("spuSaleAttrList",spuSaleAttrList);
        request.setAttribute("skuInfo",skuInfo);
        request.setAttribute("skuInfoDB",skuInfoDB);
        return "item";
    }
}
