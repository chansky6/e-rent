package com.erent.web;

import com.alibaba.fastjson2.JSON;
import com.erent.pojo.Request;
import com.erent.service.RequestService;
import com.erent.service.impl.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/request/*")
public class RequestServlet extends BaseServlet{
    // 解耦合
    private RequestService requestService = new RequestServiceImpl();


    /**
     * 提出请求
     */
    public void addReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader br = request.getReader();
        String params = br.readLine();

        Request req = JSON.parseObject(params, Request.class);

        req.setReqTime(new java.util.Date(System.currentTimeMillis()));
        requestService.addReq(req);

        response.getWriter().write("success");
    }

    /**
     * 取消请求
     */
    public void cancelReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        requestService.cancelReq(id);

        response.getWriter().write("success");
    }

    /**
     * 同意看房请求
     */
    public void comfirmReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        requestService.comfirmReq(id);

        response.getWriter().write("success");

    }

    /**
     * 拒绝看房请求
     */
    public void rejectReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        requestService.rejectReq(id);

        response.getWriter().write("success");
    }

    /**
     * 根据请求者id查询订单
     * @return
     */
    public void selectByReqId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        List<Request> requests = requestService.selectByReqId(id);
        String jsonString = JSON.toJSONString(requests);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 根据房东id查询订单
     * @return
     */
    public void selectByOwnerId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        List<Request> requests = requestService.selectByOwnerId(id);
        String jsonString = JSON.toJSONString(requests);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 更改房间为出租状态,更改请求状态为已出租
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isRent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String _reqId = request.getParameter("reqId");
        String _houseId = request.getParameter("houseId");
        String _renterId = request.getParameter("renterId");

        int reqId = Integer.parseInt(_reqId);
        int houseId = Integer.parseInt(_houseId);
        int renterId = Integer.parseInt(_renterId);

        requestService.isRent(reqId,houseId,renterId);

        response.getWriter().write("success");
    }


}
