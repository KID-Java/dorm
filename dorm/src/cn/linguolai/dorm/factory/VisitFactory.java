package cn.linguolai.dorm.factory;

import cn.linguolai.dorm.dao.VisitDao;
import cn.linguolai.dorm.daoimpl.VisitDaoImpl;
import cn.linguolai.dorm.service.VisitService;
import cn.linguolai.dorm.serviceimpl.VisitServiceImpl;

public class VisitFactory {

    public static VisitDao getVisitDao() {
        return new VisitDaoImpl();
    }

    public static VisitService getVisitService() {
        return new VisitServiceImpl();
    }
}
