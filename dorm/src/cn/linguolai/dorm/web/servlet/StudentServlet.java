package cn.linguolai.dorm.web.servlet;

import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.exception.StudentException;
import cn.linguolai.dorm.factory.StudentFactory;
import cn.linguolai.dorm.service.StudentService;
import cn.linguolai.dorm.tools.BaseServlet;
import cn.linguolai.dorm.tools.BeanTools;
import cn.linguolai.dorm.tools.ServletTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "StudentServlet",urlPatterns = "/stu")
public class StudentServlet extends BaseServlet {


    //获取StudentServices示例
    private StudentService studentService = StudentFactory.getStudentService();


    /**
     * 转发到学生列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String stuInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取当前页码，当页码为空时，默认为第一页
        String currentPageNum = request.getParameter("currentPageNum");
        //Long型页码
        Long munber;
        if (currentPageNum == null) {
            munber = 1L;
        } else {
            munber = Long.parseLong(currentPageNum);
        }

        //每页Student数量 默认为10
        Integer limit = 10;

        //获取条件
        Student criteria = BeanTools.toBean(Student.class,request.getParameterMap());
        if (criteria == null) {
            criteria = new Student();
        }
        //处理get编码
        criteria = BeanTools.endcoding(criteria);
        //获取分页数据
        PageBean<Student> studentPageBean = null;

        try {
            studentPageBean = studentService.getPageBeanByPrerequisite(criteria,munber, limit);
            //设置url
            studentPageBean.setUrl(ServletTools.getUrl(request));
        } catch (StudentException e) {
            //保存并转发错误信息
            request.setAttribute("msg",e.getMessage());
//            request.getRequestDispatcher("views/error.jsp").forward(request,response);
            return "/views/error.jsp";
        }

        //将数据保存到request域并转发
        request.setAttribute("pageBean", studentPageBean);
//        request.getRequestDispatcher("views/stu_info.jsp").forward(request,response);
        return "/views/stu_info.jsp";

    }

    /**
     * 转发到修改学生信息界面
     * @param request
     * @param response
     * @return
     */
    public String toEdit(HttpServletRequest request, HttpServletResponse response) {

        //获取学生ID
        String stuId = request.getParameter("stuId");
        //验证请求参数
        if (stuId == null) {
            request.setAttribute("smg", "当前学生不存在！");
            return "/views/error.jsp";
        }
        //获取学生id
        Long id = Long.parseLong(stuId);

        //通过ID获取学生
        Student student = studentService.getStudentById(id);

        //获取当前页码
        String currentPage = request.getParameter("currentPage");
        currentPage = currentPage == null ? "1" : currentPage;

        //保存学生信息并转发
        request.setAttribute("student", student);
        request.setAttribute("currentPage", currentPage);
        return "/views/edit.jsp";
    }

    /**
     *  修改学生信息
     * @param request
     * @param response
     * @return
     */
    public String editStu(HttpServletRequest request, HttpServletResponse response) {
        //获取参数并存如Map集合
        Map result = request.getParameterMap();
        //通过Map直接转换成Bean对象
        Student student = BeanTools.toBean(Student.class, result);

        //获取当前页码
        String current = request.getParameter("currentPage");
        Long currentPage = 1L;// 默认修改完成后，回到第一页
        if (current != null && !"".equals(current.trim())) {
            currentPage = Long.parseLong(current);
        }
        try {
            studentService.updateStudent(student);
        } catch (StudentException e) {
            //保存错误信息并转发到错误页面
            request.setAttribute("msg", e.getMessage());
            return "/views/error.jsp";
        }
        //保存信息并转发
        request.setAttribute("student", student);
        request.setAttribute("msg","修改成功！<br/>3秒后返回<a href = '" + request.getContextPath() +"/stu?m=stuInfo&currentPageNum="+ currentPage + "'>学生列表</a>");
        response.setHeader("refresh","3;url="+ request.getContextPath() +"/stu?m=stuInfo&currentPageNum="+ currentPage );
        return "/views/edit.jsp";
    }

    /**
     * 删除学生
     * @param request
     * @param response
     * @return
     */
    public String deleteStu(HttpServletRequest request, HttpServletResponse response) {
        String stuId = request.getParameter("stuId");
        //验证参数信息
        if (stuId == null) {
            //保存错误信息并转发到错误页面
            request.setAttribute("msg", "请求参数异常！请刷新后重试！");
            return "/views/error.jsp";
        }
        //获取学生ID
        Long id = Long.parseLong(stuId);

        studentService.deleteStudentById(id);
        request.setAttribute("msg", "学生学号:" + id + ",删除成功！");

        //获取当前页码
        String current = request.getParameter("currentPage");
        Long currentPage = 1L;// 默认修改完成后，回到第一页
        if (current != null && !current.trim().isEmpty()) {
            currentPage = Long.parseLong(current);
        }

        return"/stu?m=stuInfo&currentPageNum="+ currentPage;
    }


}
