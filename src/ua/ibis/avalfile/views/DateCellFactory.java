package ua.ibis.avalfile.views;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by conti on 29.11.2016.
 */
public class DateCellFactory<S> implements Callback<TableColumn<S, Date>, TableCell<S,Date>> {

    private static final DateFormat myDateFormatter = new SimpleDateFormat("dd LLLL yyyy");

    @Override
    public TableCell<S, Date> call(TableColumn<S, Date> param) {

        return new TableCell<S, Date>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null) {
                    setText(null);
                } else {
                    setText(myDateFormatter.format(item));
                }
            }
        };
    }
}