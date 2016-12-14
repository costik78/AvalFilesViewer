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

        // ��������� ����-����� � ����������� �������
        sortedX8.comparatorProperty().bind(tableX8.comparatorProperty());

        // ��������� ������� �������
        tableX8.setItems(sortedX8);
    }

    // �������������� ������ ��� �������
    // �� ������ �������� �� � ���� ������
    private void initData() {

        // �������� ������������
        Properties config = PropertiesValues.getInstance();

        Path path = null;
        datax8 = FXCollections.observableArrayList();
        filteredX8 = new FilteredList<>(datax8);

        path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x8.file"));
        new FileTask<>(path, datax8, X8Converter::getData).bindAndRun(tableX8);
        labelX8.setText(path.toString());
    }

    public void open(ActionEvent event) {

        Path filepath = selectFile("Choose #X8", "#X8 files", "#X8*.*");

        if(filepath != null) {
            new FileTask<>(filepath, datax8, X8Converter::getData).bindAndRun(tableX8);
            labelX8.setText(filepath.toString());
//            tabPane.getSelectionModel().select(tabX8);
        }
    }

    public void setPredicate(Predicate<FileX8> pr) {
        filteredX8.setPredicate(pr);
    }
}
