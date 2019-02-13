package kr.or.teamserver.coinserver.persistence;


import kr.or.teamserver.coinserver.controller.dto.DeviceDto;
import kr.or.teamserver.coinserver.controller.dto.WasherDto;

import java.util.List;

public interface DeviceDAO {
    public Integer createDevice(String token, String date);

    public List<DeviceDto> listAll();

    public Integer updateDevice(String date, Integer id);

    public Integer deleteDevice(Integer id);
}
