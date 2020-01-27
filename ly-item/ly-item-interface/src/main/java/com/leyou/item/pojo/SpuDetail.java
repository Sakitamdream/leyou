package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author leoso
 * @create 2020-01-06 12:26
 */
@Data
@Table(name="tb_spu_detail")
public class SpuDetail {
    @Id
    private Long spuId;
    private String description;
    private String specialSpec;
    private String genericSpec;
    private String packingList;
    private String afterService;
}
