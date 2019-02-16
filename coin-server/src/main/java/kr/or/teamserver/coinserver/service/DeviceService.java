package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.model.Device;
import java.util.Optional;

public interface DeviceService {
    void create(String token);

    Optional<Device> read(Long id);

    void update(Long id, String token);

    void delete(Long id);
}
