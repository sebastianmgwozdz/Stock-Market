package application;

import gui_components.InfoBox;
import javafx.scene.chart.XYChart;

import java.util.TreeMap;

// Represents one recorded day/line from the data file
public class Day implements Comparable<Day>{
    private Date date;
    private TreeMap<String, Double> data;
    private String name;

    public Day(Date date, TreeMap<String, Double> data, String name) {
        this.date = date;
        this.data = data;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public TreeMap<String, Double> getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    // Day objects are sorted alphabetically
    public int compareTo(Day other) {
        return date.toString().compareTo(other.date.toString());
    }

    public boolean equals(Object other) {
        // Checks class type equality
        if (getClass() != other.getClass()) {
            return false;
        }
        Day that = (Day) other;
        return this.compareTo(that) == 0;
    }

    // Returns an object with the x/y values of the point
    public XYChart.Data getDataPoint() {
        // X axis represents the date, y represents the closing price of the stock
        double x = Integer.parseInt(date.getYear()) + Integer.parseInt(date.getMonth()) / 12.0;
        double y = data.get("close");

        XYChart.Data<Number, Number> d = new XYChart.Data<>(x, y);

        InfoBox sp = new InfoBox(this);
        d.setNode(sp);

        return d;
    }
}
