package kr.or.teamserver.coinserver.persistence;


import kr.or.teamserver.coinserver.model.Device;
import org.springframework.data.repository.CrudRepository;


public interface DeviceDAO extends CrudRepository<Device, Long> {
}
