package timestable.ui;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import timestable.domain.TimesTable;
import timestable.domain.Vector;

/**
 * Starting program and drawing UI
 *
 * @author vikke
 */
public class TimesTableUi extends Application {

    private TimesTable timestable;
    private double multiplier = 0;
    private int totalVectors = 100;
    private double changeValue = 4;

    private Timeline timeline;

    @Override
    public void init() throws Exception {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));
        System.out.println("initializing timestable");

        timestable = new TimesTable();
        timestable.init(multiplier, totalVectors);
    }

    public Timeline animation(Pane lines) {
        KeyFrame keyframe = new KeyFrame(Duration.millis(20), e -> {
            multiplier += (changeValue * 0.001);
            timestable.updateVectors(multiplier, totalVectors);
            drawLines(lines);
        });

        Timeline timeline = new Timeline(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.stop();
        return timeline;
    }

    public void drawLines(Pane lines) {
        lines.getChildren().clear();
        for (Vector vector : timestable.getVectors()) {
            Line line = new Line(vector.getStartX(),
                    vector.getStartY(),
                    vector.getEndX(),
                    vector.getEndY());
            line.setStyle("-fx-stroke: linear-gradient(from 25% 25% to 100% 100%, rgba(102, 255, 255, 0.6), rgba(102, 255, 255, 0));");
//            line.setStyle("-fx-stroke: linear-gradient(from 0% 0% to 100% 100%, #dc143c, #661a33);");
            lines.getChildren().add(line);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("floating timestable");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        Pane lines = new Pane();
        int graphSize = timestable.getCircleRadius() * 2;
        lines.setMinSize(graphSize, graphSize);
        lines.setStyle("-fx-background-color: #222222;");
//        lines.setStyle("-fx-border-width: 100px;");
//        lines.setPadding();s
        drawLines(lines);

        Button quitButton = new Button();
        quitButton.setText("quit");
        Button adderButton = new Button();
        adderButton.setText("change direction");

        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("quit pressed");
                primaryStage.close();
            }
        });

        adderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("changing multiplier");
                changeValue = (-1) * changeValue;
            }
        });

        timeline = animation(lines); // TODO better way to init

        CheckBox playBox = new CheckBox();
        playBox.setText("play?");

        playBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                if (new_val) {
                    timeline.play();
                    System.out.println("play");
                } else {
                    timeline.stop();
                    System.out.println("stop");
                }
            }
        });

        Slider multiplierSlider = new Slider(-50, 50, 1);
        multiplierSlider.setValue(changeValue);
        Label multiplierLabel = new Label((Double.toString(changeValue)).format("Multiplier %.2f", changeValue));

        multiplierSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                changeValue = new_val.doubleValue();
                multiplierLabel.setText((Double.toString(changeValue)).format("Multiplier %.2f", changeValue));
            }
        });

        Slider lineCountSlider = new Slider(1, 1000, 1);
        lineCountSlider.setValue(totalVectors);
        Label lineCountLabel = new Label((Double.toString(totalVectors)).format("Lines %d", totalVectors));

        lineCountSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                totalVectors = new_val.intValue();
                lineCountLabel.setText((Integer.toString(totalVectors)).format("Lines %d", totalVectors));
                timestable.updateVectors(multiplier, totalVectors);
                drawLines(lines);
            }
        });

        grid.add(new Text("TimesTable"), 0, 0);
        grid.add(lines, 0, 1, 1, 7);

        grid.add(quitButton, 1, 0);
        grid.add(adderButton, 1, 1);
        grid.add(playBox, 1, 2);

        grid.add(multiplierSlider, 1, 3);
        grid.add(multiplierLabel, 1, 4);

        grid.add(lineCountSlider, 1, 5);
        grid.add(lineCountLabel, 1, 6);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() {
        System.out.println("quitting program");
    }

    public static void main(String[] args) {
        System.out.println("ui main");
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
