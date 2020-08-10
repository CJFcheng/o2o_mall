package niuliu.cheng.demo.service;

import niuliu.cheng.demo.entity.AddressBean;

public interface AddressService {
    Integer inaddress(AddressBean addressBean);
    AddressBean seaddress(String userid);
    Integer upaddress(AddressBean addressBean);
}
