package niuliu.cheng.demo.service;

import niuliu.cheng.demo.entity.Shop;

public interface ShopService {
    Integer insertshop(Shop shop);

    Shop getUserName(String user_id);
    Shop getname(String shopName);
    Integer updateshop(Shop shop);
}
