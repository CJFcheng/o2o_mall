package niuliu.cheng.demo.service;

import niuliu.cheng.demo.entity.UserBean;

public interface UserService {
    Integer insertUser(UserBean userBean);

    UserBean selectUser(String phone);

    Integer updateUser(String password, String phone);

    Integer insertR(String name,String phone);
}
