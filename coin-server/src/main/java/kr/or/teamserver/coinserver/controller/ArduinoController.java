package kr.or.teamserver.coinserver.controller;

import kr.or.teamserver.coinserver.controller.command.WasherCommand;
import kr.or.teamserver.coinserver.controller.dto.DeviceDto;
import kr.or.teamserver.coinserver.controller.dto.ResultDto;
import kr.or.teamserver.coinserver.controller.dto.WasherDto;
import kr.or.teamserver.coinserver.model.Washer;
import kr.or.teamserver.coinserver.persistence.DeviceDAO;
import kr.or.teamserver.coinserver.service.DeviceService;
import kr.or.teamserver.coinserver.service.WasherService;
import kr.or.teamserver.coinserver.service.WasherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arduino")
public class ArduinoController {

    private final Logger logger = LoggerFactory.getLogger(ArduinoController.class);
    private final WasherService washerService;

    @Autowired
    public ArduinoController(WasherService washerService) {
        this.washerService = washerService;
    }

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/test/listall")
    public List<DeviceDto> deviceListAll(){
        logger.info(deviceService.listAll().toString());
        deviceService.deleteDevice(1);
        logger.info(deviceService.listAll().toString());
        deviceService.updateDevice("목",2);
        logger.info(deviceService.listAll().toString());
        deviceService.createDevice("54d5d6sf45sd6f6", "일");
        logger.info(deviceService.listAll().toString());
        return deviceService.listAll();
    }

    @PostMapping({"", "/"})
    public ResultDto<WasherDto> save(@RequestBody WasherCommand washerCommand) {
        Washer washer = washerService.save(washerCommand.getElectricPower());
        return ResultDto.succeed(WasherDto.from(washer));
    }

    @PostMapping("/{id}")
    public ResultDto<WasherDto> save(@PathVariable long id, @RequestBody WasherCommand washerCommand) {
        Washer washer = washerService.save(id, washerCommand.getElectricPower());
        return ResultDto.succeed(WasherDto.from(washer));
    }
}