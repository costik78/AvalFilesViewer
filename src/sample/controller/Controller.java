package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import sample.pojo.*;
import sample.util.MyCurrency;
import sample.util.PropertiesValues;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Predicate;

public class Controller {

    private ObservableList<FileX5> datax5;
    private ObservableList<FileX6> datax6;
    private ObservableList<FileX7> datax7;
    private ObservableList<FileX8> datax8;

    private FilteredList<FileX5> filteredX5;
    private FilteredList<FileX6> filteredX6;
    private FilteredList<FileX7> filteredX7;
    private FilteredList<FileX8> filteredX8;

    // пункті меню
    @FXML
    private MenuItem filterAcc;

    @FXML
    private MenuItem filterTreat;

    @FXML
    private MenuItem filterCurrency;

    @FXML
    private MenuItem filterRegnmbr;

    // панель табов
    @FXML
    private TabPane tabPane;

    // табы
    @FXML
    private Tab tabX5;

    @FXML
    private Tab tabX6;

    @FXML
    private Tab tabX7;

    @FXML
    private Tab tabX8;

    // таблицы
    @FXML
    private TableView<FileX5> tableX5;

    @FXML
    private TableView<FileX6> tableX6;

    @FXML
    private TableView<FileX7> tableX7;

    @FXML
    private TableView<FileX8> tableX8;

    // метки с адресами файлов
    @FXML
    private Label labelX5;

    @FXML
    private Label labelX6;

    @FXML
    private Label labelX7;

    @FXML
    private Label labelX8;

    // инициализируем форму данными
    @FXML
    public void initialize() {
        initData();

        SortedList<FileX5> sortedX5 = new SortedList<>(filteredX5);
        SortedList<FileX6> sortedX6 = new SortedList<>(filteredX6);
        SortedList<FileX7> sortedX7 = new SortedList<>(filteredX7);
        SortedList<FileX8> sortedX8 = new SortedList<>(filteredX8);

        // связываем сорт-листы с сортировкой таблицы
        sortedX5.comparatorProperty().bind(tableX5.comparatorProperty());
        sortedX6.comparatorProperty().bind(tableX6.comparatorProperty());
        sortedX7.comparatorProperty().bind(tableX7.comparatorProperty());
        sortedX8.comparatorProperty().bind(tableX8.comparatorProperty());

        // заполняем таблицу данными
        tableX5.setItems(sortedX5);
        tableX6.setItems(sortedX6);
        tableX7.setItems(sortedX7);
        tableX8.setItems(sortedX8);
    }

    private void createThread(Task task, TableView<?> table) {
//        table.itemsProperty().bind(task.valueProperty());
        table.disableProperty().bind(task.runningProperty());

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    // подготавливаем данные для таблицы
    // вы можете получать их с базы данных
    private void initData() {

        // загрузка конфигурации
        Properties config = PropertiesValues.get();

        Path path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x5.file"));
        datax5 = FXCollections.observableArrayList();
        datax6 = FXCollections.observableArrayList();
        datax7 = FXCollections.observableArrayList();
        datax8 = FXCollections.observableArrayList();
        filteredX5 = new FilteredList<>(datax5);
        filteredX6 = new FilteredList<>(datax6);
        filteredX7 = new FilteredList<>(datax7);
        filteredX8 = new FilteredList<>(datax8);

        createThread(new XXTask<>(path, datax5, X5Converter::getData), tableX5);
        labelX5.setText(path.toString());

        path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x6.file"));
        createThread(new XXTask<>(path, datax6, X6Converter::getData), tableX6);
        labelX6.setText(path.toString());

        path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x7.file"));
        createThread(new XXTask<>(path, datax7, X7Converter::getData), tableX7);
        labelX7.setText(path.toString());

        path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x8.file"));
        createThread(new XXTask<>(path, datax8, X8Converter::getData), tableX8);
        labelX8.setText(path.toString());

        filterAcc.disableProperty().bind(tabX8.selectedProperty());
        filterTreat.disableProperty().bind(tabX8.selectedProperty());
        filterCurrency.disableProperty().bind(tabX8.selectedProperty());
        filterRegnmbr.disableProperty().bind(tabX8.selectedProperty().not());
    }

    private Path selectFile(String dialogDescription, String fileDescription, String fileExtension) {

        FileChooser chooser = new FileChooser();

        Properties properties = PropertiesValues.get();
        chooser.setInitialDirectory(new File(properties.getProperty("dirfiles")));
        chooser.setTitle(dialogDescription);
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(fileDescription, fileExtension));

        File selectedfile = chooser.showOpenDialog(null);

        return (selectedfile != null ? selectedfile.toPath() : null);
    }

