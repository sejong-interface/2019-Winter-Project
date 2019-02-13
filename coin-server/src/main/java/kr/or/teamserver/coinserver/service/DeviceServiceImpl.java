package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.controller.dto.DeviceDto;
import kr.or.teamserver.coinserver.persistence.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDAO deviceDAO;

    @Override
    public Integer createDevice(String token, String date){
        return deviceDAO.createDevice(token, date);
    }

    @Override
    public List<DeviceDto> listAll(){
        return deviceDAO.listAll();
    }

    public Integer updateDevice(String date, Integer id){
        return deviceDAO.updateDevice(date,id);
    }

    public Integer deleteDevice(Integer id){
        return deviceDAO.deleteDevice(id);
    }
}
