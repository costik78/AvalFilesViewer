package sample.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import sample.controller.X5Converter;
import sample.pojo.FileX5;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by conti on 08.12.2016.
 */
public class X5Task extends Task<ObservableList<FileX5>> {
    private Path filePath;

    public X5Task(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    protected ObservableList<FileX5> call() throws Exception {
        List<FileX5> x5 = X5Converter.getData(filePath);
        ObservableList<FileX5> dataX5 = FXCollections.observableList(x5);
        FilteredList<FileX5> filtered = dataX5.filtered(null);
        return filtered.sorted();
    }
}
