package stocks;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class Menu extends VBox {
    private Stage stage;
    public static final File FILE = new File("all_stocks_5yr.csv");

    public Menu(Stage stage) {
        super();
        this.stage = stage;
    }

    public void createAndShowMenu() {
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #2ECC71");

        Image logo = new Image("file:logo.jpg");

        Label label = new Label("Enter a company ticker");
        label.setFont(new Font("Arial", 36));

        TextField textField = new TextField();
        textField.setMaxWidth(200);

        Button submit = getSubmitButton(textField);

        Button companyListButton = getCompanyListButton();

        getChildren().addAll(new ImageView(logo), label, textField, submit, companyListButton);
        setSpacing(25);

        Scene s = new Scene(this, 1440, 900);
        stage.setScene(s);

        stage.show();
    }

    private Button getCompanyListButton() {
        Button b = new Button("Click for list of all companies");

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getChildren().remove(b);
                try {
                    getChildren().add(getCompanyListPane());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return b;
    }

    private ScrollPane getCompanyListPane() throws IOException {
        VBox vb = loadCompanies();

        ScrollPane sp = new ScrollPane();
        sp.setMaxWidth(150);
        sp.setPrefHeight(150);
        sp.setContent(vb);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        return sp;
    }

    private VBox loadCompanies() throws IOException {
        VBox vb = new VBox();

        FileReader fr = new FileReader(FILE);
        BufferedReader br = new BufferedReader(fr);
        DayReader dr = new DayReader(br);

        TreeSet<String> list = dr.getAllCompanyNames();
        for (String s : list) {
            vb.getChildren().add(new Label(s));
        }

        return vb;
    }

    private Button getSubmitButton(TextField tf) {
        Button submit = new Button("Submit");

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Company a = new Company(tf.getText().toUpperCase(), FILE);
                    StockViewer viewer = new StockViewer(a, stage);
                    viewer.createAndShowChart();
                } catch (IOException | CompanyDoesNotExistException e) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Could not find " + tf.getText() + ". Please enter another company.", ButtonType.OK);
                    alert.showAndWait();
                    tf.clear();
                    System.out.println(e.getMessage());
                }
            }
        });

        return submit;
    }


}
