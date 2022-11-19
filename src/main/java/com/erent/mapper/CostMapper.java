package com.erent.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface CostMapper {

    /**
     * 生成费用账单
     * @param arrearsId
     * @param cost
     */
    @Insert("insert into tb_cost (arrears_id,cost) values (#{arrearsId},#{cost});")
    void generateExpenseBill(@Param("arrearsId") int arrearsId,
                             @Param("cost") int cost);
}
