package com.atguigu.gmall0422.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0422.bean.*;
import com.atguigu.gmall0422.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("getCatalog1")
    public List<BaseCatalog1> getCatalog1(){
        return manageService.getCatalog1();

    }
    @RequestMapping("getCatalog2")
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
        return manageService.getCatalog2(catalog1Id);

    }
    @RequestMapping("getCatalog3")
    public List<BaseCatalog3> getCatalog3(String catalog2Id){
        return manageService.getCatalog3(catalog2Id);

    }
    //http://localhost:8082/attrInfoList?catalog3Id=63
    @RequestMapping("attrInfoList")
    public List<BaseAttrInfo> attrInfoList(String catalog3Id){
        return manageService.getAttrInfoList(catalog3Id);

    }

    /**
     * 保存和修改数据
     * @param baseAttrInfo  表示从前端页面中获取数据
     * @return
     */
    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveBaseAttrInfo(baseAttrInfo);
        return "OK";
    }
    //8082/getAttrValueList?attrId=97
//    @RequestMapping("getAttrValueList")
//    public List<BaseAttrValue> getAttrValueList(String attrId){
//
//        return manageService.getAttrValueList(attrId);
//    }

    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(String attrId){
        BaseAttrInfo baseAttrInfo = manageService.getBaseAttrInfo(attrId);
        return baseAttrInfo.getAttrValueList();
    }

    @RequestMapping("spuList")
    public List<SpuInfo> spuList(SpuInfo spuInfo){
        return manageService.getSpuInfoList(spuInfo);
    }

}
