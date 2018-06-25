package cn.linguolai.dorm.serviceimpl;

import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.bean.VisitInfo;
import cn.linguolai.dorm.dao.VisitDao;
import cn.linguolai.dorm.exception.VisitException;
import cn.linguolai.dorm.factory.VisitFactory;
import cn.linguolai.dorm.service.VisitService;

import java.util.List;

public class VisitServiceImpl implements VisitService {

    //获取VisitDao
    private VisitDao visitDao = VisitFactory.getVisitDao();

    @Override
    public PageBean<VisitInfo> getPageBean(Long currentPageNum, Integer limlit) throws VisitException {
        //验证分页参数，当参数不正确时，抛出异常
        if (currentPageNum == null || limlit == null) {
            throw new VisitException("分页参数不能为空！");
        } else if (currentPageNum <= 0 || limlit <= 0) {
            throw new VisitException("分页参数不能为负数！");
        }


        //创建空的分页数据
        PageBean<VisitInfo> visitInfoPageBean = new PageBean<VisitInfo>();

        //根据分页参数获取部分学生集合
        List<VisitInfo> visitInfos = visitDao.getPartVisitInfo((currentPageNum - 1) * limlit, limlit);
        //获取总来访记录数
        Long totalVisitInfoNum = visitDao.getTotalRecordNumber();
        //计算总页数
        Long totalPageNum = (totalVisitInfoNum - 1) / limlit + 1;


        //填充分页数据
        visitInfoPageBean.setCurrentPage(currentPageNum);
        visitInfoPageBean.setPageNumber(limlit);
        visitInfoPageBean.setTotalRecordNumber(totalVisitInfoNum);
        visitInfoPageBean.setToatlPageNumber(totalPageNum);
        visitInfoPageBean.setList(visitInfos);

        return visitInfoPageBean;
    }

    @Override
    public VisitInfo getVisitInfoById(Long id) {
        if (id == null) {
            return null;
        }
        return visitDao.getVisitInfoById(id);
    }

    @Override
    public void addVisitInfo(VisitInfo visitInfo) {
        if (visitInfo == null) {
            return ;
        }
        visitDao.addVisitInfo(visitInfo);
    }

    @Override
    public long getLastPageNum(Integer limit) {

        if (limit == null) {
            //默认最后一页是第一页
            return 1;
        }
        long totalPageNum = (visitDao.getTotalRecordNumber() - 1) / limit + 1;
        return totalPageNum;
    }

    @Override
    public VisitInfo getLastVisitInfo() {
        List<VisitInfo> visitInfos = visitDao.getPartVisitInfo(0L, 1);
        return visitInfos.get(0);
    }

}
