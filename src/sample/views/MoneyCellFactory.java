package sample.views;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by conti on 29.11.2016.
 */
public class MoneyCellFactory<S> implements Callback<TableColumn<S, Number>, TableCell<S, Number>> {

    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    @Override
    public TableCell<S, Number> call(TableColumn<S, Number> param) {
        return new TableCell<S, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null) {
                    setText(null);
                } else {
                    BigDecimal sum = new BigDecimal(item.longValue()).movePointLeft(2);
                    setText(df.format(sum));
                }
            }
        };
    }
}
