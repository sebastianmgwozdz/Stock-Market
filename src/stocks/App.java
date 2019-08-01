package stocks;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        File f = new File("all_stocks_5yr.csv");
        Company a = new Company("AAPL", f);
        createAndShowChart(a, stage);
    }

    private void createAndShowChart(Company c, Stage stage) {
        stage.setTitle("Line Chart Sample");
        final LineChart<Number,Number> lineChart = getChart();

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        String month = c.getDays().first().getDate().getMonth();
        for (Day d : c.getDays()) {
            String currentMonth = d.getDate().getMonth();
            if (!(month.equals(currentMonth))) {
                XYChart.Data data = d.getDataPoint();
                series.getData().add(data);
                month = currentMonth;
            }
        }

        lineChart.getData().add(series);
        Scene scene  = new Scene(lineChart,1000,750);


        stage.setScene(scene);
        stage.show();
    }

    private LineChart<Number, Number> getChart() {
        final NumberAxis xAxis = new NumberAxis(2013, 2020, 1);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");

        LineChart<Number, Number> chart = new LineChart<Number,Number>(xAxis,yAxis);
        chart.setTitle("Stock Monitoring, 2010");

        return chart;

    }
}
