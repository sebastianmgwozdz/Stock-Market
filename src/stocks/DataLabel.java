package stocks;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class DataLabel extends Label {
    public DataLabel(String text) {
        super(text);
        super.setTranslateY(150);
        super.setStyle("-fx-border-color: black;");
        super.setMinSize(125, 115);


    }
}
