package com.erent.service.impl;

import com.erent.mapper.HouseMapper;
import com.erent.mapper.ReqMapper;
import com.erent.pojo.House;
import com.erent.pojo.PageBean;
import com.erent.service.HouseService;
import com.erent.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class HouseServiceImpl implements HouseService {
    // 1.创建 SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<House> selectAll() {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        // 调用方法
        List<House> houses = mapper.selectAll();

        sqlSession.close();

        return houses;
    }

    @Override
    public House selectById(int id) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        // 调用方法
        House house = mapper.selectById(id);

        sqlSession.close();

        return house;
    }

    @Override
    public PageBean<House> selectByPageAndCondition(int current, int pageSize, House house, int lower, int upper) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        // 计算开始索引
        int begin = (current - 1) * pageSize;
        int size = pageSize;

        // 处理 house 条件，模糊查询
        String type = house.getType();
        if (type != null && type.length() > 0) {
            house.setType("%" + type + "%");
        }

        String addr = house.getAddr();
        if (addr != null && addr.length() > 0) {
            house.setAddr("%" + addr + "%");
        }

        // 当前页数据
        List<House> rows = mapper.selectByPageAndCondition(begin, size, house, lower, upper);

        // 总记录数
        int totalCount = mapper.selectTotalCountByCondition(house, lower, upper);

        PageBean<House> housePageBean = new PageBean<>();
        housePageBean.setRows(rows);
        housePageBean.setTotalCount(totalCount);

        sqlSession.close();

        return housePageBean;
    }


    @Override
    public void addHouse(House house) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        mapper.addHouse(house);
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void changeHouseStatus(int id, int status) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        mapper.changeHouseStatus(id,status);
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public List<House> selectByOwnerId(int ownerId) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        List<House> housesSelectByOwnerId = mapper.selectByOwnerId(ownerId);
        sqlSession.close();
        
        return housesSelectByOwnerId;
    }

    @Override
    public List<House> selectByRenterId(int renterId) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        List<House> housesSelectByRenterId = mapper.selectByRenterId(renterId);
        sqlSession.close();

        return housesSelectByRenterId;
    }

    @Override
    public void deleteHouseById(int id) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        mapper.deleteHouseById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateHouseRentById(int id, int rent) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        mapper.updateHouseRentById(id,rent);
        sqlSession.commit();
        sqlSession.close();
    }


    @Override
    public void checkOut(int houseId) {
        SqlSession sqlSession = factory.openSession();
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);

        mapper.checkOut(houseId);

        sqlSession.commit();
        sqlSession.close();
    }
}
