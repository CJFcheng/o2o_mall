package niuliu.cheng.demo.service;

import niuliu.cheng.demo.entity.Shop_status;

public interface Shop_statusService {

    Integer insertshop_status(Shop_status shop_status);//插入商店状态数据

    Shop_status getall(String shopName);//查询该商店的活动开店关店时间，当前状态和公告店内分类

    String getshoplei(String shopName);//查询该商户的分类

    Integer upshop_lei(String shopName, String shoplei);//更新分类

    Integer upcommoditilei(String old, String shopName);//删除分类后将原分类的商品移动到默认

    Integer upgonggao(String gonggao, String shopname);//更新公告

    Integer upactivity(String activity, String shopname);//更新活动

    Integer upsp_status(String sp_status, String shopname);//更新当前店铺状态

    Integer uptime(String opentime, String downtime, String shopname);//更新当前店铺状态

    Integer upcommoditynewlei(String old, String newlei, String shopname);//更新某一类别的所有商品的类别

    Integer upshopname(String newname, String oldname);
}
