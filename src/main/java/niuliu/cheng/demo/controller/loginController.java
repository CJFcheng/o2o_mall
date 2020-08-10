package niuliu.cheng.demo.controller;


import niuliu.cheng.demo.entity.UserBean;
import niuliu.cheng.demo.service.UserService;
import niuliu.cheng.demo.until.Cookiecontrol;
import niuliu.cheng.demo.until.tengxunyun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class loginController {

    Cookiecontrol Cookie=new Cookiecontrol();

    class Sencode {
        String code;
        String phone;
    }

    Sencode repswcode = new Sencode();
    Sencode logincode = new Sencode();
    Sencode joincode = new Sencode();

    @Autowired
    private UserService userService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/repassword")
    public String repassword() {
        return "repassword";
    }

    @ResponseBody
    @PostMapping("/joinsencode")
    public String joinsencode(String phone, HttpServletResponse response) {
        UserBean userBean = userService.selectUser(phone);//手机号码查找数据库
        if (userBean != null)//已注册
            return "namexx";

        tengxunyun rzm = new tengxunyun();
        String rzstr = "";
        for (int i = 0; i <= 4; i++) {
            int rzpsw = (int) (Math.random() * 100 % 10);
            rzstr += Integer.toString(rzpsw);
        }
        rzm.sd(phone, rzstr, "注册");
        joincode.phone = phone;
        joincode.code = rzstr;
        return "senok";
    }

    @ResponseBody
    @PostMapping("/loginsencode")
    public String loginsencode(String phone, String type1, HttpServletResponse response) {
        UserBean userBean = userService.selectUser(phone);//手机号码查找数据库
        if (userBean == null)//未注册
            return "namexx";
        tengxunyun rzm = new tengxunyun();
        String rzstr = "";
        for (int i = 0; i <= 4; i++) {
            int rzpsw = (int) (Math.random() * 100 % 10);
            rzstr += Integer.toString(rzpsw);
        }


        if (type1.equals("login")) {
            rzm.sd(phone, rzstr, "登录");
            logincode.phone = phone;
            logincode.code = rzstr;

        }
        if (type1.equals("repsw")) {
            rzm.sd(phone, rzstr, "修改密码");
            repswcode.phone = phone;
            repswcode.code = rzstr;
        }

        return "senok";
    }

    @ResponseBody
    @PostMapping("/register")
    public String register(String phone, String name, String password, String code, HttpServletResponse response) {

        if (joincode.phone == null || joincode.code == null || !joincode.code.equals(code) || !joincode.phone.equals(phone))
            return "codexx";//验证码错误，申请验证码号码与当前提交手机号码不一致
        UserBean userBean = userService.selectUser(phone);//手机号码查找数据库
        if (userBean == null)//找不到东西就插入
        {
            UserBean user = new UserBean();
            user.setPassword(password);
            user.setPhone(phone);
            user.setUsername(name);
            int t = userService.insertUser(user);
            return "okway";
        } else return "namexx";
    }

    @ResponseBody
    @PostMapping("/rzlogin")
    public String relogin(String phone, String psw, String the_or, HttpServletRequest request,HttpServletResponse response) {
        if (the_or.equals("yzm"))//验证码方式登录
        {
            UserBean userBean = userService.selectUser(phone);//手机号码查找数据库
            if (userBean != null && phone.equals(logincode.phone) && psw.equals(logincode.code))//手机号码和验证码一致

            {   request.getSession().setAttribute("userphone", phone);
                Cookie.writeCookie(response, "userphone", phone);//生成简单cookie

                userService.insertR(userBean.getUsername(),phone);

                return "okway";
            }

        } else {
            UserBean userBean = userService.selectUser(phone);//查找数据库
            if (userBean != null && userBean.getPhone().equals(phone) && psw.equals(userBean.getPassword()))//号码和密码一致
            {
                request.getSession().setAttribute("userphone", phone);
                request.getSession().setMaxInactiveInterval(120*60);
                
                Cookie.writeCookie(response, "userphone", phone);//生成简单cookie

                userService.insertR(userBean.getUsername(),phone);



                return "okway";
            }
        }

        return "xxx";
    }

    @ResponseBody
    @PostMapping("/repsw")
    public String repsw(String phone, String password, String code, HttpServletResponse response) {
        if (repswcode.phone == null || repswcode.code == null || !repswcode.code.equals(code) || !repswcode.phone.equals(phone))
            return "codexx";//验证码错误，申请验证码号码与当前提交手机号码不一致
        UserBean userBean = userService.selectUser(phone);//手机号码查找数据库
        if (userBean != null)//找到东西就修改
        {
            int t = userService.updateUser(password, phone);
            return "okway";
        } else return "namexx";
    }




}
