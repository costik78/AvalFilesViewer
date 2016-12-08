package sample.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import sample.controller.X8Converter;
import sample.pojo.FileX8;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by conti on 08.12.2016.
 */
public class X8Task extends Task<ObservableList<FileX8>> {
    private Path filepath;

    public X8Task(Path filepath) {
        this.filepath = filepath;
    }

    @Override
    protected ObservableList<FileX8> call() throws Exception {
        List<FileX8> x8 = X8Converter.getData(filepath);
        ObservableList<FileX8> dataX8 = FXCollections.observableList(x8);
        FilteredList<FileX8> filteredX8 = dataX8.filtered(null);
        return filteredX8.sorted();
    }
}
