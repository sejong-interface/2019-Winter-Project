package kr.or.teamserver.coinserver.controller;


import kr.or.teamserver.coinserver.controller.command.ReadHelpFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/help")
public class HelpController {
    @GetMapping("/special")
    public List searchSpecial(ReadHelpFile readHelpFile) {
        String filename = ".\\src\\main\\resources\\HelpFile\\SpecialHelp.txt";
        return readHelpFile.getStringBuffer(filename);
    }

    @GetMapping("/generate")
    public List searchGenerate(ReadHelpFile readHelpFile) {
        String filename = ".\\src\\main\\resources\\HelpFile\\GenerateHelp.txt";
        return readHelpFile.getStringBuffer(filename);
    }
}
