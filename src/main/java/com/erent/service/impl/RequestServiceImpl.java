package com.erent.service.impl;

import com.erent.mapper.ReqMapper;
import com.erent.pojo.Request;
import com.erent.service.RequestService;
import com.erent.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    // 1.创建 SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /**
     * 提出请求
     *
     * @param request
     */
    @Override
    public void addReq(Request request) {
        SqlSession sqlSession = factory.openSession();
        ReqMapper mapper = sqlSession.getMapper(ReqMapper.class);

        mapper.addReq(request);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 取消请求
     *
     * @param id
     */
    @Override
    public void cancelReq(int id) {
        SqlSession sqlSession = factory.openSession();
        ReqMapper mapper = sqlSession.getMapper(ReqMapper.class);

        mapper.cancelReq(id);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 同意看房请求
     *
     * @param id
     */
    @Override
    public void comfirmReq(int id) {
        SqlSession sqlSession = factory.openSession();
        ReqMapper mapper = sqlSession.getMapper(ReqMapper.class);

        mapper.comfirmReq(id);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 拒绝看房请求
     *
     * @param id
     */
    @Override
    public void rejectReq(int id) {
        SqlSession sqlSession = factory.openSession();
        ReqMapper mapper = sqlSession.getMapper(ReqMapper.class);

        mapper.rejectReq(id);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据请求者id查询订单
     *
     * @param id
     * @return
     */
    @Override
    public List<Request> selectByReqId(int id) {
        SqlSession sqlSession = factory.openSession();
        ReqMapper mapper = sqlSession.getMapper(ReqMapper.class);

        List<Request> requests = mapper.selectByReqId(id);

        sqlSession.close();
        return requests;
    }

    /**
     * 根据房东id查询订单
     *
     * @param id
     * @return
     */
    @Override
    public List<Request> selectByOwnerId(int id) {
        SqlSession sqlSession = factory.openSession();
        ReqMapper mapper = sqlSession.getMapper(ReqMapper.class);

        List<Request> requests = mapper.selectByOwnerId(id);

        sqlSession.close();
        return requests;
    }

    /**
     * 根据房屋ID,请求ID更改相关信息
     * @param reqId
     * @param houseId
     * @param renterId
     */
    @Override
    public void isRent(int reqId,int houseId,int renterId){
        SqlSession sqlSession = factory.openSession();
        ReqMapper mapper = sqlSession.getMapper(ReqMapper.class);

        mapper.isRent(reqId,houseId,renterId);

        sqlSession.commit();
        sqlSession.close();
    }


}
