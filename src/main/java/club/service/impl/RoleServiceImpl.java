package club.service.impl;

import club.dao.RoleDao;
import club.pojo.Role;
import club.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Override
    public List<Role> allRole() {
        return roleDao.allRole();
    }

    @Override
    public Role findById(Long id) {
        return roleDao.selectById(id);
    }
}
