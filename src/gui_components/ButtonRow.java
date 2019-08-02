package gui_components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonRow extends HBox {
    private Button returnButton;
    private Stage stage;

    public ButtonRow(Stage stage, Chart c) {
        setAlignment(Pos.CENTER);
        setSpacing(40);

        this.stage = stage;

        CompanyComparison cc = new CompanyComparison(c);

        this.returnButton = new Button("Return to Main Menu");
        setReturnButton();

        this.getChildren().addAll(cc, returnButton);
    }

    private void setReturnButton() {
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Menu menu = new Menu(stage);
                menu.createAndShowMenu();
            }
        });
    }
}
