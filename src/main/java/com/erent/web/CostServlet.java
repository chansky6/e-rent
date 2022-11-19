package com.erent.web;

import com.erent.service.CostService;
import com.erent.service.impl.CostServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/expenseBill/*")
public class CostServlet extends BaseServlet {

    private CostService costService = new CostServiceImpl();

    public void generateExpenseBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取参数
        String _arrearsId = request.getParameter("arrearsId");
        String _cost = request.getParameter("cost");

        //转换为int类型
        int arrearsId = Integer.parseInt(_arrearsId);
        int cost = Integer.parseInt(_cost);
        //添加费用单
        costService.generateExpenseBill(arrearsId,cost);

        response.getWriter().write("success");
    }

}
