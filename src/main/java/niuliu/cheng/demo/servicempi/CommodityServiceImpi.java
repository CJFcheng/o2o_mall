package niuliu.cheng.demo.servicempi;

import niuliu.cheng.demo.entity.Commodity;
import niuliu.cheng.demo.mapper.CommodityMapper;
import niuliu.cheng.demo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpi implements CommodityService {

    @Autowired//调用service层
    private CommodityMapper commodityMapper;

    @Override//表示重写
    public Integer insertcommodity(Commodity commodity) {
        return commodityMapper.insertcommodity(commodity);
    }

    @Override
    public Commodity getcommodity(String id) {

        return commodityMapper.getcommodity(id);
    }

    @Override
    public Integer updatecommodity(Commodity commodity) {

        return commodityMapper.updatecommodity(commodity);
    }

    @Override
    public List<Commodity> cmdtlist(String shopName, String shoplei, String op) {
        return commodityMapper.cmdtlist(shopName, shoplei, op);
    }

    @Override
    public Integer idupop(String op, String id) {
        return commodityMapper.idupop(op, id);
    }

    @Override
    public Integer deletesp(String id) {
        return commodityMapper.deletesp(id);
    }

    @Override
    public Integer upcommudityshopname(String newname, String oldname) {
        return commodityMapper.upcommudityshopname(newname, oldname);
    }
    @Override
    public List<Commodity> mohulist(String vlu){
        return commodityMapper.mohulist(vlu);
    }
    @Override
    public List<Commodity> leilist(String vlu){
        return commodityMapper.leilist(vlu);
    }
    @Override
    public List<Commodity> alllist(){
        return commodityMapper.alllist();
    }
}
