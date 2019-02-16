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
        deviceService.create("64efw1f5qw6f");
        verify(deviceDAO, times(2)).save(any(Device.class));
    }

    @DisplayName("디바이스 읽기")
    @Test
    void read_device() {
        try {
            deviceService.read(1L);
        }catch (Exception e){
            e.printStackTrace();
        }
        verify(deviceDAO).findById(any(Long.class));
    }

    @DisplayName("디바이스 수정")
    @Test
    void update_device(){
        try {
            deviceService.update(1L, "sdfa6we5fwfaeq");
        } catch (NullPointerException e){
            verify(deviceDAO).save(any(Device.class));
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

