package niuliu.cheng.demo.servicempi;

import niuliu.cheng.demo.entity.UserBean;
import niuliu.cheng.demo.mapper.UserMapper;
import niuliu.cheng.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public Integer insertUser(UserBean userBean) {
        return userMapper.insertUser(userBean);
    }

    @Override
    public UserBean selectUser(String phone) {
        return userMapper.selectUser(phone);
    }

    @Override
    public Integer updateUser(String password, String phone) {
        return userMapper.updateUser(password, phone);
    }

    @Override
    public  Integer insertR(String name,String phone){
        return userMapper.insertR(name,phone);
    }



}
