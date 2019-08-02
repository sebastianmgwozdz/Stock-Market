package gui_components;

import application.App;
import application.DayReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

// Shows a list of all companies found in the file
public class CompanyList extends VBox {
    private Button button;
    private ScrollPane list;

    public CompanyList() {
        setAlignment(Pos.CENTER);

        setButton();

        try {
            setList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getChildren().add(button);
    }

    // Creates button that displays the list when pressed
    private void setButton() {
        Button button = new Button("List of companies");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CompanyList.super.getChildren().remove(button);
                CompanyList.super.getChildren().add(list);
            }
        });

        this.button = button;
    }

    // Creates the scroll window
    private void setList() throws IOException {
        VBox vb = loadCompanies();

        ScrollPane sp = new ScrollPane();
        sp.setMaxWidth(150);
        sp.setPrefHeight(150);
        sp.setContent(vb);

        // Sets the scroll bar to always be visible
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.list = sp;
    }

    // Gets all company names and loads them into the list
    private VBox loadCompanies() throws IOException {
        VBox vb = new VBox();

        FileReader fr = new FileReader(App.FILE);
        BufferedReader br = new BufferedReader(fr);
        DayReader dr = new DayReader(br);

        TreeSet<String> list = dr.getAllCompanyNames();
        for (String s : list) {
            vb.getChildren().add(new Label(s));
        }

        return vb;
    }
}
