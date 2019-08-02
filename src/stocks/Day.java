package stocks;

import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.util.TreeMap;

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

    public int compareTo(Day other) {
        if (!(date.getYear().equals(other.date.getYear()))) {
            return date.getYear().compareTo(other.date.getYear());
        }
        else if (!(date.getMonth().equals(other.date.getMonth()))) {
            return date.getMonth().compareTo(other.date.getMonth());
        }
        else {
            return date.getDay().compareTo((other.date.getDay()));
        }
    }

    public boolean equals(Object other) {
        if (getClass() != other.getClass()) {
            return false;
        }
        Day that = (Day) other;
        return this.compareTo(that) == 0;
    }

    public XYChart.Data getDataPoint() {
        double x = Integer.parseInt(date.getYear()) + Integer.parseInt(date.getMonth()) / 12.0;
        double y = data.get("close");
        XYChart.Data<Number, Number> d = new XYChart.Data<>(x, y);

        StackPane sp = getInfoBox();
        d.setNode(sp);

        return d;
    }

    private StackPane getInfoBox() {
        StackPane sp = new StackPane();
        DataLabel label = new DataLabel(getInfoText());

        sp.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                sp.getChildren().setAll(label);
                sp.toFront();
            }
        });

        sp.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                sp.getChildren().remove(label);
            }
        });

        sp.setMaxSize(1, 1);

        return sp;
    }

    private String getInfoText() {
        String s = "";
        s += "  Date: " + date.toString();
        for (String category : data.keySet()) {
            s += "\n  " + capitalize(category) + ": " + data.get(category);
        }

        return s;
    }

    private String capitalize(String s) {
        String capitalized = "";
        capitalized += s.substring(0, 1).toUpperCase();
        for (int i = 1; i < s.length(); i++) {
            capitalized += s.substring(i, i + 1);
        }
        return capitalized;
    }

}
