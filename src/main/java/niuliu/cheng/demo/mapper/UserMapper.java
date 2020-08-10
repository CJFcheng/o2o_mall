package niuliu.cheng.demo.mapper;

import niuliu.cheng.demo.entity.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into users (username,password,phone) values(#{username},#{password},#{phone})")
    Integer insertUser(UserBean userBean);


    @Insert("INSERT INTO `record` (`id`, `name`, `phone`, `Ttime`) VALUES (NULL, #{name}, #{phone}, CURRENT_TIMESTAMP)")
    Integer insertR(String name,String phone);


    @Select("select * from users where phone = #{phone}")
    UserBean selectUser(String phone);

    @Update("UPDATE `users` SET `password` = #{password} WHERE `phone` = #{phone};")
    Integer updateUser(String password, String phone);
}
