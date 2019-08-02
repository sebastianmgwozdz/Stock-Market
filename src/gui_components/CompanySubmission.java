package gui_components;

import application.Company;
import application.CompanyDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class CompanySubmission extends VBox {
    private Label prompt;
    private TextField tf;
    private Button submitButton;

    public CompanySubmission(Stage stage) {
        setAlignment(Pos.CENTER);
        setSpacing(15);

        this.prompt = new Label("Enter a company ticker:");
        this.prompt.setFont(new Font("Arial", 36));

        this.tf = new TextField();
        tf.setMaxWidth(200);

        submitButton = new Button("Submit");
        setSubmitButton(stage);

        this.getChildren().addAll(prompt, tf, submitButton);
    }

    // Sets button to display stock window given a valid ticker
    private void setSubmitButton(Stage stage) {
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StockViewer viewer = new StockViewer(stage);

                if (!tf.getText().equals("")) {
                    try {
                        Company company = new Company(tf.getText().toUpperCase());

                        viewer.getChart().addCompany(company);
                        viewer.createAndShowChart();
                    } catch (IOException | CompanyDoesNotExistException e) {
                        Alert alert = new Alert(Alert.AlertType.NONE, "Could not find " + tf.getText() + ". Please enter another company.", ButtonType.OK);
                        alert.showAndWait();

                        tf.clear();
                    }
                }
            }
        });
    }
}
