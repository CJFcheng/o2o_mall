package niuliu.cheng.demo.mapper;

import niuliu.cheng.demo.entity.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper

public interface ShopMapper {
    @Insert("INSERT INTO `shop` (`id`, `user_id`, `shopName`, `Introduction`, `userName`, `userPhone`, `pictureOne`, `pictureTwo`, `pictureThree`, `idcardOne`, `idcardTwo`, `mustPicture`, `addressID`, `addressName`)" +
            "VALUES (NULL,#{user_id}, #{shopName}, #{Introduction},#{userName},#{userPhone},#{pictureOne},#{pictureTwo},#{pictureThree},#{idcardOne},#{idcardTwo},#{mustPicture},#{addressID},#{addressName})")
    Integer insertshop(Shop shop);

    @Select("SELECT * FROM `shop` WHERE `user_id` LIKE #{user_id}")
    Shop geUserName(String user_id);
    @Select("SELECT * FROM `shop` WHERE `shopName` = #{shopName}")
    Shop getname(String shopName);



    @Update("UPDATE `shop` SET `shopName` =  #{shopName},`Introduction` = #{Introduction}, `userName` = #{userName}, `userPhone` = #{userPhone}, `pictureOne` = #{pictureOne}, `pictureTwo` = #{pictureTwo}, `pictureThree` = #{pictureThree}, `idcardOne` = #{idcardOne}, `idcardTwo` = #{idcardTwo}, `mustPicture` = #{mustPicture}, `addressID`= #{addressID}, `addressName` = #{addressName} WHERE `shop`.`user_id` = #{user_id};")
    Integer updateshop(Shop shop);
}
