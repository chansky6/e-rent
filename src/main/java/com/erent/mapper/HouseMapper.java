package com.erent.mapper;

import com.erent.pojo.House;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HouseMapper {

    /**
     * 查询所有房屋信息
     *
     * @return
     */
    @Select("select * from tb_house;")
    @ResultMap("houseResultMap")
    List<House> selectAll();

    /**
     * 条件分页查询
     *
     * @param begin
     * @param size
     * @param house
     * @return
     */
    List<House> selectByPageAndCondition(@Param("begin") int begin,
                                         @Param("size") int size,
                                         @Param("house") House house,
                                         @Param("lower") int lower,
                                         @Param("upper") int upper);

    /**
     * 条件查询条目数
     *
     * @param house
     * @return
     */
    int selectTotalCountByCondition(@Param("house") House house,
                                    @Param("lower") int lower,
                                    @Param("upper") int upper);

    /**
     * 通过ID搜素房屋
     *
     * @param id
     * @return
     */
    @Select("select * from tb_house where id = #{id};")
    @ResultMap("houseResultMap")
    House selectById(int id);

    /**
     * 添加房屋信息
     *
     * @param house
     */
    @Insert("insert into tb_house (owner_id, addr, type, status, rent,time,renter_id) values (#{ownerId}, #{addr}, #{type}, #{status}, #{rent}, #{time},0)")
    void addHouse(House house);

    /**
     * 更改房屋出租状态
     *
     * @param id
     * @param status
     */
    @Update("update tb_house set status = #{status} where id = #{id};")
    void changeHouseStatus(@Param("id") int id,
                           @Param("status") int status);

    /**
     * 根据房东ID查询房屋状态
     * @param ownerId
     * @return
     */
    @Select("select * from tb_house where owner_id = #{id}")
    @ResultMap("houseResultMap")
    List<House> selectByOwnerId(@Param("id") int ownerId);

    /**
     * 根据租客ID查询房屋状态
     * @param renterId
     * @return
     */
    @Select("select * from tb_house where renter_id = #{id}")
    @ResultMap("houseResultMap")
    List<House> selectByRenterId(@Param("id") int renterId);

    /**
     * 根据房屋ID下架(删除)房屋
     * @param id
     */
    @Delete("delete from tb_house where id = #{id};")
    void deleteHouseById(int id);

    /**
     * 根据房屋ID更改房屋租金
     * @param id
     * @param rent
     */
    @Update("update tb_house set rent = #{rent} where id = #{id};")
    void updateHouseRentById(@Param("id") int id,@Param("rent") int rent);

    /**
     * 根据房屋ID更改房屋信息
     * @param houseId
     */
    @Update("update tb_house set renter_id = 0 where id = #{houseId};")
    void checkOut(@Param("houseId") int houseId);
}
