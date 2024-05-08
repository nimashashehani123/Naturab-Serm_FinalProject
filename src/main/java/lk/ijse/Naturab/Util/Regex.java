package lk.ijse.Naturab.Util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";

        switch (textField){
            case ID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case TEL:
                filed = "^(?:\\+94\\s?)?(\\d{3})(?:-|\\s)?(\\d{7})$";
                break;
            case NAME:
                filed = "^[A-Za-z\s]{3,}$";
                break;
            case ADDRESS:
                filed = "^[A-Za-z\s]{3,}$";

                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";

            case SALARY:
                filed = "^\\d+(\\.\\d{1,2})?$";
                break;

            case PASSWORD:
                filed = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
                break;
            case QTY:
                filed = "^\\d+$";

        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField fields) {

        if (Regex.isTextFieldValid(location, fields.getText())) {
            fields.setStyle("-fx-text-fill: #000000;");
            return true;
        } else {
            fields.setStyle("-fx-text-fill: red;-fx-border-color: red; -fx-background-color: #fcb3b3;");
            return false;
        }
    }
}
