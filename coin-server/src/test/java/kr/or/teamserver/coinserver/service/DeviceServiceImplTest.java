package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.model.Device;
import kr.or.teamserver.coinserver.persistence.DeviceDAO;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class DeviceServiceImplTest {

    private static DeviceService deviceService;
    private static DeviceDAO deviceDAO;

    @BeforeAll
    static void beforeAll(){
        deviceDAO = Mockito.mock(DeviceDAO.class);
        deviceService = new DeviceServiceImpl(deviceDAO);
    }

    @DisplayName("디바이스 생성")
    @Test
    void create_device() {
        deviceService.create("64efw1f5qw6f", "목");
        Mockito.verify(deviceDAO, Mockito.times(1)).save(Mockito.any(Device.class));
    }
}

