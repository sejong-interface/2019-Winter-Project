package kr.or.teamserver.coinserver.controller;

import kr.or.teamserver.coinserver.controller.dto.ResultDto;
import kr.or.teamserver.coinserver.controller.dto.WasherDto;
import kr.or.teamserver.coinserver.exception.DeviceNotFound;
import kr.or.teamserver.coinserver.controller.command.ReadHelpFile;
import kr.or.teamserver.coinserver.model.Device;
import kr.or.teamserver.coinserver.model.Washer;
import kr.or.teamserver.coinserver.service.DeviceService;
import kr.or.teamserver.coinserver.service.WasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/android")
public class AndroidController {

    private final WasherService washerService;
    private final DeviceService deviceService;

    @Autowired
    public AndroidController(WasherService washerService, DeviceService deviceService) {
        this.washerService = washerService;
        this.deviceService = deviceService;
    }

    @GetMapping("/device/{id}")
    public Device device(@PathVariable Long id){
        return deviceService.read(id).get();
    }

    @GetMapping({"", "/"})
    public ResultDto<WasherDto> searchAll() {
        // TODO 데이터가 존재하지 않을때 status code 가 404 가 되도록 변경
        List<Washer> washers = washerService.findAll();
        return ResultDto.from(washers);
    }

    @GetMapping("/{id}")
    public ResultDto<WasherDto> searchOne(@PathVariable long id) {
        Washer washer = washerService.findOne(id);
        return ResultDto.from(List.of(washer));
    }
}
