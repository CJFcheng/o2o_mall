package niuliu.cheng.demo.controller;

import niuliu.cheng.demo.entity.*;
import niuliu.cheng.demo.service.*;
import niuliu.cheng.demo.until.Cookiecontrol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewshopController {

    @Autowired
    private Shop_statusService shop_statusService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private DingdanService dingdanService;
    @Autowired
    private loveService loveservice;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    Cookiecontrol Cookie =new Cookiecontrol();

    @GetMapping("/index")
    public String index( HttpServletRequest request,Model model)throws Exception {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "login2";
        }

        List<Commodity> commodityList=commodityService.alllist();

        List<Commodity> list= new ArrayList<Commodity>();
        int xout=8;
        if (commodityList.size()<xout)
            xout=commodityList.size();
        int []a=new int[commodityList.size()-1];
        int ran2=0;
        for(int i=0;i<xout;i++){

             ran2 = (int) (Math.random()*(commodityList.size()-1));
            while(a[ran2]!=0){
                ran2 = (int) (Math.random()*(commodityList.size()-1));
            }

            a[ran2]=1;
            list.add(commodityList.get(ran2));

        }

        List<Commodity> list2= new ArrayList<Commodity>();
        xout=3;
        if (commodityList.size()<xout)
            xout=commodityList.size();
        int []b=new int[commodityList.size()-1];

        for(int i=0;i<xout;i++){

            ran2 = (int) (Math.random()*(commodityList.size()-1));
            while(b[ran2]!=0){
                ran2 = (int) (Math.random()*(commodityList.size()-1));
            }

            b[ran2]=1;
            list2.add(commodityList.get(ran2));

        }
        model.addAttribute("list2",list2);
        model.addAttribute("list",list);

        List<loveBean> lovebeans=loveservice.seall(sessioning);
        model.addAttribute("sc",lovebeans);
        UserBean userBean=userService.selectUser(sessioning);
        model.addAttribute("user",userBean);

        return "index";
    }
    @GetMapping("/find/{name}")
    public String fing(@PathVariable String name, Model model, HttpServletRequest request){


        name="%"+name+"%";

       List<Commodity> commodityList=commodityService.mohulist(name);

        model.addAttribute("list",commodityList);

        return "find";
    }
    @GetMapping("/finlei/{name}")
    public String finlei(@PathVariable String name, Model model, HttpServletRequest request){

        List<Commodity> commodityList=commodityService.leilist(name);

        model.addAttribute("list",commodityList);

        return "find";
    }
    @GetMapping("/userdd")//用户订单
    public String userdd(HttpServletRequest request,Model model){
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "login2";
        }
        List<Dingdan> allDD=dingdanService.seluallDD(cookeing1);
        List<Dingdan> yDD=dingdanService.seluDD(cookeing1,"y");
        List<Dingdan> nDD=dingdanService.seluDD(cookeing1,"n");
        model.addAttribute("alldd",allDD);
        model.addAttribute("ydd",yDD);
        model.addAttribute("ndd",nDD);
        return "userdd";
    }
    @GetMapping("/shopview/{shopname}")//商店页面
    public String viewshop(@PathVariable String shopname, Model model, HttpServletRequest request) {

        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "login2";
        }


        Shop shop = shopService.getname(shopname);
        Shop_status shop_status = shop_statusService.getall(shopname);
        String shoplei = shop_status.getShoplei();//获取店内分类内容
        String[] lei = shoplei.split(",");//转化成字符串数组
        String op = "是";
        List<Commodity>[] list = new ArrayList[lei.length];
        for (int i = 0; i < lei.length; i++) {
            list[i] = commodityService.cmdtlist(shopname, lei[i], op);
        }

        // String activity = thetext + "-%%%-" + betime1 + "-%%%-" + betime2 + "-%%%-" + entime1 + "-%%%-" + entime2;
        String[] active = shop_status.getActivity().split("-%%%-");
        model.addAttribute("activ", active);
        model.addAttribute("shop_status", shop_status);
        model.addAttribute("shop", shop);//商店资料
        model.addAttribute("list", list);//商品列表链表
        model.addAttribute("shoplei", lei);//类别列表数组
        return "shopview";
    }


    @GetMapping("/spxq/{id}")
    public String spxq(@PathVariable String id, Model model, HttpServletRequest request) {

        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "login2";
        }

        AddressBean adde=addressService.seaddress(cookeing1);
        model.addAttribute("address",adde);


        Commodity commodity = commodityService.getcommodity(id);
        Shop shop=shopService.getname(commodity.getShopName());
        String addressid=shop.getAddressID();
        loveBean lovebeans=loveservice.selove(sessioning,id);



        model.addAttribute("sc",lovebeans);
        model.addAttribute("addressid",addressid);
        model.addAttribute("commodity", commodity);
        return "the_spxq";
    }

    @ResponseBody
    @PostMapping("/addDD")
    public String addDD2(Dingdan dingdan, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "login2";
        }

        Shop shop = shopService.getname(dingdan.getShopName());
        dingdan.setShopaddress(shop.getAddressName());
        dingdan.setShopphone(shop.getUserPhone());
        dingdan.setUserid(sessioning);
        dingdan.setShopid(shop.getUser_id());

       int a= dingdanService.insertDD(dingdan);


        return "addDDok";
    }

    @ResponseBody//添加收藏
    @PostMapping("/joinsc")
    public String joinsc(loveBean love, HttpServletRequest request){
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            love.setUserid(sessioning);
            loveservice.insert(love);

            return "ok";
        } else return "notok";
    }

    @ResponseBody//删除收藏
    @PostMapping("/deletesc")
    public String deleteid(String spid, HttpServletRequest request){
    String cookeing1= Cookie.getCookie(request,"userphone");
    String sessioning=(String) request.getSession().getAttribute("userphone");
    if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
       loveservice.deletesp(sessioning,spid);
        return "ok";
    } else return "notok";
    }

    @ResponseBody
    @PostMapping("/repassword")
    public String repassword(String old, String new1,HttpServletRequest request, HttpServletResponse response){
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {

            UserBean userBean=userService.selectUser(sessioning);
            if (old.equals(userBean.getPassword()))
            {
                userService.updateUser(new1,sessioning);
                return "ok";
            }
            else return "not";
        }
        else return "notok";
    }

    @GetMapping("/address")
    public String addressget(HttpServletRequest request,Model model){
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "login2";
        }

        AddressBean adde=addressService.seaddress(cookeing1);
        model.addAttribute("address",adde);


        return "address";
    }
    @ResponseBody
    @PostMapping("/address")
    public String address(AddressBean addressBean,HttpServletRequest request, HttpServletResponse response){

        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            addressBean.setUserid(cookeing1);
            AddressBean adde=addressService.seaddress(cookeing1);
             if(adde==null)
                 addressService.inaddress(addressBean);
             else
                 addressService.upaddress(addressBean);


                return "ok";
        }
        else return "notok";

    }




}
