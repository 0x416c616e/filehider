
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;

public class PopupExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Popup Example");
        final Popup popup = new Popup();
        popup.setX(300);
        popup.setY(200);
        
        Label testLabel = new Label("test");
        GridPane testPane = new GridPane();
        testPane.add(testLabel, 0, 0);
        popup.getContent().addAll(testPane);

        Button show = new Button("Show");
        //show.setOnAction(new EventHandler<ActionEvent>() {
        //    @Override
        //    public void handle(ActionEvent event) {
        //        popup.show(primaryStage);
        //    }
        //});
        
        show.setOnAction(e -> { 
                popup.show(primaryStage);
        });
        

        Button hide = new Button("Hide");
        //hide.setOnAction(new EventHandler<ActionEvent>() {
         //   @Override
        //    public void handle(ActionEvent event) {
        //        popup.hide();
        //    }
        //});
        
        
        hide.setOnAction(e -> { 
                popup.hide();
        });
        
        HBox layout = new HBox(10);
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
        layout.getChildren().addAll(show, hide);
        primaryStage.setScene(new Scene(layout));
        primaryStage.show();
    }
}