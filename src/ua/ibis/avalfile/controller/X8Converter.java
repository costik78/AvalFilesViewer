package ua.ibis.avalfile.controller;

import ua.ibis.avalfile.pojo.FileX8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class X8Converter {

    private static String getClientNumber(String line) {
        return line.split("=")[0].substring(3);
    }

    private static FileX8 getValue(String line) {
        String[] split = line.split("=");
        return setField(new FileX8(), split[0].substring(0, 3), split[1]);
    }

    private static FileX8 setField(FileX8 client, String field, String value) {
        // значение показателя
        try {
            int valueInt = Integer.parseInt(value.trim());
            switch (field) {
                case "010":
                    client.setMfo(valueInt);
                    break;
                case "020":
                    client.setRegnumber(valueInt);
                    break;
                case "040":
                    client.setDate(valueInt);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {

        }

        return client;
    }

    private static FileX8 merge(FileX8 one, FileX8 two) {
        if (two.getRegnumber() != 0) {
            one.setRegnumber(two.getRegnumber());
        } else if (two.getDate() != null) {
            one.setDate(two.getDate());
        } else if (two.getMfo() != 0) {
            one.setMfo(two.getMfo());
        }
        return one;
    }

    public static List<FileX8> getData(Path filepath) {

        try (Stream<String> streamX8 = Files.lines(filepath, Charset.forName("windows-1251"))) {

            return streamX8.
                    skip(3).
                    filter(e -> e.startsWith("010") || e.startsWith("020") || e.startsWith("040")).
                    collect(Collectors.toMap(X8Converter::getClientNumber, X8Converter::getValue, X8Converter::merge)).
                    values().
                    stream().
//                    filter(e -> e.getMfo()/100000 == 3).
                    sorted(Comparator.comparing(FileX8::getMfo).thenComparing(
                            Comparator.comparing(FileX8::getRegnumber))).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
