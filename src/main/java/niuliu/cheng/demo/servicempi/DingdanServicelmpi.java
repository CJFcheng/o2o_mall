package niuliu.cheng.demo.servicempi;

import niuliu.cheng.demo.entity.Dingdan;
import niuliu.cheng.demo.mapper.DingdanMapper;
import niuliu.cheng.demo.service.DingdanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DingdanServicelmpi implements DingdanService {

    @Autowired
    private DingdanMapper dingdanmapper;

    @Override
    public Integer insertDD(Dingdan dingdan) {
        return dingdanmapper.insertDD(dingdan);
    }

    @Override
    public List<Dingdan> selectDD(String userid,String st){
        return dingdanmapper.selectDD(userid,st);
    }
    @Override
    public List<Dingdan> selectallDD(String userid){
        return dingdanmapper.selectallDD(userid);
    }
    @Override
    public List<Dingdan> seluDD(String userid,String st){
        return dingdanmapper.seluDD(userid,st);
    }
    @Override
    public List<Dingdan> seluallDD(String userid){
        return dingdanmapper.seluallDD(userid);
    }
    @Override
    public Integer TokDD(String id){
        return  dingdanmapper.TokDD(id);
    }
}
