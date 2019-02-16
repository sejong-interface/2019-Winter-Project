package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.model.Device;

import java.util.List;

public interface DeviceService {
    public void create(String token, String date);

    public Device read(Long id);

    public void update(Long id, String date);

    public void delete(Long id);
}
