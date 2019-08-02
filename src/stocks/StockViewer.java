package stocks;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class StockViewer {
    private Company company;
    private Stage stage;

    public StockViewer(Company company, Stage stage) {
        this.company = company;
        this.stage = stage;
    }

    public void createAndShowChart() {
        final LineChart<Number,Number> lineChart = getChart();

        XYChart.Series series = new XYChart.Series();
        series.setName(company.getDays().first().getName());

        // Fill series with data
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
        Scene scene  = new Scene(lineChart,1000,750);

        stage.setScene(scene);
        stage.show();
    }

    private LineChart<Number, Number> getChart() {
        final NumberAxis xAxis = new NumberAxis(2013, 2019, 1);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");

        LineChart<Number, Number> chart = new LineChart<>(xAxis,yAxis);
        chart.setTitle("Stock Viewer");

        return chart;

    }
}
