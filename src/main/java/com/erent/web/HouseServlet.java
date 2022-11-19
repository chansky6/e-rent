package com.erent.web;

import com.alibaba.fastjson.JSON;
import com.erent.pojo.House;
import com.erent.pojo.PageBean;
import com.erent.service.HouseService;
import com.erent.service.impl.HouseServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet("/house/*")
public class HouseServlet extends BaseServlet {
    // 解耦合
    private HouseService houseService = new HouseServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.调用service
        List<House> houses = houseService.selectAll();

        // 2.转为JSON
        String jsonString = JSON.toJSONString(houses);

        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        // 1.调用service
        House house = houseService.selectById(id);
        // 2.转为JSON
        String jsonString = JSON.toJSONString(house);

        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 接收 当前页面 每页条数 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        String _lower = request.getParameter("lower");
        String _upper = request.getParameter("upper");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        int lower = Integer.parseInt(_lower);
        int upper = Integer.parseInt(_upper);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();  // json字符串

        House brand = JSON.parseObject(params, House.class);

        // 1.调用service
        PageBean<House> brandPageBean = houseService.selectByPageAndCondition(currentPage, pageSize, brand, lower, upper);

        // 2.转为JSON
        String jsonString = JSON.toJSONString(brandPageBean);

        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void addHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("utf-8");

        // 获取ownerId
        String _ownerId = request.getParameter("ownerId");
        int ownerId = Integer.parseInt(_ownerId);

        BufferedReader br = request.getReader();
        String params = br.readLine();
        House house = JSON.parseObject(params, House.class);

        house.setOwnerId(ownerId);
        house.setStatus(0);
        house.setTime(new java.util.Date(System.currentTimeMillis()));

        houseService.addHouse(house);

        response.getWriter().write("success");
    }

    public void chengeHouseStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        //通过get请求获取参数
        String _id = request.getParameter("id");
        String _status = request.getParameter("status");

        //将字符串转为int类型
        int id = Integer.parseInt(_id);
        int status = Integer.parseInt(_status);

        houseService.chengeHouseStatus(id,status);

        response.getWriter().write("success");
    }

    public void selectByOwnerId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        List<House> housesSelectByOwnerId = houseService.selectByOwnerId(id);
        String jsonString =JSON.toJSONString(housesSelectByOwnerId);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByRenterId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        List<House> housesSelectByRenterId = houseService.selectByRenterId(id);
        String jsonString =JSON.toJSONString(housesSelectByRenterId);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteHouseById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        houseService.deleteHouseById(id);

        response.getWriter().write("success");
    }

    public void updateHouseRentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String _id = request.getParameter("id");
        String _rent = request.getParameter("rent");

        int id = Integer.parseInt(_id);
        int rent = Integer.parseInt(_rent);

        houseService.updateHouseRentById(id,rent);

        response.getWriter().write("success");
    }

    /**
     * 更改房间为未出租状态,更改请求状态为已退房
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String _houseId = request.getParameter("houseId");
        int houseId = Integer.parseInt(_houseId);

        houseService.checkOut(houseId);

        response.getWriter().write("success");
    }
}
