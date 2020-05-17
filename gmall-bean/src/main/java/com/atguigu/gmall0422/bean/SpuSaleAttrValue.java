package com.atguigu.gmall0422.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**spu商品属性值表
 * @author msi-pc
 */
@Data
public class SpuSaleAttrValue implements Serializable {
    @Id
    @Column
    String id ;

    @Column
    String spuId;

    @Column
    String saleAttrId;

    @Column
    String saleAttrValueName;

    //当前销售属性是否被选中 1：选中 0 未选中
    @Transient
    String isChecked;

}
