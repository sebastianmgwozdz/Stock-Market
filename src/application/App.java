package application;

import gui_components.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;

public class App extends Application {
    public static final File FILE = new File("all_stocks_5yr.csv");

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Stock Market");
        Menu menu = new Menu(stage);
        menu.createAndShowMenu();
    }
}
