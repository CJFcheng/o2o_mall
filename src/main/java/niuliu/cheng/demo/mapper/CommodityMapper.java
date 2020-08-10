package niuliu.cheng.demo.mapper;

import niuliu.cheng.demo.entity.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommodityMapper {
    //添加商品
    @Insert("INSERT INTO `commodity`( `id`,`shopName`, `spName`, `miaosu`, `picture_sp1`, `picture_sp2`, `picture_sp3`, `danjia`, `danwei`, `kucun`, `op`, `shoplei`, `biaoqian`) " +
            "VALUES (NULL,#{shopName}, #{spName}, #{miaosu},#{picture_sp1},#{picture_sp2},#{picture_sp3},#{danjia},#{danwei},#{kucun},#{op},#{shoplei},#{biaoqian})")
    Integer insertcommodity(Commodity commodity);

    ////根据id查找商品
    @Select("SELECT * FROM `commodity` WHERE `id` = #{id} AND `deletesp` LIKE '0' ")
    Commodity getcommodity(String id);

    @Update("UPDATE `commodity` SET " +
            " `spName` = #{spName}, `miaosu` = #{miaosu}, `picture_sp1` = #{picture_sp1}, `picture_sp2` = #{picture_sp2}, `picture_sp3` = #{picture_sp3}, `danjia` = #{danjia}, `danwei` = #{danwei}, `kucun` = #{kucun} , `op` = #{op}, `shoplei` = #{shoplei}, `biaoqian` = #{biaoqian}" +
            "WHERE `commodity`.`id` = #{id}")
    Integer updatecommodity(Commodity commodity);

    @Select("SELECT * FROM `commodity` WHERE `shopName` = #{shopName} AND `shoplei` = #{shoplei} AND `op` = #{op} AND `deletesp` LIKE '0'")
    List<Commodity> cmdtlist(String shopName, String shoplei, String op);

    @Update("UPDATE `commodity` SET `op` = #{op} WHERE `commodity`.`id` = #{id};")
    Integer idupop(String op, String id);

    @Update("UPDATE `commodity` SET `deletesp` = '1' WHERE `commodity`.`id` =#{id};")
    Integer deletesp(String id);

    @Update("UPDATE `commodity` SET `shopName` = #{newname} WHERE `commodity`.`shopName` = #{oldname}")
    Integer upcommudityshopname(String newname, String oldname);
    //模糊查询
    @Select("SELECT * FROM `commodity` WHERE `spName` like #{vlu}")
    List<Commodity> mohulist(String vlu);

    //标签查询
    @Select("SELECT * FROM `commodity` WHERE `biaoqian` = #{vlu}")
    List<Commodity> leilist(String vlu);

    @Select("SELECT * FROM `commodity` ")
    List<Commodity> alllist();

}
