package com.leyou.item.mapper;

import com.leyou.common.mapper.BaseMapper;
import com.leyou.item.pojo.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


public interface StockMapper extends BaseMapper<Stock> {
    @Update("UPDATE tb_stock SET stock = stock- #{num} WHERE sku_id = #{sku_id} AND stock >= #{num}")
    int decreaseStock(@Param("sku_id") Long skuId, @Param("num") Integer num);
}
