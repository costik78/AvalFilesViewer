package ua.ibis.avalfile.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import ua.ibis.avalfile.pojo.FileX5;
import ua.ibis.avalfile.pojo.FileXX;
import ua.ibis.avalfile.pojo.X5Converter;
import ua.ibis.avalfile.util.PropertiesValues;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Predicate;

import static ua.ibis.avalfile.controller.MyDialog.selectFile;

/**
 * Created by conti on 14.12.2016.
 */
public class ViewX5Controller {

    private ObservableList<FileX5> datax5;

    private FilteredList<FileX5> filteredX5;

    @FXML
    private TableView<FileX5> tableX5;

    @FXML
    private Label labelX5;

    @FXML
    public void initialize() {
        initData();

        SortedList<FileX5> sortedX5 = new SortedList<>(filteredX5);

        // связываем сорт-листы с сортировкой таблицы
        sortedX5.comparatorProperty().bind(tableX5.comparatorProperty());

        // заполняем таблицу данными
        tableX5.setItems(sortedX5);
    }

    private void loadFile(Path path) {
        if (path != null) {
            new FileTask<>(path, datax5, X5Converter::getData).bindAndRun(tableX5);
            labelX5.setText(path.toString());
        }

    }

    private void initData() {

        datax5 = FXCollections.observableArrayList();
        filteredX5 = datax5.filtered(null);

        // загрузка конфигурации
        Properties config = PropertiesValues.getInstance();

        Path path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x5.file"));
        loadFile(path);
    }

    public void open(ActionEvent event){
        Path path = selectFile("Choose #X5", "#X5 files", "#X5*.*");
        loadFile(path);
    }

    public void setPredicate(Predicate<FileXX> pr) {
        filteredX5.setPredicate(pr);
    }

}
