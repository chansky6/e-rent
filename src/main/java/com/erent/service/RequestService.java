package com.erent.service;

import com.erent.pojo.Request;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface RequestService {

    /**
     * 提出请求
     * @param request
     */
    void addReq(Request request);

    /**
     * 取消请求
     * @param id
     */
    void cancelReq(int id);

    /**
     * 同意看房请求
     * @param id
     */
    void comfirmReq(int id);

    /**
     * 拒绝看房请求
     * @param id
     */
    void rejectReq(int id);

    /**
     * 根据请求者id查询订单
     * @param id
     * @return
     */
    List<Request> selectByReqId(int id);

    /**
     * 根据房东id查询订单
     * @param id
     * @return
     */
    List<Request> selectByOwnerId(int id);

    /**
     * 根据房屋ID,请求ID更改相关信息
     * @param reqId
     * @param houseId
     * @param renterId
     */
    void isRent(int reqId,int houseId,int renterId);



}
