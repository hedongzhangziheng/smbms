package club.service;

import club.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillService {
    int count(String productName, Integer providerId, Integer isPayment);
    List<Bill> showByPage(String productName, Integer providerId, Integer isPayment, Integer pageNum, Integer pageSize);
    int add(Bill bill);
    Bill findById(Long id);
    int modify(Bill bill);
    int del(Long id);
    long selectCountByProid(Long proid);
}
