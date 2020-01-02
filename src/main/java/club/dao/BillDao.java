package club.dao;

import club.pojo.Bill;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillDao extends BaseMapper<Bill> {
    int count(@Param("productName") String productName, @Param("providerId") Integer providerId,
              @Param("isPayment") Integer isPayment);
    List<Bill> showByPage(@Param("productName") String productName, @Param("providerId") Integer providerId,
                          @Param("isPayment") Integer isPayment, @Param("start")Integer start,
                          @Param("pageSize") Integer pageSize);
    Bill findById(Long id);
}
