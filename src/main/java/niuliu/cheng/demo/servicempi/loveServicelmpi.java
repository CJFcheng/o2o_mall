package niuliu.cheng.demo.servicempi;

import niuliu.cheng.demo.entity.loveBean;
import niuliu.cheng.demo.mapper.loveMapper;
import niuliu.cheng.demo.service.loveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class loveServicelmpi implements loveService {
    @Autowired
    loveMapper loveMapper;
    @Override
     public Integer insert(loveBean loveBean){
        return loveMapper.insert(loveBean);
    }
    public  List<loveBean> seall(String userid){
        return loveMapper.seall(userid);
    }
    @Override
    public  Integer deletesp(String userid,String spid){
        return loveMapper.deletesp(userid,spid);

    }
    @Override
        public loveBean selove(String userid,String spid){
            return loveMapper.selove(userid,spid);
}
}
