package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import sample.pojo.*;
import sample.task.X5Task;
import sample.task.X6Task;
import sample.task.X7Task;
import sample.task.X8Task;
import sample.util.MyCurrency;
import sample.util.PropertiesValues;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

public class Controller {

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
    private void initialize() {
        initData();
    }

    private void createThread(Task task, TableView<?> table) {
        table.itemsProperty().bind(task.valueProperty());

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
        createThread(new X5Task(path), tableX5);
        labelX5.setText(path.toString());

        path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x6.file"));
        createThread(new X6Task(path), tableX6);
        labelX6.setText(path.toString());

        path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x7.file"));
        createThread(new X7Task(path), tableX7);
        labelX7.setText(path.toString());

        path = Paths.get(config.getProperty("dirfiles"), config.getProperty("x8.file"));
        createThread(new X8Task(path),tableX8);
        labelX8.setText(path.toString());
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
            createThread(new X5Task(filepath), tableX5);
            labelX5.setText(filepath.toString());
            tabPane.getSelectionModel().select(tabX5);
        }
    }

    @FXML
    private void openX6(ActionEvent event) {

        Path filepath = selectFile("Choose #X6", "#X6 files", "#X6*.*");

        if(filepath != null) {
            createThread(new X6Task(filepath), tableX6);
            labelX6.setText(filepath.toString());
            tabPane.getSelectionModel().select(tabX6);
        }
    }

    @FXML
    private void openX7(ActionEvent event) {

        Path filepath = selectFile("Choose #X7", "#X7 files", "#X7*.*");

        if(filepath != null) {
            createThread(new X7Task(filepath), tableX7);
            labelX7.setText(filepath.toString());
            tabPane.getSelectionModel().select(tabX7);
        }
    }


    @FXML
    private void openX8(ActionEvent event) {

        Path filepath = selectFile("Choose #X8", "#X8 files", "#X8*.*");

        if(filepath != null) {
            createThread(new X8Task(filepath), tableX8);
            labelX8.setText(filepath.toString());
            tabPane.getSelectionModel().select(tabX8);
        }
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void changeTab(Event event) {
        if(event.getSource() != tabX8) {
            filterAcc.setDisable(false);
            filterTreat.setDisable(false);
            filterCurrency.setDisable(false);
            filterRegnmbr.setDisable(true);
        } else {
            filterAcc.setDisable(true);
            filterTreat.setDisable(true);
            filterCurrency.setDisable(true);
            filterRegnmbr.setDisable(false);
        }
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
