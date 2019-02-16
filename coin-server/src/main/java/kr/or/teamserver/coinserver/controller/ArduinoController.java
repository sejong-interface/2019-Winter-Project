package kr.or.teamserver.coinserver.controller;

import kr.or.teamserver.coinserver.controller.command.WasherCommand;
import kr.or.teamserver.coinserver.controller.dto.ResultDto;
import kr.or.teamserver.coinserver.controller.dto.WasherDto;
import kr.or.teamserver.coinserver.model.Washer;
import kr.or.teamserver.coinserver.service.WasherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/arduino")
public class ArduinoController {

    private final Logger logger = LoggerFactory.getLogger(ArduinoController.class);
    private final WasherService washerService;

    @Autowired
    public ArduinoController(WasherService washerService) {
        this.washerService = washerService;
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