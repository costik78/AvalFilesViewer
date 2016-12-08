package sample.task;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.controller.X6Converter;
import sample.pojo.FileX6;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by conti on 08.12.2016.
 */
public class X6Task extends Task<List<FileX6>> {
    private Path filePath;
    private ObservableList<FileX6> list;

    public X6Task(Path filePath, ObservableList<FileX6> list) {
        this.filePath = filePath;
        this.list = list;
    }

    @Override
    protected List<FileX6> call() throws Exception {
        return X6Converter.getData(filePath);
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        Platform.runLater(() -> list.setAll(getValue()));
    }
}
