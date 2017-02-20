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
 * Created by conti on 20.02.2017.
 */
public class XDConverter {
    /**
     * Устанавленвает значение поля в FileX5 по порядковому номеру колонки в файле #X5
     * @param xd - объект, в котором нужно установить поле
     * @param numberOfField - порядковый номер колонки в #X5
     * @param field - строка со значением поля
     */
    public static void setField(FileXD xd, int numberOfField, String field) {

        try {
            switch (numberOfField) {
                case 2:
                    xd.setMfo(Integer.parseInt(field));
                    break;
                case 3:
                    xd.setAccount(field);
                    break;
                case 4:
                    xd.setTrNumber(field);
                    break;
                case 5:
                    xd.setCurrency(Integer.parseInt(field));
                    break;
                case 6:
                    field.replace(',', ';');
                    xd.setN170(field);
                    break;
                case 7:
                    field.replace(',', ';');
                    xd.setN172(field);
                    break;
                case 8:
                    field.replace(',', ';');
                    xd.setN173(field);
                    break;
                case 9:
                    field.replace(',', ';');
                    xd.setN179(field);
                    break;
            }
        }
        catch (NumberFormatException e) {

        }
    }

    public static FileXD convert(String line) {

        FileXD treat = new FileXD();

        String[] fields = line.split(";");
        for(int i = 0; i < fields.length; i++) {
            setField(treat, i + 1, fields[i].trim());
        }

        return treat;
    }

    public static List<FileXD> getData(Path filepath) {

        try (Stream<String> lines = Files.lines(filepath, Charset.forName("windows-1251"))) {
            return lines.
                    skip(1).
                    filter(e -> !e.isEmpty()).
                    map(XDConverter::convert).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
