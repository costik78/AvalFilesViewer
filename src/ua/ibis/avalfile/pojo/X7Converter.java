package ua.ibis.avalfile.pojo;

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
public class X7Converter {
    public static FileX7 convert(String line) {

        FileX7 fileInfo = new FileX7();
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
                        fileInfo.setSum(Long.parseLong(field));
                        break;
                }
            } catch (NumberFormatException e) {

            }
            i++;
        }

        return fileInfo;
    }

    public static List<FileX7> getData(Path filepath) {

        try(Stream<String> linesX7 = Files.lines(filepath, Charset.forName("windows-1251"))) {
            return linesX7.
                    skip(1).
                    filter(x -> { String bs4 = x.substring(10, 14); return !"2625".equals(bs4) && !"2605".equals(bs4);} ).
                    map(X7Converter::convert).
                    collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
