package sample.task;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.controller.X7Converter;
import sample.pojo.FileX7;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by conti on 08.12.2016.
 */
public class X7Task extends Task<List<FileX7>> {
    private Path filePath;
    private ObservableList<FileX7> list;

    public X7Task(Path filePath, ObservableList<FileX7> list) {
        this.filePath = filePath;
        this.list = list;
    }

    @Override
    protected List<FileX7> call() throws Exception {
        return X7Converter.getData(filePath);
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        Platform.runLater(() -> list.setAll(getValue()));
    }
}
