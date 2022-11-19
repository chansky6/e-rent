package com.erent.service.impl;

import com.erent.mapper.CostMapper;
import com.erent.mapper.HouseMapper;
import com.erent.service.CostService;
import com.erent.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class CostServiceImpl implements CostService {

    // 1.创建 SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void generateExpenseBill(int arrearsId, int cost) {
        // 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();

        CostMapper mapper = sqlSession.getMapper(CostMapper.class);

        mapper.generateExpenseBill(arrearsId,cost);

        sqlSession.commit();
        sqlSession.close();
    }
}
