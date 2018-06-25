package cn.linguolai.dorm.web.servlet;

import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.bean.VisitInfo;
import cn.linguolai.dorm.exception.StudentException;
import cn.linguolai.dorm.exception.VisitException;
import cn.linguolai.dorm.factory.VisitFactory;
import cn.linguolai.dorm.service.VisitService;
import cn.linguolai.dorm.tools.BaseServlet;
import cn.linguolai.dorm.tools.BeanTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "VisitServlet", urlPatterns = "/visit")
public class VisitServlet extends BaseServlet {

    private VisitService visitService = VisitFactory.getVisitService();

    /**
     * 转发到访客登记信息列表页面
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String visitInfo(HttpServletRequest request, HttpServletResponse response) {

        //获取当前页码，当页码为空时，默认为第一页
        String currentPageNum = request.getParameter("currentPageNum");
        //Long型页码
        Long munber;
        if (currentPageNum == null) {
            munber = 1L;
        } else {
            munber = Long.parseLong(currentPageNum);
        }

        //每页来访记录数量 默认为3
        Integer limit = 3;

        //获取分页数据
        PageBean<VisitInfo> visitInfoPageBean = null;
        try {
            visitInfoPageBean = visitService.getPageBean(munber, limit);
        } catch (VisitException e) {
            //保存并转发错误信息
            request.setAttribute("msg",e.getMessage());
            return "/views/error.jsp";
        }

        //将数据保存到request域并转发
        request.setAttribute("pageBean", visitInfoPageBean);
        return "/views/visit_info.jsp";
    }

    /**
     * 添加一个来访记录信息
     *
     * @param request
     * @param response
     * @return
     */
    public String addVisitInfo(HttpServletRequest request, HttpServletResponse response) {

        //获取参数信息集合
        Map result = request.getParameterMap();
        VisitInfo visitInfo = BeanTools.toBean(VisitInfo.class, result);

        if (visitInfo == null) {
            request.setAttribute("msg", "请求参数异常！请刷新后重试！");
            return "/views/error.jsp";
        }

        if (visitInfo.getName().trim().isEmpty() || visitInfo.getAddress().trim().isEmpty() || visitInfo.getContent().trim().isEmpty()) {
            request.setAttribute("msg", "请先将信息填写完整！");
            return "f:/views/visit.jsp";
        }

        //填充时间信息
        visitInfo.setDate(new Date());


        visitService.addVisitInfo(visitInfo);
        //转发到来访信息记录列表
        return "/visit?m=visitInfo";
    }


}
