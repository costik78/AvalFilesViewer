package sample.controller;

import sample.pojo.FileX6;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by conti on 18.11.2016.
 */
public class X6Converter {
    public static FileX6 convert(String line) {

        FileX6 fileInfo = new FileX6();
        int i = 1;
        for (String field : line.split(";") ) {

            field = field.trim();
            try {
                switch (i) {
                    case 2:
                        fileInfo.setMfo(Integer.parseInt(field));
                        break;
                    case 3:
                        fileInfo.setAccount(field);
                        break;
                    case 4:
                        fileInfo.setTrNumber(field);
                        break;
                    case 5:
                        fileInfo.setCurrency(Integer.parseInt(field));
                        break;
                    case 6:
                        fileInfo.setObesp(Integer.parseInt(field));
                        break;
                    case 7:
                        fileInfo.setSummaObesp(Long.parseLong(field));
                        break;
                }
            } catch (NumberFormatException e) {

            }

            i++;
        }

        return fileInfo;
    }

    public static List<FileX6> getData(Path filepath) {
        try(Stream<String> linesX6 = Files.lines(filepath, Charset.forName("windows-1251"))) {
            return linesX6.
                    skip(1).
                    map(X6Converter::convert).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
