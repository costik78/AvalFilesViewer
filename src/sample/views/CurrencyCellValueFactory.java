package sample.views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import sample.pojo.FileXX;
import sample.util.MyCurrency;

/**
 * Created by conti on 28.11.2016.
 */
public class CurrencyCellValueFactory<S, T> implements Callback<TableColumn.CellDataFeatures<FileXX, String>, ObservableValue<String>> {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<FileXX, String> param) {
        return new ReadOnlyObjectWrapper<>(MyCurrency.getSymbol(param.getValue().getCurrency()));
    }
}
