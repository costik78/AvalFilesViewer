package ua.ibis.avalfile.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import ua.ibis.avalfile.pojo.FileXD;
import ua.ibis.avalfile.pojo.FileXX;
import ua.ibis.avalfile.pojo.XDConverter;
import ua.ibis.avalfile.util.PropertiesValues;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Predicate;

import static ua.ibis.avalfile.controller.MyDialog.selectFile;

/**
 * Created by conti on 14.12.2016.
 */
public class ViewXDController {

    private ObservableList<FileXD> dataxd;

    private FilteredList<FileXD> filteredXD;

    @FXML
    private TableView<FileXD> tableXD;

    @FXML
    private Label labelXD;

    @FXML
    public void initialize() {
        initData();

        SortedList<FileXD> sortedXD = new SortedList<>(filteredXD);

        // связываем сорт-листы с сортировкой таблицы
        sortedXD.comparatorProperty().bind(tableXD.comparatorProperty());

        // заполняем таблицу данными
        tableXD.setItems(sortedXD);
    }

    private void loadFile(Path path) {
        if (path != null) {
            new FileTask<>(path, dataxd, XDConverter::getData).bindAndRun(tableXD);
            labelXD.setText(path.toString());
        }
    }

    private void initData() {

        dataxd = FXCollections.observableArrayList();
        filteredXD = dataxd.filtered(null);

        // загрузка конфигурации
        Properties config = PropertiesValues.getInstance();

        Path path = Paths.get(config.getProperty("dirfiles"), config.getProperty("xd.file"));
        loadFile(path);
    }

    public void open(ActionEvent event){
        Path path = selectFile("Choose #XD", "#XD files", "#XD*.*");
        loadFile(path);
    }

    public void setPredicate(Predicate<FileXX> pr) {
        filteredXD.setPredicate(pr);
    }

}
