package ua.ibis.avalfile.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by conti on 04.11.2016.
 * Class to present client's information from file #X8, which is made in VICONT
 */
public class FileX8 {

    private int mfo;
    private int regnumber;
    private LocalDate date;

    // formatter for date field
    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    public LocalDate getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = LocalDate.parse(Integer.toString(date), dateFormat);
    }

    public void setDate(LocalDate  date) {
        this.date = date;
    }

    public int getRegnumber() {

        return regnumber;
    }

    public void setRegnumber(int regnumber) {
        this.regnumber = regnumber;
    }

    public int getMfo() {

        return mfo;
    }

    public void setMfo(int mfo) {
        this.mfo = mfo;
    }

    @Override
    public String toString() {
        return String.format("FileX8{ mfo=%d, regnumber=%8d, date=%s }", mfo, regnumber, date.toString());
    }
}
