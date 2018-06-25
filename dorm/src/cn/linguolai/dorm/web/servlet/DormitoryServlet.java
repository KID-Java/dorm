package cn.linguolai.dorm.web.servlet;

import cn.linguolai.dorm.bean.Dormitory;
import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.exception.DormitoryException;
import cn.linguolai.dorm.exception.StudentException;
import cn.linguolai.dorm.factory.DormitoryFactory;
import cn.linguolai.dorm.service.DormitoryService;
import cn.linguolai.dorm.tools.BaseServlet;
import cn.linguolai.dorm.tools.BeanTools;
import cn.linguolai.dorm.tools.ServletTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DormitoryServlet",urlPatterns = "/dorm")
public class DormitoryServlet extends BaseServlet {

    //获取DormitoryService
    private DormitoryService dormitoryService = DormitoryFactory.getDormitoryService();

    /**
     * 转发到宿舍信息列表页面
     * @param request
     * @param response
     * @return
     */
    public String dormInfo(HttpServletRequest request, HttpServletResponse response) {

        //获取当前页码
        String currentPage = request.getParameter("currentPageNum");
        Long currentPageNum = 1L;
        if (currentPage != null) {
            currentPageNum = Long.parseLong(currentPage);
        }

        //默认每页显示10个宿舍信息
        Integer limit = 10;

        //获取条件
        Dormitory criteria = BeanTools.toBean(Dormitory.class, request.getParameterMap());
        //处理get编码
        criteria = BeanTools.endcoding(criteria);
        //获取宿舍分页信息
        PageBean<Dormitory> dormitoryPageBean = null;
        try {
//            dormitoryPageBean = dormitoryService.getPageBean(currentPageNum, limit);
            dormitoryPageBean = dormitoryService.getPageBeanByPrerequisite(criteria, currentPageNum, limit);
            //设置url
            dormitoryPageBean.setUrl(ServletTools.getUrl(request));

        } catch (DormitoryException e) {
            e.printStackTrace();
            //保存并转发错误信息
            request.setAttribute("msg",e.getMessage());
            return "/views/error.jsp";
        }
        //保存宿舍信息并转发
        request.setAttribute("pageBean", dormitoryPageBean);
        String isScore = request.getParameter("isScore");
        //判断是否需要转发到评分界面
        if ("true".equals(isScore)) {
            return "/views/dorm_score.jsp";
        } else {
            return "/views/dorm_info.jsp";
        }




    }


    /**
     * 转发到宿舍详细界面
     * @param request
     * @param response
     * @return
     */
    public String detailedInfo(HttpServletRequest request, HttpServletResponse response) {

        //获取宿舍ID
        String reqId = request.getParameter("id");
        if (reqId == null) {
            //保存并转发错误信息
            request.setAttribute("msg", "当前宿舍不存在！请刷新后重试！");
            return "/views/error.jsp";
        }

        Dormitory dormitory = null;
        try {
            dormitory = dormitoryService.getDormitoryById(Long.parseLong(reqId));
        } catch (DormitoryException e) {
            e.printStackTrace();
            //保存并转发错误信息
            request.setAttribute("msg",e.getMessage());
            return "/views/error.jsp";
        }
        //保存宿舍信息并转发
        request.setAttribute("dormitory", dormitory);
        return "/views/detailed_dorm.jsp";

    }

    /**
     * 转发到宿舍评分修改界面
     * @param request
     * @param response
     * @return
     */
    public String dormScore(HttpServletRequest request, HttpServletResponse response) {

        //获取当前宿舍ID
        String id = request.getParameter("id");

        //获取当前页码
        String currentPage = request.getParameter("currentPage");
        if (currentPage == null) {
            //如果没有页码，默认第一页
            currentPage = "1";
        }

        if (id == null) {
            request.setAttribute("msg", "请求参数异常！请刷新后重试！");
            return "/views/error.jsp";
        }
        //查询出宿舍的详细信息
        Dormitory dormitory;
        try {
            dormitory = dormitoryService.getDormitoryById(Long.parseLong(id));
        } catch (DormitoryException e) {
            request.setAttribute("msg", e.getMessage());
            return "views/error.jsp";
        }
        request.setAttribute("dormitory", dormitory);
        request.setAttribute("currentPage",currentPage);
        return "/views/edit_score.jsp";
    }

    /**
     * 修改宿舍评分信息
     * @param request
     * @param response
     * @return
     */
    public String editScore(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        String score = request.getParameter("score");
        if (id == null || score == null) {
            //保存错误信息并转发到错误页面
            request.setAttribute("msg", "评分信息参数异常！请重新填写！");
            return "/views/error.jsp";
        }
        //获取当前页码
        String current = request.getParameter("currentPage");
        Long currentPage = 1L;// 默认修改完成后，回到第一页
        if (current != null && !"".equals(current)) {
            currentPage = Long.parseLong(current);
        }

        try {
            dormitoryService.updateDormitoryScore(Double.parseDouble(score), Long.parseLong(id));
        } catch (DormitoryException e) {
            //保存错误信息并转发到错误页面
            request.setAttribute("msg", e.getMessage());
            return "/views/error.jsp";
        }


        Dormitory dormitory;
        try {
            dormitory = dormitoryService.getDormitoryById(Long.parseLong(id));
        } catch (DormitoryException e) {
            //保存错误信息并转发到错误页面
            request.setAttribute("msg", e.getMessage());
            return "/views/error.jsp";
        }
        //保存信息并转发
        request.setAttribute("dormitory",dormitory );
        request.setAttribute("msg","修改成功！<br/>3秒后返回<a href = '" + request.getContextPath() +"/dorm?m=dormInfo&isScore=true&currentPageNum="+ currentPage + "'>宿舍评分列表</a>");
        response.setHeader("refresh","3;url=" + request.getContextPath() +"/dorm?m=dormInfo&isScore=true&currentPageNum="+ currentPage );
        return "/views/edit_score.jsp";
    }
}
