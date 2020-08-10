package niuliu.cheng.demo.mapper;

import niuliu.cheng.demo.entity.Shop_status;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Shop_statusMapper {

    @Update("UPDATE `shop_status` SET `shop_name` = #{newname} WHERE `shop_status`.`shop_name` = #{oldname}")
    Integer upshopname(String newname, String oldname);


    @Insert("INSERT INTO `shop_status` (`id`, `shop_name`, `opentime`, `downtime`, `sp_status`, `shop_post`, `activity`)" +
            "VALUES (NULL,#{shop_name}, #{opentime}, #{downtime},#{sp_status},#{shop_post},#{activity})")
    Integer insertshop_status(Shop_status shop_status);//插入商店状态数据

    @Select("SELECT * FROM `shop_status` WHERE `shop_name` LIKE #{shopName}")
    Shop_status getall(String shopName);//查询该商店的活动开店关店时间，当前状态和公告店内分类

    @Select("SELECT `shoplei` FROM `shop_status` WHERE `shop_name` LIKE #{shopName}")
    String getshoplei(String shopName);//查询该商户的分类

    @Update("UPDATE `shop_status` SET  `shoplei` = #{shoplei} WHERE `shop_status`.`shop_name` = #{shopName};")
    Integer upshop_lei(String shopName, String shoplei);//更新分类

    @Update("UPDATE `commodity` SET `shoplei` = '默认分类' WHERE `commodity`.`shopName` = #{shopName}AND `commodity`.`shoplei`= #{old};")
    Integer upcommoditilei(String old, String shopName);//删除分类后将原分类的商品移动到默认

    @Update("UPDATE `commodity` SET `shoplei` = #{newlei}  WHERE `commodity`.`shopName` = #{shopname} AND `commodity`.`shoplei`= #{old};")
    Integer upcommoditynewlei(String old, String newlei, String shopname);//更新某一类别的所有商品的类别

    @Update("UPDATE `shop_status` SET `shop_post` = #{gonggao} WHERE `shop_status`.`shop_name` = #{shopname}")
    Integer upgonggao(String gonggao, String shopname);//更新公告

    @Update("UPDATE `shop_status` SET `activity` = #{activity} WHERE `shop_status`.`shop_name` = #{shopname}")
    Integer upactivity(String activity, String shopname);//更新活动

    @Update("UPDATE `shop_status` SET `sp_status` = #{sp_status} WHERE `shop_status`.`shop_name` = #{shopname}")
    Integer upsp_status(String sp_status, String shopname);//更新当前店铺状态

    @Update("UPDATE `shop_status` SET `opentime` = #{opentime}, `downtime` = #{downtime} WHERE `shop_status`.`shop_name` = #{shopname};")
    Integer uptime(String opentime, String downtime, String shopname);//更新当前店铺状态
}
