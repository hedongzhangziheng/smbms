package club.service.impl;

import club.dao.UserDao;
import club.pojo.User;
import club.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional//(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User login(String name, String password) {
        User u = new User();
        u.setUsercode(name);
        User user = userDao.selectOne(u);
        if(user != null && user.getUserpassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public Page<User> showByPage(String queryname, Long queryUserRole, Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<User>(pageNum, pageSize);
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        if(queryname != null && !queryname.equals("")) wrapper.like("userName", queryname);
        if(queryUserRole != null && queryUserRole != 0) wrapper.eq("userRole", queryUserRole);
        List<User> userList = userDao.selectPage(page, wrapper);
        page.setRecords(userList);
        return page;
    }

    @Override
    public int add(User user) {
        return userDao.insert(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.selectById(id);
    }

    @Override
    public int modify(User user) {
        return userDao.updateById(user);
    }

    @Override
    public int del(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public User isExist(String userCode) {
        User user = new User();
        user.setUsercode(userCode);
        return userDao.selectOne(user);
    }

    @Override
    public int pwdmodify(User user) {
        return userDao.updateById(user);
    }


}
