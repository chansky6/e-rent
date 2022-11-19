package com.erent.service;

import com.erent.pojo.House;
import com.erent.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HouseService {
    /**
     * 查询所有房屋
     *
     * @return
     */
    List<House> selectAll();

    /**
     * 分页条件查询
     *
     * @param current
     * @param pageSize
     * @param house
     * @param lower
     * @param upper
     * @return
     */
    PageBean<House> selectByPageAndCondition(int current, int pageSize, House house, int lower, int upper);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    House selectById(int id);

    /**
     * 添加房屋
     *
     * @param house
     */
    void addHouse(House house);

    /**
     * 根据ID修改房屋出租状态
     *
     * @param id
     * @param status
     */
    void chengeHouseStatus(int id, int status);

    /**
     * 根据房东ID查询房屋状态
     * @param ownerId
     * @return
     */
    List<House> selectByOwnerId(int ownerId);

    /**
     * 根据租客ID查询房屋状态
     * @param renterId
     * @return
     */
    List<House> selectByRenterId(int renterId);

    /**
     * 根据房屋ID下架(删除)房屋
     * @param id
     */
    void deleteHouseById(int id);

    /**
     * 根据房屋ID更改房屋租金
     * @param id
     * @param rent
     */
    void updateHouseRentById(int id,int rent);

    /**
     * 根据房屋ID更改相关信息
     * @param houseId
     */
    void checkOut(int houseId);
}
