package sample.task;

import javafx.application.Platform;
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
public class X5Task extends Task<List<FileX5>> {
    private Path filePath;
    private ObservableList<FileX5> list;

    public X5Task(Path filePath, ObservableList<FileX5> list) {
        this.filePath = filePath;
        this.list = list;
    }

    @Override
    protected List<FileX5> call() throws Exception {
        return X5Converter.getData(filePath);
    }

    @Override
    protected void succeeded() {
        super.succeeded();

        Platform.runLater(() -> list.setAll(getValue()));
    }
}
