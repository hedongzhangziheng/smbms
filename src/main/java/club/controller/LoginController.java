package club.controller;

import club.pojo.User;
import club.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public String dologin(HttpSession session, @RequestParam(required = false) String userCode, @RequestParam(required = false) String password){
        User user = userService.login(userCode, password);
        if(user == null){
            session.setAttribute("error", "用户名或密码错误！");
            return "redirect:/user/login";
        }else {
            session.removeAttribute("error");
            session.setAttribute("host", user);
            return "redirect:/user/frame";
        }
    }

    @RequestMapping("/frame")
    public String frame(){
        return "frame";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
