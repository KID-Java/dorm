package cn.linguolai.dorm.web.servlet;

import cn.linguolai.dorm.factory.VisitFactory;
import cn.linguolai.dorm.service.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InitServlet",urlPatterns = "")
public class InitServlet extends HttpServlet {


    private VisitService visitService = VisitFactory.getVisitService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取最新的一个访客记录
        request.setAttribute("lastVisitInfo", visitService.getLastVisitInfo());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