    @FXML
    private void openX5(ActionEvent event){

        Path filepath = selectFile("Choose #X5", "#X5 files", "#X5*.*");

        if(filepath != null) {
            createThread(new XXTask<>(filepath, datax5, X5Converter::getData), tableX5);
            labelX5.setText(filepath.toString());
            tabPane.getSelectionModel().select(tabX5);
        }
    }

    @FXML
    private void openX6(ActionEvent event) {

        Path filepath = selectFile("Choose #X6", "#X6 files", "#X6*.*");

        if(filepath != null) {
            createThread(new XXTask<>(filepath, datax6, X6Converter::getData), tableX6);
            labelX6.setText(filepath.toString());
            tabPane.getSelectionModel().select(tabX6);
        }
    }

    @FXML
    private void openX7(ActionEvent event) {

        Path filepath = selectFile("Choose #X7", "#X7 files", "#X7*.*");

        if(filepath != null) {
            createThread(new XXTask<>(filepath, datax7, X7Converter::getData), tableX7);
            labelX7.setText(filepath.toString());
            tabPane.getSelectionModel().select(tabX7);
        }
    }


    @FXML
    private void openX8(ActionEvent event) {

        Path filepath = selectFile("Choose #X8", "#X8 files", "#X8*.*");

        if(filepath != null) {
            createThread(new XXTask<>(filepath, datax8, X8Converter::getData), tableX8);
            labelX8.setText(filepath.toString());
            tabPane.getSelectionModel().select(tabX8);
        }
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    //
    @FXML
    private void filterByAccount(ActionEvent event) {

        String answer = MyDialog.askDialog("Number of account", "Please, enter number of account", "Account:");
        if(answer != null) {

            Predicate<FileXX> pr;
            if(answer.isEmpty()) {
                pr = null;
            } else {
                pr = (e -> e.getAccount().startsWith(answer) );
            }

            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab == tabX5) {
                filteredX5.setPredicate(pr);
            } else if (selectedTab == tabX6) {
                filteredX6.setPredicate(pr);
            } else if (selectedTab == tabX7) {
                filteredX7.setPredicate(pr);
            }
        }
    }

    @FXML
    private void filterByTreat(ActionEvent event) {

        String answer = MyDialog.askDialog("Number of treat", "Please, enter number of treat", "Treat:");
        if(answer != null) {

            Predicate<FileXX> pr;
            if( answer.isEmpty() ) {
                pr = null;
            } else {
                pr = (e -> e.getTrNumber().startsWith(answer));
            }

            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab == tabX5) {
                filteredX5.setPredicate(pr);
            } else if (selectedTab == tabX6) {
                filteredX6.setPredicate(pr);
            } else if (selectedTab == tabX7) {
                filteredX7.setPredicate(pr);
            }
        }
    }

    @FXML
    private void filterByCurrency(ActionEvent event) {

        String answer = MyDialog.askDialog("Code of currency ", "Please, enter code of currency", "Currency:");
        if(answer != null) {

            Predicate<FileXX> pr;
            if(answer.isEmpty()) {
                pr = null;
            } else {
                pr = (e -> e.getCurrency() == MyCurrency.getCode(answer));
            }

            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab == tabX5) {
                filteredX5.setPredicate(pr);
            } else if (selectedTab == tabX6) {
                filteredX6.setPredicate(pr);
            } else if (selectedTab == tabX7) {
                filteredX7.setPredicate(pr);
            }
        }
    }

    @FXML
    private void filterByRegnmbr(ActionEvent event) {
        String answer = MyDialog.askDialog("Registered number of client", "Please, enter regnumber of client", "Reg.number:");
        if(answer != null) {

            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab == tabX8) {
                if (answer.isEmpty()) {
                    filteredX8.setPredicate(null);
                } else {
                    filteredX8.setPredicate(e -> e.getRegnumber() == Integer.parseInt(answer));
                }
            }
        }
    }

    @FXML
    private void resetCurrentTabFilters(ActionEvent event) {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab == tabX5) {
            filteredX5.setPredicate(null);
        } else if (selectedTab == tabX6) {
            filteredX6.setPredicate(null);
        } else if (selectedTab == tabX7) {
            filteredX7.setPredicate(null);
        } else if (selectedTab == tabX8) {
            filteredX8.setPredicate(null);
        }
    }

    @FXML
    private void resetAllFilters(ActionEvent event) {
        filteredX5.setPredicate(null);
        filteredX6.setPredicate(null);
        filteredX7.setPredicate(null);
        filteredX8.setPredicate(null);
    }

}
