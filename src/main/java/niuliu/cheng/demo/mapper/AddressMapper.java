package niuliu.cheng.demo.mapper;

import niuliu.cheng.demo.entity.AddressBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AddressMapper {
    @Insert("INSERT INTO `toaround`.`useraddress` (`userid`, `name`, `phone`, `address`, `mo`) VALUES ( '${userid}', '${name}', '${phone}', '${address}', 'y')")
    Integer inaddress(AddressBean addressBean);
    @Select("SELECT * FROM `useraddress` where `userid` like #{userid}")
    AddressBean seaddress(String userid);
    @Update("UPDATE `useraddress` SET `phone` = '${phone}', `name` = '${name}', `address` = '${address}' WHERE `userid` = '${userid}'")
    Integer upaddress(AddressBean addressBean);
}
