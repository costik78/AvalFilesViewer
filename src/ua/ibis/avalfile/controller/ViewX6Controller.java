package ua.ibis.avalfile.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import ua.ibis.avalfile.pojo.FileX6;
import ua.ibis.avalfile.pojo.FileXX;
import ua.ibis.avalfile.pojo.X6Converter;
import ua.ibis.avalfile.util.PropertiesValues;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Predicate;

import static ua.ibis.avalfile.controller.MyDialog.selectFile;

/**
 * Created by conti on 14.12.2016.
 */
public class ViewX6Controller {

    private ObservableList<FileX6> datax6;

    private FilteredList<FileX6> filteredX6;

    @FXML
    private TableView<FileX6> tableX6;

    @FXML
    private Label labelX6;

    @FXML
    public void initialize() {
        initData();

        SortedList<FileX6> sortedX6 = new SortedList<>(filteredX6);

        // ��������� ����-����� � ����������� �������
        sortedX6.comparatorProperty().bind(tableX6.comparatorProperty());

        // ��������� ������� �������
        tableX6.setItems(sortedX6);
    }

    // �������������� ������ ��� �������
    // �� ������ �������� �� � ���� ������
    private void initData() {

        // �������� ������������
        Properties config = PropertiesValues.getInstance();

        Path path = null;
        datax6 = FXCollections.observableArrayList();
        filteredX6 = new FilteredList<>(datax6);

        path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x6.file"));
        new FileTask<>(path, datax6, X6Converter::getData).bindAndRun(tableX6);
        labelX6.setText(path.toString());
    }

    public void open(ActionEvent event) {

        Path filepath = selectFile("Choose #X6", "#X6 files", "#X6*.*");

        if(filepath != null) {
            new FileTask<>(filepath, datax6, X6Converter::getData).bindAndRun(tableX6);
            labelX6.setText(filepath.toString());
//            tabPane.getSelectionModel().select(tabX6);
        }
    }

    public void setPredicate(Predicate<FileXX> pr) {
        filteredX6.setPredicate(pr);
    }
}
