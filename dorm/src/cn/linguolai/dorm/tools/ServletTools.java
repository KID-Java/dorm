package cn.linguolai.dorm.tools;

import javax.servlet.http.HttpServletRequest;

public class ServletTools {

    /**
     * 获取url
     * @param request
     * @return
     */
    public static String getUrl(HttpServletRequest request) {
        //获取路径
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();

        //如果存在页码，则截掉
        if (queryString.contains("&currentPageNum=")) {
            int index = queryString.lastIndexOf("&currentPageNum=");
            queryString = queryString.substring(0, index);
        }
        //拼接路径并返回
        return contextPath + servletPath + "?" + queryString;
    }

}
