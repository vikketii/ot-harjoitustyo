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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;
import timestable.dao.FileUserDao;
import timestable.domain.TimesTable;
import timestable.domain.TimesTableService;
import timestable.domain.Vector;

/**
 * Starting program and drawing UI
 *
 * @author vikke
 */
public class TimesTableUi extends Application {

    private TimesTableService timesTableService;
    private TimesTable timesTable;

    private int totalVectors = 100;
    private double multiplier = 0;
    private double changeValue = 2;

    private Color lineMainColor = Color.rgb(102, 255, 255, 0.6);
    private Color bgMainColor = Color.rgb(40, 40, 40);

    private String currentUser = "";

    @Override
    public void init() throws Exception {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));

        FileUserDao userDao = new FileUserDao("users.db");
        timesTableService = new TimesTableService(userDao);

        timesTable = new TimesTable(multiplier, totalVectors);
    }

    public Timeline animation(Canvas lines, Label progress) {
        KeyFrame keyframe = new KeyFrame(Duration.millis(20), e -> {
            multiplier += (changeValue * 0.001);
            timesTable.updateVectors(multiplier, totalVectors);
            drawLines(lines);
            progress.setText((Double.toString(multiplier)).format("Progress %.2f", multiplier));
        });

        Timeline timeline = new Timeline(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);

        return timeline;
    }

    public void drawLines(Canvas lines) {
        GraphicsContext gc = lines.getGraphicsContext2D();
        gc.setFill(bgMainColor);
        gc.fillRect(0, 0, timesTable.getCircleRadius() * 2, timesTable.getCircleRadius() * 2);

        Stop[] stops = new Stop[]{new Stop(0, bgMainColor), new Stop(1, lineMainColor)};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.REFLECT, stops);
        gc.setStroke(lg1);

        for (Vector vector : timesTable.getVectors()) {
            gc.strokeLine(vector.getStartX(), vector.getStartY(), vector.getEndX(), vector.getEndY());
        }
    }

    public GridPane initGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        return grid;
    }

    public Canvas initLineCanvas() {
        int canvasSize = timesTable.getCircleRadius() * 2;
        Canvas lines = new Canvas(canvasSize, canvasSize);

        drawLines(lines);
        return lines;
    }

    public Scene initGeneratorScene(Stage primaryStage, Label titleLabel) {
        GridPane grid = initGrid();
        Canvas lines = initLineCanvas();

        Label progress = new Label((Double.toString(multiplier)).format("Progress %.2f", multiplier));
        Timeline timeline = animation(lines, progress);

        Button quitButton = new Button();
        quitButton.setText("quit");
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        CheckBox playBox = new CheckBox();
        playBox.setText("Play");
        playBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                if (new_val) {
                    timeline.play();
                } else {
                    timeline.stop();
                }
            }
        });

        Slider multiplierSlider = new Slider(-100, 100, 1);
        Label multiplierLabel = new Label((Double.toString(changeValue)).format("Speed: %.2f", changeValue));
        multiplierSlider.setValue(changeValue);
        multiplierSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                changeValue = new_val.doubleValue();
                multiplierLabel.setText((Double.toString(changeValue)).format("Speed: %.2f", changeValue));
            }
        });

        Slider lineCountSlider = new Slider(1, 100, 1);
        Label lineCountLabel = new Label((Integer.toString(totalVectors)).format("Lines %d", totalVectors));
        lineCountSlider.setValue((int) Math.sqrt(totalVectors));
        lineCountSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                totalVectors = new_val.intValue() * new_val.intValue();
                lineCountLabel.setText((Integer.toString(totalVectors)).format("Lines %d", totalVectors));
                timesTable.updateVectors(multiplier, totalVectors);
                drawLines(lines);
            }
        });

        ColorPicker mainColorPicker = new ColorPicker();
        mainColorPicker.setValue(lineMainColor);
        mainColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                lineMainColor = mainColorPicker.getValue();
                drawLines(lines);
            }
        });

        grid.add(titleLabel, 0, 0);
        grid.add(quitButton, 1, 0);
        grid.add(lines, 0, 1, 1, 9);

        grid.add(playBox, 1, 2);
        grid.add(multiplierLabel, 1, 3);
        grid.add(multiplierSlider, 1, 4);
        grid.add(lineCountLabel, 1, 5);
        grid.add(lineCountSlider, 1, 6);
        grid.add(progress, 1, 7);

        grid.add(mainColorPicker, 1, 8);

        Scene generatorScene = new Scene(grid);

        return generatorScene;
    }

    public Scene initGreeterScene(Stage primaryStage, Scene generatorScene, Label titleLabel) {
        GridPane greeter = new GridPane();
        greeter.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();

        Button welcome = new Button("continue");
        welcome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                currentUser = nameField.getText();
                titleLabel.setText("TimesTable | User: " + currentUser);
                primaryStage.setScene(generatorScene);
            }
        });

        greeter.add(nameLabel, 0, 0);
        greeter.add(nameField, 1, 0);
        greeter.add(welcome, 1, 1);

        Scene greeterScene = new Scene(greeter);
        return greeterScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("floating timesTable");
        Label titleLabel = new Label("TimesTable | User: ");

        Scene generatorScene = initGeneratorScene(primaryStage, titleLabel);
        Scene greeterScene = initGreeterScene(primaryStage, generatorScene, titleLabel);

        primaryStage.setScene(greeterScene);
        primaryStage.show();
    }

    @Override
    public void stop() {
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
