package ua.ibis.avalfile.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ua.ibis.avalfile.util.MyCurrency;
import ua.ibis.avalfile.pojo.*;

import java.util.function.Predicate;

public class Controller {

    @FXML
    private ViewX5Controller viewX5Controller;

    @FXML
    private ViewX6Controller viewX6Controller;

    @FXML
    private ViewX7Controller viewX7Controller;

    @FXML
    private ViewX8Controller viewX8Controller;

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

    @FXML
    public void initialize() {
        // связываем доступность меню фильтрации с выбором вкладки #X8
//        filterAcc.disableProperty().bind(tabX8.selectedProperty());
//        filterTreat.disableProperty().bind(tabX8.selectedProperty());
//        filterCurrency.disableProperty().bind(tabX8.selectedProperty());
//        filterRegnmbr.disableProperty().bind(tabX8.selectedProperty().not());
        // связываем видимость меню фильтрации с выбором вкладки #X8
        filterAcc.visibleProperty().bind(tabX8.selectedProperty().not());
        filterTreat.visibleProperty().bind(tabX8.selectedProperty().not());
        filterCurrency.visibleProperty().bind(tabX8.selectedProperty().not());
        filterRegnmbr.visibleProperty().bind(tabX8.selectedProperty());
    }

    @FXML
    private void openX5(ActionEvent event){
        viewX5Controller.open(event);
        tabPane.getSelectionModel().select(tabX5);
    }

    @FXML
    private void openX6(ActionEvent event) {
        viewX6Controller.open(event);
        tabPane.getSelectionModel().select(tabX6);
    }

    @FXML
    private void openX7(ActionEvent event) {
        viewX7Controller.open(event);
        tabPane.getSelectionModel().select(tabX7);
    }


    @FXML
    private void openX8(ActionEvent event) {
        viewX8Controller.open(event);
        tabPane.getSelectionModel().select(tabX8);
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
                viewX5Controller.setPredicate(pr);
            } else if (selectedTab == tabX6) {
                viewX6Controller.setPredicate(pr);
            } else if (selectedTab == tabX7) {
                viewX7Controller.setPredicate(pr);
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
                viewX5Controller.setPredicate(pr);
            } else if (selectedTab == tabX6) {
                viewX6Controller.setPredicate(pr);
            } else if (selectedTab == tabX7) {
                viewX7Controller.setPredicate(pr);
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
                viewX5Controller.setPredicate(pr);
            } else if (selectedTab == tabX6) {
                viewX6Controller.setPredicate(pr);
            } else if (selectedTab == tabX7) {
                viewX7Controller.setPredicate(pr);
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
                    viewX8Controller.setPredicate(null);
                } else {
                    viewX8Controller.setPredicate(e -> e.getRegnumber() == Integer.parseInt(answer));
                }
            }
        }
    }

    @FXML
    private void resetCurrentTabFilters(ActionEvent event) {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab == tabX5) {
            viewX5Controller.setPredicate(null);
        } else if (selectedTab == tabX6) {
            viewX6Controller.setPredicate(null);
        } else if (selectedTab == tabX7) {
            viewX7Controller.setPredicate(null);
        } else if (selectedTab == tabX8) {
            viewX8Controller.setPredicate(null);
        }
    }

    @FXML
    private void resetAllFilters(ActionEvent event) {
        viewX5Controller.setPredicate(null);
        viewX6Controller.setPredicate(null);
        viewX7Controller.setPredicate(null);
        viewX8Controller.setPredicate(null);
    }
}
