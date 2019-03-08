package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.exception.DeviceNotFound;
import kr.or.teamserver.coinserver.model.Device;
import kr.or.teamserver.coinserver.persistence.DeviceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceDAO deviceDAO;
    private final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    public DeviceServiceImpl(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @Override
    public void create(String token, String date){
        logger.info("new device create");
        deviceDAO.save(Device.of(token, date));
    }

    @Override
    public Optional<Device> read(Long id){

        if(!deviceDAO.findById(id).isPresent()) {
            logger.info("device not found");
            throw new DeviceNotFound("404" + id);
        }

        logger.info("device found");
        return deviceDAO.findById(id);
    }

    @Override
    public void update(Long id, String token, String date){
        logger.info("device update");
        deviceDAO.save(Device.of(id, token, date));
    }

    @Override
    public void delete(Long id){
        logger.info("device delete");
        deviceDAO.deleteById(id);
    }

/*
    @Override
    List<Optional<Device>> readAll(){
        return
    }
*/
}
