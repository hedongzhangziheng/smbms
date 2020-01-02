package club.service.impl;

import club.dao.BillDao;
import club.pojo.Bill;
import club.service.BillService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

@Service
@Transactional
public class BillserviceImpl implements BillService {

    @Resource
    private BillDao billDao;

    @Override
    public int count(String productName, Integer providerId, Integer isPayment) {
        return billDao.count(productName, providerId, isPayment);
    }

    @Override
    public List<Bill> showByPage(String productName, Integer providerId, Integer isPayment, Integer pageNum, Integer pageSize) {
        List<Bill> billList = billDao.showByPage(productName, providerId, isPayment, (pageSize * (pageNum - 1)), pageSize);
        return billList;
    }

    @Override
    public int add(Bill bill) {
        return billDao.insert(bill);
    }

    @Override
    public Bill findById(Long id) {
        return billDao.findById(id);
    }

    @Override
    public int modify(Bill bill) {
        return billDao.updateById(bill);
    }

    @Override
    public int del(Long id) {
        return billDao.deleteById(id);
    }

    @Override
    public long selectCountByProid(Long proid) {
        EntityWrapper<Bill> wrapper = new EntityWrapper<Bill>();
        wrapper.eq("providerId", proid);
        return billDao.selectCount(wrapper);
    }


}
