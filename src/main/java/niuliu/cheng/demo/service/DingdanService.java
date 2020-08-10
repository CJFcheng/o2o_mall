package niuliu.cheng.demo.service;

import niuliu.cheng.demo.entity.Dingdan;

import java.util.List;

public interface DingdanService {
    Integer insertDD(Dingdan dingdan);
    List<Dingdan> selectDD(String userid,String st);
    List<Dingdan> selectallDD(String userid);
    List<Dingdan> seluDD(String userid,String st);
    List<Dingdan> seluallDD(String userid);
    Integer TokDD(String id);
}
