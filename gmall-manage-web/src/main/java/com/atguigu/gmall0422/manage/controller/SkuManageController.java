package com.atguigu.gmall0422.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0422.bean.SkuInfo;
import com.atguigu.gmall0422.bean.SkuLsInfo;
import com.atguigu.gmall0422.bean.SpuImage;
import com.atguigu.gmall0422.bean.SpuSaleAttr;
import com.atguigu.gmall0422.service.ListService;
import com.atguigu.gmall0422.service.ManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SkuManageController {
    @Reference
    private ManageService manageService;
    @Reference
    private ListService listService;

    @RequestMapping("spuImageList")
    public List<SpuImage> getSpuImageList(String spuId){
        return manageService.getSpuImageList(spuId);
    }

    @RequestMapping("spuSaleAttrList")
    public List<SpuSaleAttr> spuSaleAttrList(String spuId){
        return manageService.getSpuSaleAttrList(spuId);
    }
    //saveSkuInfo
    @RequestMapping("saveSkuInfo")
    public String saveSkuInfo(@RequestBody SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
        return "ok";
    }

    @RequestMapping( "onSale")
    public void onSale(String skuId){
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        SkuLsInfo skuLsInfo = new SkuLsInfo();
        // 属性拷贝skuInfo
        BeanUtils.copyProperties(skuInfo,skuLsInfo);
        listService.saveSkuInfo(skuLsInfo);
    }

}
