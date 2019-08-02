package gui_components;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StockViewer extends VBox {
    private Stage stage;
    private Chart chart;

    public StockViewer(Stage stage) {
        this.stage = stage;

        this.setStyle("-fx-background-color: #3498DB");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(25);
    }

    public Chart getChart() {
        return chart;
    }

    public void createAndShowChart() {
        this.chart = new Chart();
        chart.fillData();

        HBox buttonRow = new ButtonRow(stage, chart);

        this.getChildren().addAll(chart, buttonRow);

        Scene scene  = new Scene(this,1500,900);

        stage.setScene(scene);
        stage.show();
    }
}
