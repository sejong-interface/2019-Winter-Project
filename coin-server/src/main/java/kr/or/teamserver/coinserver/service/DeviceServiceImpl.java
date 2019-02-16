package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.model.Device;
import kr.or.teamserver.coinserver.persistence.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceDAO deviceDAO;

    @Autowired
    public DeviceServiceImpl(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @Override
    public void create(String token){
        deviceDAO.save(Device.of(token));
    }

    @Override
    public Optional<Device> read(Long id){
        return deviceDAO.findById(id);
    }

    @Override
    public void update(Long id, String token){
        deviceDAO.save(Device.of(id, token));
    }

    @Override
    public void delete(Long id){
        deviceDAO.deleteById(id);
    }
}
