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
public class X5Converter {
    /**
     * Устанавленвает значение поля в FileX5 по порядковому номеру колонки в файле #X5
     * @param x5 - объект, в котором нужно установить поле
     * @param numberOfField - порядковый номер колонки в #X5
     * @param field - строка со значением поля
     */
    public static void setField(FileX5 x5, int numberOfField, String field) {

        try {
            switch (numberOfField) {
                case 2:
                    x5.setMfo(Integer.parseInt(field));
                    break;
                case 3:
                    x5.setAccount(field);
                    break;
                case 4:
                    x5.setTrNumber(field);
                    break;
                case 5:
                    x5.setCurrency(Integer.parseInt(field));
                    break;
                case 6:
                    x5.setSummaObesp(Long.parseLong(field));
                    break;
                case 7:
                    x5.setClientClass(field.trim().charAt(0));
                    break;
                case 8:
                    x5.setSummaReserve(Long.parseLong(field));
                    break;
                case 9:
                    x5.setBs4Reserve(Integer.parseInt(field));
                    break;
                case 10:
                    x5.setObespech(field);
                    break;
                case 11:
                    x5.setCorrecterClientClass(field.trim().charAt(0));
                    break;
                // вынесено в файл #X6
//                case 12:
//                    break;
                case 13:
                    x5.setSummaObespP23(Long.parseLong(field));
                    break;
                case 14:
                    x5.setSummaForgiven(Long.parseLong(field));
                    break;
                case 15:
                    x5.setDPD(Integer.parseInt(field));
                    break;
                case 16:
                    x5.setGroupOfClient(Integer.parseInt(field));
                    break;
                case 17:
                    x5.setProbabilityOfDefault(field);
                    break;
                case 18:
                    x5.setPD(field);
                    break;
            }
        }
        catch (NumberFormatException e) {

        }
    }

    public static FileX5 convert(String lineX5) {

        FileX5 treat = new FileX5();

        String[] fields = lineX5.split(";");
        for(int i = 0; i < fields.length; i++) {
            setField(treat, i + 1, fields[i].trim());
        }

        return treat;
    }

    public static List<FileX5> getData(Path filepath) {

        try (Stream<String> lines = Files.lines(filepath, Charset.forName("windows-1251"))) {
            return lines.
                    skip(1).
                    filter(e -> !e.isEmpty()).
                    map(X5Converter::convert).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
