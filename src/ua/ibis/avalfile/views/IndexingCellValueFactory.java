package ua.ibis.avalfile.views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Class for cellvaluefactory of indexing column in table
 * Created by conti on 28.11.2016.
 */
public class IndexingCellValueFactory<S> implements Callback<TableColumn.CellDataFeatures<S, Number>, ObservableValue<Number>> {
    @Override
    public ObservableValue<Number> call(TableColumn.CellDataFeatures<S, Number> param) {
        return new ReadOnlyObjectWrapper<>(param.getTableView().getItems().indexOf(param.getValue()) + 1);
    }
}
