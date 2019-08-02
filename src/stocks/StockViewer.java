package stocks;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StockViewer {
    private ArrayList<Company> companiesToDraw;
    private Stage stage;

    public StockViewer(Company company, Stage stage) {
        this.companiesToDraw = new ArrayList<>();
        this.companiesToDraw.add(company);
        this.stage = stage;
    }

    public void createAndShowChart() {
        VBox vb = new VBox();
        vb.setStyle("-fx-background-color: #3498DB");
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(25);

        final LineChart<Number,Number> lineChart = getChart();


        // Fill series with data
        fillData(lineChart);

        HBox buttonRowContainer = new HBox();

        Pane returnButtonPane = getReturnButtonPane();

        HBox comparePane = new HBox();
        TextField tf = new TextField();
        Button compareButton = getCompareButton(tf, lineChart);
        comparePane.getChildren().addAll(tf, compareButton);

        buttonRowContainer.getChildren().addAll(returnButtonPane, tf, compareButton);

        lineChart.setMinSize(1500, 830);

        vb.getChildren().addAll(lineChart, buttonRowContainer);

        Scene scene  = new Scene(vb,1500,900);

        stage.setScene(scene);
        stage.show();
    }

    private void fillData(LineChart<Number, Number> lineChart) {
        for (Company company : companiesToDraw) {
            XYChart.Series series = new XYChart.Series();
            series.setName(company.getDays().first().getName());

            String month = company.getDays().first().getDate().getMonth();
            for (Day d : company.getDays()) {
                String currentMonth = d.getDate().getMonth();

                // Pull no more than one point of data from each month
                if (!(month.equals(currentMonth))) {
                    XYChart.Data data = d.getDataPoint();
                    series.getData().add(data);
                    month = currentMonth;
                }
            }
            lineChart.getData().add(series);
        }
        companiesToDraw.removeAll(companiesToDraw);
    }

    private Button getCompareButton(TextField tf, LineChart<Number, Number> lineChart) {
        Button b = new Button("Compare");

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Company c = new Company(tf.getText().toUpperCase(), Menu.FILE);
                    companiesToDraw.add(c);
                    fillData(lineChart);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CompanyDoesNotExistException e) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Could not find " + tf.getText() + ". Please enter another company.", ButtonType.OK);
                    alert.showAndWait();

                    tf.clear();
                }
            }
        });

        return b;
    }

    private LineChart<Number, Number> getChart() {
        final NumberAxis xAxis = new NumberAxis(2013, 2019, 1);
        xAxis.setStyle("-fx-tick-label-fill: rgb(0, 0, 0)");
        xAxis.setLabel("Year");

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setStyle("-fx-tick-label-fill: rgb(0, 0, 0)");
        yAxis.setLabel("Price Per Share ($)");

        LineChart<Number, Number> chart = new LineChart<>(xAxis,yAxis);

        return chart;

    }

    private Pane getReturnButtonPane() {
        Button button = new Button("Return to Main Menu");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Menu menu = new Menu(stage);
                menu.createAndShowMenu();
            }
        });

        Pane p = new Pane();
        p.getChildren().add(button);

        return p;
    }
}
