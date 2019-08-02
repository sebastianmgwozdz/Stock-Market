package gui_components;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Models the main menu
public class Menu extends VBox {
    private Stage stage;

    public Menu(Stage stage) {
        this.stage = stage;
    }

    public void createAndShowMenu() {
        // Formats the window
        setAlignment(Pos.CENTER);
        setSpacing(25);
        setStyle("-fx-background-color: #2ECC71");

        // Load menu logo, text field, and company list
        Image logo = new Image("file:logo.jpg");
        CompanySubmission submission = new CompanySubmission(stage);
        CompanyList list = new CompanyList();

        getChildren().addAll(new ImageView(logo), submission, list);

        Scene s = new Scene(this, 1500, 900);
        stage.setScene(s);

        stage.show();
    }
}
