package ua.ibis.avalfile.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

/**
 * Created by conti on 08.12.2016.
 */
public class FileTask<T> extends Task<List<T>> {
    private Path filePath;
    private ObservableList<T> list;
    private Function<Path, List<T>> puller;

    public FileTask(Path filePath, ObservableList<T> list, Function<Path, List<T>> puller) {
        this.filePath = filePath;
        this.list = list;
        this.puller = puller;
    }

    @Override
    protected List<T> call() throws Exception {
        return puller.apply(filePath);
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        Platform.runLater(() -> list.setAll(getValue()));
    }

    public void bindAndRun(TableView<?> table) {
//        table.itemsProperty().bind(this.valueProperty());
        table.disableProperty().bind(this.runningProperty());

        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
    }

}
