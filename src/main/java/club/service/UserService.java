package club.service;

import club.pojo.User;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface UserService {
    User login(String name, String password);
    Page<User> showByPage(String queryname, Long queryUserRole, Integer pageNum, Integer pageSize);
    int add(User user);
    User findById(Long id);
    int modify(User user);
    int del(Long id);
    User isExist(String userCode);
    int pwdmodify(User user);
}
