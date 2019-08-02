package stocks;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private File file;

    public Menu(Stage stage) {
        super();
        this.stage = stage;
        this.file = new File("all_stocks_5yr.csv");
    }

    public void createAndShowMenu() {
        setAlignment(Pos.CENTER);

        Label label = new Label("Enter a company ticker");
        label.setFont(new Font("Arial", 36));

        TextField textField = new TextField();
        textField.setMaxWidth(100);

        Button submit = getSubmitButton(textField);

        Button companyListButton = getCompanyListButton();

        getChildren().addAll(label, textField, submit, companyListButton);
        setSpacing(10);

        Scene s = new Scene(this, 1080, 720);
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
        sp.setPrefHeight(135);
        sp.setContent(vb);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        return sp;
    }

    private VBox loadCompanies() throws IOException {
        VBox vb = new VBox();

        FileReader fr = new FileReader(file);
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
                    Company a = new Company(tf.getText(), file);
                    StockViewer viewer = new StockViewer(a, stage);
                    viewer.createAndShowChart();
                } catch (IOException | CompanyDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        return submit;
    }


}
