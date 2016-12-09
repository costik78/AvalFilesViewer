package ua.ibis.avalfile.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
//import javafx.stage.Modality;

import java.util.Optional;

/**
 * Created by conti on 25.11.2016.
 */
public class MyDialog {

    public static final String askDialog(String title, String info, String asking) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(info);
        dialog.setContentText(asking);
//        dialog.initModality(Modality.NONE);

        Optional<String> result = dialog.showAndWait();
        return (result.isPresent() ? result.get().trim() : null);
    }

//    public static final void errMessage(Exception e) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Exception");
//        alert.setContentText(e.getMessage());
//
//        Label label = new Label("The exception stacktrace was:");
//
//        TextArea textArea = new TextArea(e.);
//        textArea.setEditable(false);
//        textArea.setWrapText(true);
//
//        textArea.setMaxWidth(Double.MAX_VALUE);
//        textArea.setMaxHeight(Double.MAX_VALUE);
////        GridPane.setVgrow(textArea, Priority.ALWAYS);
////        GridPane.setHgrow(textArea, Priority.ALWAYS);
//
//        GridPane expContent = new GridPane();
//        expContent.setMaxWidth(Double.MAX_VALUE);
//        expContent.add(label, 0, 0);
//        expContent.add(textArea, 0, 1);
//
//        // Set expandable Exception into the dialog pane.
//        alert.getDialogPane().setExpandableContent(expContent);
//
//        alert.showAndWait();
//    }
}
