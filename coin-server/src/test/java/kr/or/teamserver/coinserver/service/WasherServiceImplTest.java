package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.model.Washer;
import kr.or.teamserver.coinserver.persistence.WasherDAO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class WasherServiceImplTest {

    private static WasherService washerService;

    @AfterEach
    void afterEach() {
    }

    @DisplayName("정상적인 최초 세탁기 생성")
    @Test
    void create_normal() {
        Washer washer1 = washerService.save(0);
        Assertions.assertEquals(1, washer1.getId());
        Assertions.assertEquals(0, washer1.getElectricPower());

        Washer washer2 = washerService.save(10);
        Assertions.assertEquals(2, washer2.getId());
        Assertions.assertEquals(10, washer2.getElectricPower());
    }


    @DisplayName("정상적인 세탁기 생성/업데이트")
    @ParameterizedTest(name = "아이디={0}, 전력={1}")
    @CsvSource({"1, 0", "2, 10", "3, 100", "3, 10"})
    void save_normal(long id, long electricPower) {
        Washer washer = washerService.save(id, electricPower);

        Assertions.assertEquals(id, washer.getId());
        Assertions.assertEquals(electricPower, washer.getElectricPower());
    }

    @DisplayName("정상적인 세탁기 목록 조회")
    @Test
    void find_all() {
        washerService.save(1, 0);
        Assertions.assertEquals(1, washerService.findAll().size());

        washerService.save(2, 10);
        Assertions.assertEquals(2, washerService.findAll().size());

        washerService.save(3, 100);
        Assertions.assertEquals(3, washerService.findAll().size());

        washerService.save(3, 100);
        Assertions.assertEquals(3, washerService.findAll().size());
    }
}
