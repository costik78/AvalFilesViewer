package ua.ibis.avalfile.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import ua.ibis.avalfile.pojo.FileX8;
import ua.ibis.avalfile.pojo.X8Converter;
import ua.ibis.avalfile.util.PropertiesValues;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Predicate;

import static ua.ibis.avalfile.controller.MyDialog.selectFile;

/**
 * Created by conti on 14.12.2016.
 */
public class ViewX8Controller {

    private ObservableList<FileX8> datax8;

    private FilteredList<FileX8> filteredX8;

    @FXML
    private TableView<FileX8> tableX8;

    @FXML
    private Label labelX8;

    @FXML
    public void initialize() {
        initData();

        SortedList<FileX8> sortedX8 = new SortedList<>(filteredX8);

        // связываем сорт-листы с сортировкой таблицы
        sortedX8.comparatorProperty().bind(tableX8.comparatorProperty());

        // заполняем таблицу данными
        tableX8.setItems(sortedX8);
    }

    private void loadFile(Path path) {
        if (path != null) {
            new FileTask<>(path, datax8, X8Converter::getData).bindAndRun(tableX8);
            labelX8.setText(path.toString());
        }

    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {

        datax8 = FXCollections.observableArrayList();
        filteredX8 = datax8.filtered(null);

        // загрузка конфигурации
        Properties config = PropertiesValues.getInstance();

        Path path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x8.file"));
        loadFile(path);
    }

    public void open(ActionEvent event) {
        Path path = selectFile("Choose #X8", "#X8 files", "#X8*.*");
        loadFile(path);
    }

    public void setPredicate(Predicate<FileX8> pr) {
        filteredX8.setPredicate(pr);
    }
}
