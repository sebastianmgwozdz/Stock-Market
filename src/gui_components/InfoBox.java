package gui_components;

import application.Day;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class InfoBox extends StackPane {
    private Day day;

    public InfoBox(Day day) {
        this.day = day;
        setInfoBox();
        this.setMaxSize(1, 1);

    }

    // Sets info box so that it pops up when point is hovered, disappears when no longer hovered
    private void setInfoBox() {
        DataLabel label = new DataLabel(getInfoText());

        InfoBox thisBox = this;
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                thisBox.getChildren().setAll(label);
                thisBox.toFront();
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                thisBox.getChildren().remove(label);
            }
        });
    }

    // Formats string to fit box
    private String getInfoText() {
        String s = "";
        s += "  Date: " + day.getDate().toString();
        for (String category : day.getData().keySet()) {
            s += "\n  " + capitalize(category) + ": " + day.getData().get(category);
        }

        return s;
    }

    // Capitalizes the first letter of the given string
    private String capitalize(String s) {
        String capitalized = "";
        capitalized += s.substring(0, 1).toUpperCase();
        for (int i = 1; i < s.length(); i++) {
            capitalized += s.substring(i, i + 1);
        }
        return capitalized;
    }
}
