package niuliu.cheng.demo.controller;

import niuliu.cheng.demo.entity.Commodity;
import niuliu.cheng.demo.entity.Dingdan;
import niuliu.cheng.demo.entity.Shop;
import niuliu.cheng.demo.entity.Shop_status;
import niuliu.cheng.demo.service.CommodityService;
import niuliu.cheng.demo.service.DingdanService;
import niuliu.cheng.demo.service.ShopService;
import niuliu.cheng.demo.service.Shop_statusService;
import niuliu.cheng.demo.until.Cookiecontrol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class Shop_statusContrller {
    @Autowired
    private Shop_statusService shop_statusService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private DingdanService dingdanService;


    Cookiecontrol Cookie =new Cookiecontrol();

///////////////////////////////////////////////////////////////////////////////////////////////////////商家中心

    @GetMapping("/shopcenter")//商家中心
    public String index(Model model, Shop_status shop_status, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
                  return "redirect:/login";
        }

        Shop shop=shopService.getUserName(sessioning);
        if (shop==null)
            return "redirect:/vip";

        String spic=shop.getPictureOne();
        model.addAttribute("pic",spic);

        shop_status = shop_statusService.getall(shop.getShopName());//获取该商店所有状态
        List<Dingdan> DD=dingdanService.selectDD(cookeing1,"n");//订单列表
        String str = shop_status.getShoplei();
        String[] lei = str.split(",");//单独生成lei数组
        str = shop_status.getActivity();
        String[] activity = str.split("-%%%-");//单独生成activity数组
        model.addAttribute("dingdan",DD);
        model.addAttribute("activity", activity);
        model.addAttribute("lists", shop_status);
        model.addAttribute("lei", lei);
        return "shopcenter";

    }

    @GetMapping("/outlogin")
    public  String outlogin(HttpServletRequest request){

        request.getSession().removeAttribute("userphone");

        return "login2";

    }
    ///////添加店内类别
    @ResponseBody
    @PostMapping("/addlei")
    public String conshoplei(String addnewlei, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();


            addnewlei = addnewlei.replace("，", ",");
            String shoplei = shop_statusService.getshoplei(shopName);//获取店内分类内容
            String[] lei = shoplei.split(",");//转化成字符数组
            String[] newlei = new String[lei.length + 1];
            for (int i = 0; i < lei.length; i++)
                newlei[i] = lei[i];
            newlei[lei.length] = addnewlei;//添加新分类到数组尾部

            String newshoplei = new String();//将数据转化成字符串
            for (int i = 0; i < newlei.length; i++)
                newshoplei += newlei[i] + ",";
            int a = shop_statusService.upshop_lei(shopName, newshoplei);//更新数据库分类
            return "ok";
        } else return "notok";
    }

    /////删除idlei位置上的类
    @ResponseBody
    @PostMapping("/deletelei")
    public String deletelei(String idlei, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();
            int id = Integer.parseInt(idlei);
            String shoplei = shop_statusService.getshoplei(shopName);//获取店内分类内容
            String[] lei = shoplei.split(",");//转化成字符串数组
            String[] newlei = new String[lei.length - 1];
            int t = -1, j = -1;
            while (t < newlei.length) {//除了要删的位置，其他赋给新数组
                j++;
                t++;
                if (id == t) {
                    j--;
                    int updata = shop_statusService.upcommoditilei(lei[id], shopName);//将该店铺此分类下的商品改到默认
                    continue;
                } else {
                    newlei[j] = lei[t];
                }
            }
            String newshoplei = new String();//将数据转化成字符串
            for (int i = 0; i < newlei.length; i++)
                newshoplei += newlei[i] + ",";
            int a = shop_statusService.upshop_lei(shopName, newshoplei);//更新数据库分类
            return "ok";
        } else return "notok";

    }

    /////确认订单
    @ResponseBody
    @PostMapping("/TokDD")
    public String TokDD(String id, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {


            dingdanService.TokDD(id);


            return "ok";
        } else return "notok";

    }

    /////更新某位置上的类别
    @ResponseBody
    @PostMapping("/uplei")
    public String upthis(String idlei, HttpServletRequest request, String uptext) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();
            int id = Integer.parseInt(idlei);
            String shoplei = shop_statusService.getshoplei(shopName);//获取店内分类内容
            String[] lei = shoplei.split(",");//转化成字符串数组

            int b = shop_statusService.upcommoditynewlei(lei[id], uptext, shopName);
            lei[id] = uptext;
            String newshoplei = new String();//将数据转化成字符串
            for (int i = 0; i < lei.length; i++)
                newshoplei += lei[i] + ",";
            int a = shop_statusService.upshop_lei(shopName, newshoplei);//更新数据库分类
            return "ok";
        } else return "notok";

    }




    /////改变类别的顺序
    @ResponseBody
    @PostMapping("/upsx")
    public String upsx(String maxid, String minid, HttpServletRequest request) {
        int max = Integer.parseInt(maxid);
        int min = Integer.parseInt(minid);
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();


            String shoplei = shop_statusService.getshoplei(shopName);//获取店内分类内容
            String[] lei = shoplei.split(",");//转化成字符串数组
            String str = lei[max];
            lei[max] = lei[min];
            lei[min] = str;
            String newshoplei = new String();//转成字符串
            for (int i = 0; i < lei.length; i++)
                newshoplei += lei[i] + ",";
            int a = shop_statusService.upshop_lei(shopName, newshoplei);//更新数据库分类
            return "ok";
        } else return "notok";
    }

    /////更新公告
    @ResponseBody
    @PostMapping("/upgonggao")
    public String upgonggao(String gonggao, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();


            int a = shop_statusService.upgonggao(gonggao, shopName);
            return "ok";
        } else return "notok";
    }

    ///////更新活动
    @ResponseBody
    @PostMapping("/upactivity")
    public String upactivity(String thetext, String betime1, String betime2, String entime1, String entime2, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();



            String activity = thetext + "-%%%-" + betime1 + "-%%%-" + betime2 + "-%%%-" + entime1 + "-%%%-" + entime2;
            int a = shop_statusService.upactivity(activity, shopName);
            return "ok";
        } else return "notok";
    }

    //////更新营业时间
    @ResponseBody
    @PostMapping("/uptime")
    public String uptime(String betime, String entime, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();

            int a = shop_statusService.uptime(betime, entime, shopName);
            return "ok";
        } else return "notok";
    }

    //////更新状态
    @ResponseBody
    @PostMapping("/upsp_status")
    public String upsp_status(String spstatus, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();


            if (spstatus.equals("开启") || spstatus.equals("关闭")) {
                int a = shop_statusService.upsp_status(spstatus, shopName);
                return "ok";
            }
            return "error";
        } else return "notok";
    }

