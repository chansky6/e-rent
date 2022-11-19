package com.erent.mapper;

import com.erent.pojo.Request;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReqMapper {

    /**
     * 提出看房请求
     * @param request
     */
    void addReq(@Param("request") Request request);

    /**
     * 取消看房请求
     * @param id
     */
    @Update("update tb_req set current = 9 where id = #{id};")
    void cancelReq(int id);

    /**
     * 同意看房请求
     * @param id
     */
    @Update("update tb_req set current = 1 where id = #{id}")
    void comfirmReq(int id);

    /**
     * 拒绝看房请求
     * @param id
     */
    @Update("update tb_req set current = 2 where id = #{id}")
    void rejectReq(int id);

    /**
     * 根据请求者id查询订单
     * @param id
     * @return
     */
    @Select("select * from tb_req where req_id = #{id} and current in (0,1,2) ;")
    @ResultMap("requestResultMap")
    List<Request> selectByReqId(int id);

    /**
     * 根据房东id查询订单
     * @param id
     * @return
     */
    @Select("select * from tb_req where owner_id = #{id} and current in (0,1,2);")
    @ResultMap("requestResultMap")
    List<Request> selectByOwnerId(int id);

    /**
     * 根据请求ID更改请求信息,根据房屋ID更改房屋信息
     * @param reqId
     * @param houseId
     * @param renterId
     */
    void isRent(@Param("reqId") int reqId,
                @Param("houseId") int houseId,
                @Param("renterId") int renterId);

}
