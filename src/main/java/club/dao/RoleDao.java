package club.dao;

import club.pojo.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface RoleDao extends BaseMapper<Role> {
    List<Role> allRole();
}
