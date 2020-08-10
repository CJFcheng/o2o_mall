package niuliu.cheng.demo.servicempi;

import niuliu.cheng.demo.entity.Shop;
import niuliu.cheng.demo.mapper.ShopMapper;
import niuliu.cheng.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiempi implements ShopService {
    @Autowired//调用service层
    private ShopMapper shopMapper;

    @Override//表示重写
    public Integer insertshop(Shop shop) {
        return shopMapper.insertshop(shop);
    }

    @Override
    public Shop getUserName(String user_id) {

        return shopMapper.geUserName(user_id);//执行sql语句
    }

    @Override
    public Shop getname(String shopName){
        return shopMapper.getname(shopName);
    }

    @Override
    public Integer updateshop(Shop shop) {
        return shopMapper.updateshop(shop);
    }
}
