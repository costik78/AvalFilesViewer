package ua.ibis.avalfile.views;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by conti on 29.11.2016.
 */
public class DateCellFactory<S> implements Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> {

    private static final DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    @Override
    public TableCell<S, LocalDate> call(TableColumn<S, LocalDate> param) {

        return new TableCell<S, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
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