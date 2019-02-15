package kr.or.teamserver.coinserver.controller.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadHelpFile {
    private String[] GenHelp;
    private String[] EspHelp;

    private final Logger logger = LoggerFactory.getLogger(ReadHelpFile.class);

    public String[] GenerateHelp() {
        int i = 0;
        try {
            File file = new File("C:\\Users\\onsae\\Documents\\2019-Winter-Project\\coin-server\\src\\main\\java\\kr\\or\\teamserver\\coinserver\\controller\\command\\GenerateHelp.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.mark(10000);
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                i = i + 1;
            }

            bufferedReader.reset();
            GenHelp = new String[i];
            i = 0;

            while((line = bufferedReader.readLine()) != null) {
                logger.info("line={}", line);
                GenHelp[i] = line;
                i = i+1;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println(e);
        }

        return GenHelp;
    }

    public String[] SpecialHelp() {
        int i = 0;
        try {
            File file = new File("C:\\Users\\onsae\\Documents\\2019-Winter-Project\\coin-server\\src\\main\\java\\kr\\or\\teamserver\\coinserver\\controller\\command\\SpecialHelp.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.mark(10000);
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                i = i + 1;
            }

            bufferedReader.reset();
            EspHelp = new String[i];
            i = 0;

            while((line = bufferedReader.readLine()) != null) {
                logger.info("line={}", line);
                EspHelp[i] = line;
                i = i+1;
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println(e);
        }

        return EspHelp;
    }
}
