package club.dao;

import club.pojo.Provider;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface ProviderDao extends BaseMapper<Provider> {
    List<Provider> all();
}
