package niuliu.cheng.demo.service;

import niuliu.cheng.demo.entity.loveBean;

import java.util.List;

public interface loveService {
    Integer insert(loveBean loveBean);
    List<loveBean> seall(String userid);
    Integer deletesp(String userid,String spid);
    loveBean selove(String userid,String spid);
}
