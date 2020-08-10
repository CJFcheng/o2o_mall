package niuliu.cheng.demo.mapper;

import niuliu.cheng.demo.entity.loveBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface loveMapper {

    @Insert("INSERT INTO `love` (`id`, `userid`, `spid`, `spname`, `jianjie`, `picture`, `jiage`) VALUES (NULL, #{userid}, #{spid}, #{spname}, #{jianjie},#{picture}, #{jiage})")
    Integer insert(loveBean loveBean);
    @Select("SELECT * FROM `love` WHERE `userid` = #{userid}")
    List<loveBean> seall(String userid);
    @Delete("DELETE FROM `love` WHERE `userid` = #{userid} and `spid` = #{spid}")
    Integer deletesp(String userid,String spid);
    @Select("SELECT * FROM `love` WHERE `userid` = #{userid} and `spid` = #{spid}")
    loveBean selove(String userid,String spid);
}
