package kr.or.teamserver.coinserver.controller;

import kr.or.teamserver.coinserver.controller.dto.ResultDto;
import kr.or.teamserver.coinserver.controller.dto.WasherDto;
import kr.or.teamserver.coinserver.model.Washer;
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

    @Autowired
    public AndroidController(WasherService washerService) {
        this.washerService = washerService;
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
