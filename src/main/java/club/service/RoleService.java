package club.service;

import club.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> allRole();
    Role findById(Long id);
}
