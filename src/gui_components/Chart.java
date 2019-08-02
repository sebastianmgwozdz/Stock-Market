package gui_components;

import application.Company;
import application.Day;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Chart extends VBox {
    private LineChart<Number, Number> lineChart;
    private ArrayList<Company> companiesToDraw;

    public Chart() {
        this.companiesToDraw = new ArrayList<>();

        setChart();

        this.getChildren().add(lineChart);
    }

    public LineChart<Number, Number> getLineChart() {
        return lineChart;
    }

    public void addCompany(Company c) {
        companiesToDraw.add(c);
    }

    private void setChart() {
        final NumberAxis xAxis = new NumberAxis(2013, 2019, 1);
        xAxis.setStyle("-fx-tick-label-fill: rgb(0, 0, 0)");
        xAxis.setLabel("Year");

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setStyle("-fx-tick-label-fill: rgb(0, 0, 0)");
        yAxis.setLabel("Price Per Share ($)");

        this.lineChart = new LineChart<>(xAxis,yAxis);
        this.lineChart.setMinSize(1500, 830);
    }

    public void fillData() {
        for (Company company : this.companiesToDraw) {
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
            this.lineChart.getData().add(series);
        }
        this.companiesToDraw.removeAll(this.companiesToDraw);
    }
}
