package kr.or.teamserver.coinserver.controller.command;

import kr.or.teamserver.coinserver.exception.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadHelpFile {
    private final Logger logger = LoggerFactory.getLogger(ReadHelpFile.class);

    public List<String> getStringBuffer(String filename) {
        List<String> help = new ArrayList<String>();

        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                logger.info("line={}", line);
                help.add(line);
            }

            bufferedReader.close();
            return help;

        } catch (Exception ex) {
            logger.error("Cannot find file", ex);
            help.clear();
            throw new FileNotFoundException("File not found ", ex);

        }
    }
}