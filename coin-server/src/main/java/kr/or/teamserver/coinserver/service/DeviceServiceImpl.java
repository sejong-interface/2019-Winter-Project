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
    public void create(String token, String date){
        Device device = new Device(token, date);
        deviceDAO.save(device);
    }

    @Override
    public Device read(Long id){
        Optional<Device> optionalDevice = deviceDAO.findById(id);

        if(optionalDevice.isPresent()) {
            return new Device(optionalDevice.get().getId(), optionalDevice.get().getToken(), optionalDevice.get().getDate());
        }
        return new Device();
    }

    @Override
    public void update(Long id, String date){
        String token = read(id).getToken();
        Device device = new Device(id, token, date);
        deviceDAO.save(device);
    }

    @Override
    public void delete(Long id){
        deviceDAO.deleteById(id);
    }
}
