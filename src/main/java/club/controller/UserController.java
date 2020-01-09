package club.controller;

import club.pojo.Role;
import club.pojo.User;
import club.service.RoleService;
import club.service.UserService;
import club.pojo.vo.Result;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @RequestMapping("/user")
    public String bill(HttpSession session, String queryname, Long queryUserRole, Integer pageIndex){
        int pageSize = 3;
        List<Role> roleList = roleService.allRole();
        if(pageIndex == null) pageIndex = 1;
        Page<User> page = userService.showByPage(queryname, queryUserRole, pageIndex, pageSize);
        session.setAttribute("totalCount", page.getTotal());//总条数
        session.setAttribute("queryUserName", queryname);
        session.setAttribute("queryUserRole", queryUserRole);
        session.setAttribute("roleList", roleList);
        session.setAttribute("totalPageCount", page.getPages());
        session.setAttribute("currentPageNo", page.getCurrent());
        session.setAttribute("userList", page.getRecords());
        return "user/userlist";
    }

    @RequestMapping("/useradd")
    public String useradd(){
        return "user/useradd";
    }

    @RequestMapping("/saveuser")
    public String douseradd(User user){
        userService.add(user);
        return "redirect:/sys/user";
    }

    @RequestMapping("/getrolelist")
    @ResponseBody
    public List<Role> allRole(){
        return roleService.allRole();
    }

    @RequestMapping("/usercode")
    @ResponseBody
    public Result usercode(String userCode){
        Result result = new Result();
        User user = userService.isExist(userCode);
        if(user != null) result.setUserCode("exist");
        return result;
    }

    @RequestMapping("/viewUser/{id}")
    public String billview(HttpSession session ,@PathVariable Long id){
        User user = userService.findById(id);
        user.setRole(roleService.findById(user.getUserrole()));
        session.setAttribute("user", user);
        return "user/userview";
    }

    @RequestMapping("/modifyUser")
    public String modify(HttpSession session ,Long uid){
        User user = userService.findById(uid);
        user.setRole(roleService.findById(user.getUserrole()));
        session.setAttribute("user", user);
        return "user/usermodify";
    }

    @RequestMapping("/modifyusersave")
    public String domodify(User user){
        userService.modify(user);
        return "redirect:/sys/user";
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Object billdel(Long uid){
        Result result = new Result();
        User user = userService.findById(uid);
        if(user == null){
            result.setDelResult("notexist");
        }else {
            int del = userService.del(uid);
            if(del == 0){
                result.setDelResult("false");
            }else{
                result.setDelResult("true");
            }
        }
        return result;
    }

    @RequestMapping("/pwdmodify")
    public String pwdmodify(){
        return "user/pwdmodify";
    }

    @RequestMapping("/checkpwd")
    @ResponseBody
    public Result checkpwd(HttpSession session, String oldpassword){
        Result result = new Result();
        User user = (User)session.getAttribute("host");
        if(user == null){
            result.setResult("sessionerror");
        }else if(oldpassword == null || oldpassword.equals("")){
            result.setResult("error");
        }else if(!oldpassword.equals(user.getUserpassword())){
            result.setResult("false");
        }else {
            result.setResult("true");
        }
        return result;
    }

    @RequestMapping("/savepwdmodify")
    public String savepwdmodify(HttpSession session, String newpassword){
        User host = (User)session.getAttribute("host");
        host.setUserpassword(newpassword);
        int i = userService.pwdmodify(host);
        if(i == 1){
            return "redirect:/user/login";
        }else {
            session.setAttribute("message","密码修改失败");
            return "redirect:/sys/pwdmodify";
        }
    }
}
