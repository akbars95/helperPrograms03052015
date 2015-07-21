package com.mtsmda.tools.gui.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;

import java.util.Optional;

/**
 * Created by c-DMITMINZ on 20.07.2015.
 */
public class AlertMTSMDA extends Alert{

    public AlertMTSMDA(AlertType alertType) {
        super(alertType);
    }

    public AlertMTSMDA(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
    }

    private static Alert alertProcessing(AlertType alertType, String titleText, String headerText, String contentText){
        Alert alert = new Alert(alertType);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    private static Alert getStandardAlertInner(AlertType alertType, String titleText, String headerText, String contentText){
        Alert alert = alertProcessing(alertType, titleText, headerText, contentText);
        alert.setWidth(400);
        alert.showAndWait();
        return alert;
    }

    public static void getStandardAlert(AlertType alertType, String titleText, String headerText, String contentText){
        getStandardAlertInner(alertType, titleText, headerText, contentText);
    }

    public static void getStandardInformationAlert(String headerText, String contentText){
        Alert alert = alertProcessing(AlertType.INFORMATION, "INFORMATION", headerText, contentText);
        alert.setWidth(400);
        alert.showAndWait();
    }

    public static void getStandardErrorAlert(String headerText, String contentText){
        Alert alert = alertProcessing(AlertType.ERROR, "ERROR", headerText, contentText);
        alert.setWidth(400);
        alert.showAndWait();
    }

    public static boolean getConfirmationAlert(String titleText, String headerText, String contentText){
        Alert alert = alertProcessing(AlertType.CONFIRMATION, titleText, headerText, contentText);
        Optional<ButtonType> buttonTypeOptional = alert.showAndWait();
        if(buttonTypeOptional.get() == ButtonType.OK){
            return true;
        }else{
            return false;
        }
    }

    public static boolean getProgressBarAlert(String titleText, String headerText, String contentText){
        Alert alert = alertProcessing(AlertType.INFORMATION, titleText, headerText, contentText);
        Optional<ButtonType> buttonTypeOptional = alert.showAndWait();

        ProgressBar progressBar = new ProgressBar();

        

        if(buttonTypeOptional.get() == ButtonType.OK){
            return true;
        }else{
            return false;
        }
    }

}
