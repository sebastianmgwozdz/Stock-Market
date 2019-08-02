package gui_components;

import application.Company;
import application.CompanyDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CompanyComparison extends HBox {
    private TextField tf;
    private Button compareButton;

    public CompanyComparison(Chart c) {
        setSpacing(10);

        this.tf = new TextField();

        this.compareButton = new Button("Add Company");
        setCompareButton(c);

        this.getChildren().addAll(tf, compareButton);
    }

    private void setCompareButton(Chart chart) {
        compareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tf.getText().equals("")) {
                    return;
                }

                // Try to add company data
                try {
                    Company c = new Company(tf.getText().toUpperCase());
                    chart.addCompany(c);
                    chart.fillData();
                } catch (IOException | CompanyDoesNotExistException e) {
                    // Show dialog on user's screen alerting them
                    Alert alert = new Alert(Alert.AlertType.NONE, "Could not find " + tf.getText() + ". Please enter another company.", ButtonType.OK);
                    alert.showAndWait();

                    tf.clear();
                }
            }
        });
    }
}
