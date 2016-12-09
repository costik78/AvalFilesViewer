package ua.ibis.avalfile.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by conti on 04.11.2016.
 * Class to present client's information from file #X8, which is made in VICONT
 */
public class FileX8 {

    private int mfo;
    private int regnumber;
    private Date date;

    // formatter for date field
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public Date getDate() {
        return date;
    }

    public void setDate(int date) {

        try {
            this.date = dateFormat.parse(Integer.toString(date));
        } catch (ParseException e) {
            // should add log message
        }
    }

    public void setDate(Date date) {
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
        return String.format("FileX8{ mfo=%d, regnumber=%8d, date=%td-%<tm-%<tY }", mfo, regnumber, date);
    }
}
