package niuliu.cheng.demo.until;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class indencontrol {

    @ResponseBody
    @RequestMapping(value = "/session")
    public String  getSession(HttpServletRequest request) {
        request.getSession().setAttribute("username", "chengtext");
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("sessionId", request.getSession().getId());
        return "dfg";
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public String get(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("username");

        return userName;
    }


}