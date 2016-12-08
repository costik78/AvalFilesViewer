package sample.task;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.controller.X8Converter;
import sample.pojo.FileX8;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by conti on 08.12.2016.
 */
public class X8Task extends Task<List<FileX8>> {
    private Path filepath;
    private ObservableList<FileX8> list;

    public X8Task(Path filepath, ObservableList<FileX8> list) {
        this.filepath = filepath;
        this.list = list;
    }

    @Override
    protected List<FileX8> call() throws Exception {
        return X8Converter.getData(filepath);
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        Platform.runLater(() -> list.setAll(getValue()));
    }
}
