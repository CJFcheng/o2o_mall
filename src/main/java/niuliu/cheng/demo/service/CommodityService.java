package niuliu.cheng.demo.service;

import niuliu.cheng.demo.entity.Commodity;

import java.util.List;

public interface CommodityService {

    Integer insertcommodity(Commodity commodity);

    Commodity getcommodity(String id);

    Integer updatecommodity(Commodity commodity);

    List<Commodity> cmdtlist(String shopName, String shoplei, String op);

    Integer idupop(String op, String id);

    Integer deletesp(String id);

    Integer upcommudityshopname(String newname, String oldname);
    List<Commodity> mohulist(String vlu);
    List<Commodity> leilist(String vlu);
    List<Commodity> alllist();
}
