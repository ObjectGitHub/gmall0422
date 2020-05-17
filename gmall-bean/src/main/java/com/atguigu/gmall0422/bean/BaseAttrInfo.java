package com.atguigu.gmall0422.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 平台属性bean
 * @author msi-pc
 */
@Data
public class BaseAttrInfo implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //获取主键自增值
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;

    @Transient  //表示在数据库中并没有这个字段，而是业务需要
    private List<BaseAttrValue> attrValueList;

}
