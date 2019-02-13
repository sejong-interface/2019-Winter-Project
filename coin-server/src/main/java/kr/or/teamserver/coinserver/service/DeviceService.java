package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.controller.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    public Integer createDevice(String token, String date);

    public List<DeviceDto> listAll();

    public Integer updateDevice(String date, Integer id);

    public Integer deleteDevice(Integer id);
}
