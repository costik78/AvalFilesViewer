package ua.ibis.avalfile.views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import ua.ibis.avalfile.pojo.FileXX;
import ua.ibis.avalfile.util.MyCurrency;

/**
 * Created by conti on 28.11.2016.
 */
public class CurrencyCellValueFactory<S, T> implements Callback<TableColumn.CellDataFeatures<FileXX, String>, ObservableValue<String>> {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<FileXX, String> param) {
        return new ReadOnlyObjectWrapper<>(MyCurrency.getSymbol(param.getValue().getCurrency()));
    }
}