////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////商品管理

    //////添加商品页面
    @GetMapping("/add")
    public String add(Model model, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();


            String shoplei = shop_statusService.getshoplei(shopName);//获取本店内分类内容
            String[] lei = shoplei.split(",");//转化成字符串数组
            model.addAttribute("lei", lei);
            return "add";
        } else
            return "login";

    }

    ///////查看商品详细信息
    @GetMapping("/s_commodity/{id}")
    public String s_commodity(@PathVariable String id, Model model, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();
            Commodity cmdt = commodityService.getcommodity(id);//获取本商品信息
            String shoplei = shop_statusService.getshoplei(shopName);//获取店内分类内容
            String[] lei = shoplei.split(",");//转化成字符串数组
            model.addAttribute("lei", lei);
            model.addAttribute("cmdt", cmdt);
            return "add";
        } else return "login";
    }

    ///////添加商品
    @PostMapping("/addCommodity")
    public String addCommodity(Commodity commodity, @RequestParam("picture_sp") MultipartFile file[], Model model, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();

            commodity.setShopName(shopName);

            commodity.setPicture_sp1("");//页面传输的图片地址置空
            commodity.setPicture_sp2("");
            commodity.setPicture_sp3("");
            //对三张图片进行传输操作
            for (int i = 0; i <= 2; i++) {
                if (file[i].isEmpty()) {
                    System.out.println("文件为空");
                } else {
                    String fileName = file[i].getOriginalFilename();  // 文件名
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
                    String filePath = "C://inetpub//wwwroot//ToARD//commodity//" + shopName + "//"; // 上传后的路径
                    fileName = UUID.randomUUID() + suffixName; // 新文件名
                    File dest = new File(filePath + fileName);
                    if (i == 0)//数据库存储路径
                        commodity.setPicture_sp1("commodity/" + shopName + "/" + fileName);
                    else if (i == 1)
                        commodity.setPicture_sp2("commodity/" + shopName + "/" + fileName);
                    else if (i == 2)
                        commodity.setPicture_sp3("commodity/" + shopName + "/" + fileName);

                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    try {
                        file[i].transferTo(dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            Integer cmdt = commodityService.insertcommodity(commodity);//向数据库插入新商品信息

            return "add";
        } else return "login";
    }

    ///////修改
    @PostMapping("/upCommodity/{id}")
    public String upCommodity(@PathVariable String id, Commodity commodity, @RequestParam("picture_sp") MultipartFile file[], Model model, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1!=null&&sessioning!=null&&sessioning.equals(cookeing1)) {
            Commodity cmdt = commodityService.getcommodity(id);//通过id查询得到商品全部信息
            commodity.setId(cmdt.getId());
            commodity.setPicture_sp1(cmdt.getPicture_sp1());
            commodity.setPicture_sp2(cmdt.getPicture_sp2());
            commodity.setPicture_sp3(cmdt.getPicture_sp3());
            String u_name = cmdt.getShopName();
            for (int i = 0; i <= 2; i++) {
                if (file[i].isEmpty()) {
                    System.out.println("文件为空");
                } else {
                    String fileName = file[i].getOriginalFilename();  // 文件名
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
                    String filePath = "C://inetpub//wwwroot//ToARD//commodity//" + u_name + "//"; // 上传后的路径
                    fileName = UUID.randomUUID() + suffixName; // 新文件名
                    File dest = new File(filePath + fileName);
                    if (i == 0)//数据库存储路径
                        commodity.setPicture_sp1("commodity/" + u_name + "/" + fileName);
                    else if (i == 1)
                        commodity.setPicture_sp2("commodity/" + u_name + "/" + fileName);
                    else if (i == 2)
                        commodity.setPicture_sp3("commodity/" + u_name + "/" + fileName);

                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    try {
                        file[i].transferTo(dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            commodityService.updatecommodity(commodity);
            if (commodity.getOp().equals("是"))
                return "redirect:/cmdtlist/shi";
            else
                return "redirect:/cmdtlist/fou";
        } else return "login";
    }

    ////显示商品列表页面，op代表上架下架状态
    @GetMapping("/cmdtlist/{op}")
    public String comdtlist(@PathVariable String op, Model model, HttpServletRequest request) {

        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "redirect:/login";
        }
            Shop shop=shopService.getUserName(sessioning);
            String shopName=shop.getShopName();


            String shoplei = shop_statusService.getshoplei(shopName);//获取店内分类内容
            String[] lei = shoplei.split(",");//转化成字符串数组
            String[] show = new String[2];
            if (op.equals("shi"))
                op = "是";
            else if (op.equals("fou"))
                op = "否";
            else
                return "login";
            if (op.equals("是")) {
                show[0] = "已上架商品";
                show[1] = "下架";
            } else {
                show[0] = "待上架商品";
                show[1] = "上架";
            }
            List<Commodity>[] list = new ArrayList[lei.length];
            for (int i = 0; i < lei.length; i++) {
                list[i] = commodityService.cmdtlist(shopName, lei[i], op);
            }
            model.addAttribute("list", list);//商品列表
            model.addAttribute("shoplei", lei);//类别列表
            model.addAttribute("show", show);//上下架标签
            return "commodity_list";

    }

    ////////修改商品op
    @ResponseBody
    @PostMapping("/upop")
    public String upop(String op, String id, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "notok";
        }
        Shop shop=shopService.getUserName(sessioning);
        String shopname=shop.getShopName();
        Commodity commodity=commodityService.getcommodity(id);

        if(commodity.getShopName().equals(shopname)){
            if (op.equals("是"))
                op = "否";
            else op = "是";
            int a = commodityService.idupop(op, id);
            return "ok";
        }else
            return "notok";
    }

    ////////删除商品
    @ResponseBody
    @PostMapping("/deletesp")
    public String deletesp(String id, HttpServletRequest request) {
        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "notok";
            }
            Shop shop=shopService.getUserName(sessioning);
            String shopname=shop.getShopName();
            Commodity commodity=commodityService.getcommodity(id);

            if(commodity.getShopName().equals(shopname)){
                int a = commodityService.deletesp(id);
                return "ok";
            }else
                return "notok";

    }
/////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////




    @GetMapping("/vip")

    public String set_Shop(Model model, HttpServletRequest request) {

        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "redirect:/login";
        }
        Shop shop=shopService.getUserName(sessioning);

        model.addAttribute("vip", shop);

        return "vip";
    }

    //注册店铺
    @PostMapping("/takevip")
    public String takevip(Shop shop, @RequestParam("pic") MultipartFile file[], Model model, HttpServletRequest request) {


        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "redirect:/login";
        }
        shop.setPictureOne("");
        shop.setPictureTwo("");
        shop.setPictureThree("");
        shop.setIdcardOne("");
        shop.setIdcardTwo("");
        shop.setMustPicture("");
        shop.setShoplei("");
        shop.setUser_id(sessioning);


        for (int i = 0; i <= 5; i++) {
            if (file[i].isEmpty()) {
                System.out.println("文件为空");
            } else {
                String fileName = file[i].getOriginalFilename();  // 文件名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
                String filePath = "C://inetpub//wwwroot//ToARD//temp-vip//" + shop.getUser_id() + "//"; // 上传后的路径
                fileName = UUID.randomUUID() + suffixName; // 新文件名
                File dest = new File(filePath + fileName);
                if (i == 0)//数据库存储路径
                    shop.setPictureOne("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 1)
                    shop.setPictureTwo("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 2)
                    shop.setPictureThree("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 3)
                    shop.setIdcardOne("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 4)
                    shop.setIdcardTwo("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 5)
                    shop.setMustPicture("temp-vip/" + shop.getUser_id() + "/" + fileName);

                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    //保存文件
                    file[i].transferTo(dest);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        Integer cmdt = shopService.insertshop(shop);
        model.addAttribute("vip", shop);

        Shop_status shop_status=new Shop_status(shop.getShopName(),"待发布","00:00:00","00:00:00","关闭","暂无活动-%%%-xxx-%%%-xxx-%%%-xxx-%%%-xxx","");
        shop_statusService.insertshop_status(shop_status);


        return "redirect:/shopcenter";
    }
    //修改店铺信息
    @PostMapping("/upshopvip")
    public String upvip(Shop shop, @RequestParam("pic") MultipartFile file[], Model model, HttpServletRequest request) {

        String cookeing1= Cookie.getCookie(request,"userphone");
        String sessioning=(String) request.getSession().getAttribute("userphone");
        if (cookeing1==null||sessioning==null||!sessioning.equals(cookeing1)) {
            return "redirect:/login";
        }

        Shop sop=shopService.getUserName(sessioning);

        shop.setPictureOne(sop.getPictureOne());
        shop.setPictureTwo(sop.getPictureTwo());
        shop.setPictureThree(sop.getPictureThree());
        shop.setIdcardOne(sop.getIdcardOne());
        shop.setIdcardTwo(sop.getIdcardTwo());
        shop.setMustPicture(sop.getMustPicture());

        shop.setUser_id(sop.getUser_id());


        for (int i = 0; i <= 5; i++) {
            if (file[i].isEmpty()) {
                System.out.println("文件为空");
            } else {
                String fileName = file[i].getOriginalFilename();  // 文件名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
                String filePath = "C://inetpub//wwwroot//ToARD//temp-vip//" + shop.getUser_id() + "//"; // 上传后的路径
                fileName = UUID.randomUUID() + suffixName; // 新文件名
                File dest = new File(filePath + fileName);
                if (i == 0)//数据库存储路径
                    shop.setPictureOne("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 1)
                    shop.setPictureTwo("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 2)
                    shop.setPictureThree("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 3)
                    shop.setIdcardOne("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 4)
                    shop.setIdcardTwo("temp-vip/" + shop.getUser_id() + "/" + fileName);
                else if (i == 5)
                    shop.setMustPicture("temp-vip/" + shop.getUser_id() + "/" + fileName);

                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    //保存文件
                    file[i].transferTo(dest);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
         shopService.updateshop(shop);

///修改了店名，更改商品属性中的店名
        if (!sop.getShopName().equals(shop.getShopName())) {
             shop_statusService.upshopname(shop.getShopName(), sop.getShopName());
             commodityService.upcommudityshopname(shop.getShopName(), sop.getShopName());
        }


        model.addAttribute("vip", shop);
        return "redirect:/shopcenter";
    }


}
