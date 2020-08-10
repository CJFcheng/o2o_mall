package niuliu.cheng.demo.servicempi;

import niuliu.cheng.demo.entity.AddressBean;
import niuliu.cheng.demo.mapper.AddressMapper;
import niuliu.cheng.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServicelmpi implements AddressService {
    @Autowired
   private AddressMapper addressMapper;
    @Override
    public Integer inaddress(AddressBean addressBean){
        return addressMapper.inaddress(addressBean);
    }
    @Override
    public  AddressBean seaddress(String userid){
        return addressMapper.seaddress(userid);
    }
    @Override
    public Integer upaddress(AddressBean addressBean){
        return addressMapper.upaddress(addressBean);
    }


}
