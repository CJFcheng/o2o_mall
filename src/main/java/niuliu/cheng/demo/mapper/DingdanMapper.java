package niuliu.cheng.demo.mapper;

import niuliu.cheng.demo.entity.Dingdan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DingdanMapper {
    @Insert("INSERT INTO `toaround`.`sporder`(`userid`, `shopid`, `shopName`,`shopphone`,`shopaddress`,`spid`,`spname`, `sppicture`,`danjia`, `amount`, `total`, `name`, `phone`, `address`, `remark`, `status`) VALUES (#{userid}, #{shopid},#{shopName},#{shopphone},#{shopaddress}, #{spid},#{spname},#{sppicture},#{danjia}, #{amount}, #{total}, #{name}, #{phone}, #{address}, #{remark}, 'n')")
    Integer insertDD(Dingdan dingdan);
    @Select("SELECT * From `sporder` where `shopid` = #{userid} and `status`=#{st} ")
    List<Dingdan> selectDD(String userid,String st);
    @Select("SELECT * From `sporder` where `shopid` = #{userid}  ")
    List<Dingdan> selectallDD(String userid);
    @Select("SELECT * From `sporder` where `userid` = #{userid} and `status`=#{st} ")
    List<Dingdan> seluDD(String userid,String st);
    @Select("SELECT * From `sporder` where `userid` = #{userid}  ")
    List<Dingdan> seluallDD(String userid);
    @Update("UPDATE `sporder` SET `status` = 'y' WHERE `sporder`.`id` = #{id}")
    Integer TokDD(String id);
}
