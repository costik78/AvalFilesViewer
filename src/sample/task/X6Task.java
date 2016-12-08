package sample.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import sample.controller.X6Converter;
import sample.pojo.FileX6;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by conti on 08.12.2016.
 */
public class X6Task extends Task<ObservableList<FileX6>> {
    private Path filePath;

    public X6Task(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    protected ObservableList<FileX6> call() throws Exception {
        List<FileX6> x6 = X6Converter.getData(filePath);
        ObservableList<FileX6> dataX6 = FXCollections.observableList(x6);
        FilteredList<FileX6> filteredX6 = dataX6.filtered(null);
        return filteredX6.sorted();
    }
}
