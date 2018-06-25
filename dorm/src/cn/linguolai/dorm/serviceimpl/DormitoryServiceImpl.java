package cn.linguolai.dorm.serviceimpl;

import cn.linguolai.dorm.bean.Dormitory;
import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.dao.DormitoryDao;
import cn.linguolai.dorm.exception.DormitoryException;
import cn.linguolai.dorm.exception.StudentException;
import cn.linguolai.dorm.factory.DormitoryFactory;
import cn.linguolai.dorm.service.DormitoryService;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.List;

public class DormitoryServiceImpl implements DormitoryService {

    //获取DormitoryDao
    private DormitoryDao dormitoryDao = DormitoryFactory.getDormitoryDao();

    @Override
    public PageBean<Dormitory> getPageBean(Long currentPageNum, Integer limlit) throws DormitoryException {
        //验证分页参数，当参数不正确时，抛出异常
        if (currentPageNum == null || limlit == null) {
            throw new DormitoryException("分页参数不能为空！");
        } else if (currentPageNum <= 0 || limlit <= 0) {
            throw new DormitoryException("分页参数不能为负数！");
        }


        //创建空的分页数据
        PageBean<Dormitory>  dormitoriesPageBean = new PageBean<Dormitory>();

        //根据分页参数获取部分宿舍集合
        List<Dormitory> dormitories = dormitoryDao.getPartDormitory((currentPageNum - 1) * limlit, limlit);
        //获取总宿舍数
        Long totalStudentNum = dormitoryDao.getTotalRecordNumber();
        //计算总页数
        Long totalPageNum = (totalStudentNum - 1) / limlit + 1;


        //填充分页数据
        dormitoriesPageBean.setCurrentPage(currentPageNum);
        dormitoriesPageBean.setPageNumber(limlit);
        dormitoriesPageBean.setTotalRecordNumber(totalStudentNum);
        dormitoriesPageBean.setToatlPageNumber(totalPageNum);
        dormitoriesPageBean.setList(dormitories);

        return dormitoriesPageBean;
    }

    @Override
    public PageBean<Dormitory> getPageBeanByPrerequisite(Dormitory criteria, Long currentPageNum, Integer limit) throws DormitoryException {
        //验证分页参数，当参数不正确时，抛出异常
        if (currentPageNum == null || limit == null) {
            throw new DormitoryException("分页参数不能为空！");
        } else if (currentPageNum <= 0 || limit <= 0) {
            throw new DormitoryException("分页参数不能为负数！");
        }


        //当条件为空时，相当于无条件查询
        if (criteria == null) {
            criteria = new Dormitory();
        }

        //创建空的分页数据
        PageBean<Dormitory> dormitoryPageBean = new PageBean<Dormitory>();
        //根据分页参数获取部分宿舍集合
        List<Dormitory> dormitories = dormitoryDao.getDormitoryByPrerequisite(criteria,(currentPageNum - 1) * limit, limit);
        //获取总宿舍数
        Long totalStudentNum = dormitoryDao.getTotalRecordNumberByPrerequisite(criteria);
        //计算总页数
        Long totalPageNum = (totalStudentNum - 1) / limit + 1;


        //填充分页数据
        dormitoryPageBean.setCurrentPage(currentPageNum);
        dormitoryPageBean.setPageNumber(limit);
        dormitoryPageBean.setTotalRecordNumber(totalStudentNum);
        dormitoryPageBean.setToatlPageNumber(totalPageNum);
        dormitoryPageBean.setList(dormitories);

        return dormitoryPageBean;
    }

    @Override
    public Dormitory getDormitoryById(Long id) throws DormitoryException {

        if (id == null) {
            throw new DormitoryException("请求参数异常！请刷新后重试！");
        }
        return dormitoryDao.getDormitoryById(id);
    }

    @Override
    public void addDormitory(Dormitory dormitory) {

    }

    @Override
    public void updateDormitoryScore(Double score, Long id) throws DormitoryException {
        if (score == null || id == null) {
            throw new DormitoryException("评分信息参数异常！请重新填写！");
        }
        dormitoryDao.updateDormitoryScore(score, id);
    }
}
