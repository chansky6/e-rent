package com.erent.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换 HttpServlet, 根据请求最后一段路径, 进行方法分发
 * 反射的应用
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求路径
        String uri = req.getRequestURI();   // /brand-case/brand/selectAll

        // 2.获取最后一段路径
        int index = uri.lastIndexOf('/');
//        String methodName = uri.substring(index);   // /selectAll
        String methodName = uri.substring(index + 1);   // selectAll
//        System.out.println(methodName);

        // 3.执行方法
        // 3.1 获取 BrandServlet/UserServlet 字节码对象 Class
        // this 被谁调用就代表谁, 即 BaseServlet 的子类
        Class<? extends BaseServlet> cls = this.getClass();
        // 3.2 获取方法的 Method 对象

        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 3.3 执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
