package stocks;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class DataLabel extends Label {
    public DataLabel(String text) {
        super(text);
        super.setTranslateX(50);
        super.setTranslateY(100);
        super.setStyle("-fx-background-color: gray;");
        super.setTextFill(Color.web("#FFFFFF"));
        super.setMinSize(150, 115);


    }
}
