package cn.linguolai.dorm.factory;

import cn.linguolai.dorm.dao.DormitoryDao;
import cn.linguolai.dorm.daoimpl.DormitoryDaoImpl;
import cn.linguolai.dorm.serviceimpl.DormitoryServiceImpl;
import cn.linguolai.dorm.service.DormitoryService;

public class DormitoryFactory {

    public static DormitoryDao getDormitoryDao() {
        return new DormitoryDaoImpl();
    }

    public static DormitoryService getDormitoryService() {
        return new DormitoryServiceImpl();
    }
}
