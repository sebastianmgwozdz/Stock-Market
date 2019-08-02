package gui_components;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

// Models a formatted label for a specific data point
public class DataLabel extends Label {
    public DataLabel(String text) {
        super(text);
        // Reposition the box
        super.setTranslateX(50);
        super.setTranslateY(100);

        super.setStyle("-fx-background-color: gray;");

        // Set text color to white
        super.setTextFill(Color.web("#FFFFFF"));
        super.setMinSize(150, 115);


    }
}
