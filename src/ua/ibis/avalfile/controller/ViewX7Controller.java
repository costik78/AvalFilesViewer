package ua.ibis.avalfile.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import ua.ibis.avalfile.pojo.FileX7;
import ua.ibis.avalfile.pojo.FileXX;
import ua.ibis.avalfile.pojo.X7Converter;
import ua.ibis.avalfile.util.PropertiesValues;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Predicate;

import static ua.ibis.avalfile.controller.MyDialog.selectFile;

/**
 * Created by conti on 14.12.2016.
 */
public class ViewX7Controller {
    private ObservableList<FileX7> datax7;

    private FilteredList<FileX7> filteredX7;

    @FXML
    private TableView<FileX7> tableX7;

    @FXML
    private Label labelX7;

    @FXML
    public void initialize() {
        initData();

        SortedList<FileX7> sortedX7 = new SortedList<>(filteredX7);

        // связываем сорт-листы с сортировкой таблицы
        sortedX7.comparatorProperty().bind(tableX7.comparatorProperty());

        // заполняем таблицу данными
        tableX7.setItems(sortedX7);
    }

    private void loadFile(Path path) {
        if (path != null) {
            new FileTask<>(path, datax7, X7Converter::getData).bindAndRun(tableX7);
            labelX7.setText(path.toString());
        }

    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {

        datax7 = FXCollections.observableArrayList();
        filteredX7 = datax7.filtered(null);

        // загрузка конфигурации
        Properties config = PropertiesValues.getInstance();

        Path path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x7.file"));
        loadFile(path);
    }

    public void open(ActionEvent event) {
        Path path = selectFile("Choose #X7", "#X7 files", "#X7*.*");
        loadFile(path);
    }

    public void setPredicate(Predicate<FileXX> pr) {
        filteredX7.setPredicate(pr);
    }
}
