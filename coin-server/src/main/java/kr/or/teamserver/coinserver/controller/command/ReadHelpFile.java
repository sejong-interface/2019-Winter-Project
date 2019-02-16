package kr.or.teamserver.coinserver.controller.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadHelpFile {
    private List Help = new ArrayList();
    private final Logger logger = LoggerFactory.getLogger(ReadHelpFile.class);

    private void getStringBuffer(String filename) {
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                logger.info("line={}", line);
                Help.add(line);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List GenerateHelp() {
        String filename = ".\\src\\main\\java\\kr\\or\\teamserver\\coinserver\\controller\\HelpFile\\GenerateHelp.txt";
        getStringBuffer(filename);

        return Help;
    }

    public List SpecialHelp() {
        String filename = ".\\src\\main\\java\\kr\\or\\teamserver\\coinserver\\controller\\HelpFile\\SpecialHelp.txt";
        getStringBuffer(filename);

        return Help;
    }
}
