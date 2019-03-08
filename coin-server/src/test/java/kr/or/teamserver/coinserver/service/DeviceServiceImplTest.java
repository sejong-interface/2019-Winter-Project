package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.model.Device;
import kr.or.teamserver.coinserver.persistence.DeviceDAO;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class DeviceServiceImplTest {

    private static DeviceService deviceService;
    private static DeviceDAO deviceDAO;

    @BeforeAll
    static void beforeAll(){
        deviceDAO = mock(DeviceDAO.class);
        deviceService = new DeviceServiceImpl(deviceDAO);
    }

    @DisplayName("디바이스 생성")
    @Test
    void create_device() {
        deviceService.create("64efw1f5qw6f", "화");
        verify(deviceDAO, times(2)).save(any(Device.class));
    }

    @DisplayName("디바이스 읽기 nullPointException")
    @Test
    void read_device_null() {
        doThrow(new ArithmeticException()).when(deviceDAO).findById(any(Long.class));
        deviceDAO.findById(null);
    }

    @DisplayName("디바이스 수정 NullPointException")
    @Test
    void update_device_null() {
        try {
            deviceService.update(1L, "sdfa6we5fwfaeq", "mon");
        } catch (NullPointerException e) {
            verify(deviceDAO).save(any(Device.class));
        }
    }

    @DisplayName("디바이스 수정 Exception")
    @Test
    void update_device_exception(){
        try {
            deviceService.update(1L, "sdfa6we5fwfaeq", "mon");
        } catch (Exception e){
            verify(deviceDAO).save(any(Device.class));
        }
    }

    @DisplayName("디바이스 삭제")
    @Test
    void delete_device() {
        deviceService.delete(1L);
        verify(deviceDAO).deleteById(any(Long.class));
    }
}

