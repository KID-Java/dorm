package cn.linguolai.dorm.tools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * 实现一个BaseServlet，用于给Servlet实现，从而大量减少Servlet的数量
 * 机制模仿了SpringMVC框架
 */
public class BaseServlet extends HttpServlet {
    public BaseServlet() {
    }

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理POST编码问题
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String methodName = null;

        try {
            //获取需要执行的方法名
            methodName = req.getParameter("m");

            //通过反射获取机制执行对应的方法
            Class z = this.getClass();
            Method method = z.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            String handle = (String)method.invoke(this, req, resp);

            //通过方法的返回字符串决定转发或者重定向的路径
            if (!handle.contains(":")) {
                //如果不存在前缀，则默认直接执行转发
                req.getRequestDispatcher(handle).forward(req, resp);
            } else {
                int index = handle.indexOf(":");
                String pre = handle.substring(0, index);
                String suf = handle.substring(index + 1);
                //如果前缀的f，则转发，如果前缀是r则重定向，否则抛出异常
                if ("f".equalsIgnoreCase(pre)) {
                    req.getRequestDispatcher(suf).forward(req, resp);
                } else {
                    if (!"r".equalsIgnoreCase(pre)) {
                        throw new ServletException("当前版本不支持操作:" + pre);
                    }

                    resp.sendRedirect(req.getContextPath() + suf);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("找不到方法:" + methodName);
        }
    }
}
