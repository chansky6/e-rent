package com.erent.web;

import com.alibaba.fastjson.JSON;
import com.erent.pojo.User;
import com.erent.service.UserService;
import com.erent.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    // 解耦合
    private UserService userService = new UserServiceImpl();

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        // 1.调用service
        User user = userService.selectById(id);

        // 2.转为JSON
        String jsonString = JSON.toJSONString(user);

        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");

        User user = userService.selectByUsername(username);

        response.getWriter().write(String.valueOf(user != null));
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader br = request.getReader();
        String params = br.readLine();

        User user = JSON.parseObject(params, User.class);
        boolean b = userService.register(user);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(String.valueOf(b));
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 1. 获取用户名和密码
        String username = new String(request.getParameter("username").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String password = request.getParameter("password");

        // 2. 调用 service 查询
        User user = userService.login(username, password);

        // 3. 判断
        if (user != null) {
            // 转 JSON
            String jsonString = JSON.toJSONString(user, User.class.getModifiers());

            // 写数据
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);

        } else {
            // 登录失败,写入 failed
            response.getWriter().write("failed");
            return;
        }
    }
}
