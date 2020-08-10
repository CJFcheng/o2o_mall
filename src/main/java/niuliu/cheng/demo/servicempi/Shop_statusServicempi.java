package niuliu.cheng.demo.servicempi;

import niuliu.cheng.demo.entity.Shop_status;
import niuliu.cheng.demo.mapper.Shop_statusMapper;
import niuliu.cheng.demo.service.Shop_statusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Shop_statusServicempi implements Shop_statusService {

    @Autowired//调用service层
    private Shop_statusMapper shop_statusMapper;

    @Override//表示重写
    public Integer insertshop_status(Shop_status shop_status) {
        return shop_statusMapper.insertshop_status(shop_status);
    }

    @Override
    public Integer upshop_lei(String shopName, String shoplei) {
        return shop_statusMapper.upshop_lei(shopName, shoplei);
    }

    @Override
    public String getshoplei(String shopName) {
        return shop_statusMapper.getshoplei(shopName);

    }

    @Override
    public Shop_status getall(String shopName) {
        return shop_statusMapper.getall(shopName);
    }

    @Override
    public Integer upcommoditilei(String old, String shopName) {
        return shop_statusMapper.upcommoditilei(old, shopName);
    }

    @Override
    public Integer upgonggao(String gonggao, String shopname) {
        return shop_statusMapper.upgonggao(gonggao, shopname);
    }//更新公告

    @Override
    public Integer upactivity(String activity, String shopname) {
        return shop_statusMapper.upactivity(activity, shopname);
    }//更新活动

    @Override
    public Integer upsp_status(String sp_status, String shopname) {
        return shop_statusMapper.upsp_status(sp_status, shopname);
    }//更新当前店铺状态

    @Override
    public Integer uptime(String opentime, String downtime, String shopname) {
        return shop_statusMapper.uptime(opentime, downtime, shopname);
    }//更新当前店铺状态

    @Override
    public Integer upcommoditynewlei(String old, String newlei, String shopname) {
        return shop_statusMapper.upcommoditynewlei(old, newlei, shopname);
    }

    //更新某一类别的所有商品的类别
    @Override
    public Integer upshopname(String newname, String oldname) {
        return shop_statusMapper.upshopname(newname, oldname);
    }
}
