package sample.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import sample.controller.X7Converter;
import sample.pojo.FileX7;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by conti on 08.12.2016.
 */
public class X7Task extends Task<ObservableList<FileX7>> {
    private Path filePath;

    public X7Task(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    protected ObservableList<FileX7> call() throws Exception {
        List<FileX7> x7 = X7Converter.getData(filePath);
        ObservableList<FileX7> dataX7 = FXCollections.observableList(x7);
        FilteredList<FileX7> filteredX7 = dataX7.filtered(null);
        return filteredX7.sorted();
    }
}
