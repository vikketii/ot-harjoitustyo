package timestable.ui;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import timestable.dao.FileUserDao;
import timestable.domain.Settings;
import timestable.domain.TimesTableService;
import timestable.domain.User;
import timestable.domain.Vector;

/**
 * Starting program and drawing UI
 *
 * @author vikke
 */
public class TimesTableUi extends Application {

    private TimesTableService timesTableService;
    private FileUserDao userDao;
    private Settings settings;
    private String currentUser = "";

    @Override
    public void init() throws Exception {
        userDao = new FileUserDao("users.db");
        settings = new Settings();
        timesTableService = new TimesTableService(userDao, settings);
    }

    public Timeline animation(Canvas lines, Label progress) {
        KeyFrame keyframe = new KeyFrame(Duration.millis(20), e -> {
            settings.setMultiplier(settings.getMultiplier() + settings.getSpeed() * 0.001);
            timesTableService.updateTimesTable();
            drawLines(lines);
            progress.setText(String.format("Progress: %.2f", settings.getMultiplier()));
        });

        Timeline timeline = new Timeline(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);

        return timeline;
    }

    public void drawLines(Canvas lines) {
        GraphicsContext gc = lines.getGraphicsContext2D();
        gc.setFill(settings.getBgMainColor());
        gc.fillRect(0, 0, settings.getCircleRadius() * 2, settings.getCircleRadius() * 2);

        Stop[] stops = new Stop[]{new Stop(0, settings.getBgMainColor()), new Stop(0.4, settings.getLineMainColor())};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.REFLECT, stops);
        gc.setStroke(lg1);

        for (Vector vector : timesTableService.getVectors()) {
            gc.strokeLine(vector.getStartX(), vector.getStartY(), vector.getEndX(), vector.getEndY());
        }
    }

    public GridPane initGrid(Pos p) {
        GridPane grid = new GridPane();
        grid.setAlignment(p);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        return grid;
    }

    public Canvas initLineCanvas() {
        int canvasSize = settings.getCircleRadius() * 2;
        Canvas lineCanvas = new Canvas(canvasSize, canvasSize);
        drawLines(lineCanvas);
        return lineCanvas;
    }

    public Scene initGeneratorScene(Stage primaryStage, Label titleLabel, Canvas lineCanvas) {
        GridPane grid = initGrid(Pos.TOP_CENTER);

        Label progress = new Label(String.format("Progress: %.2f", settings.getMultiplier()));
        Timeline timeline = animation(lineCanvas, progress);

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
            @Override
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                if (new_val) {
                    timeline.play();
                } else {
                    timeline.stop();
                }
            }
        });

        TextField multiplierTextField = new TextField();
        multiplierTextField.setPromptText("Set progress value");
        multiplierTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                settings.setMultiplier(Double.parseDouble(multiplierTextField.getText().replace(",", ".")));
                progress.setText(String.format("Progress: %.2f", settings.getMultiplier()));
                timesTableService.updateTimesTable();
                drawLines(lineCanvas);
            }
        });

        Slider speedSlider = new Slider(-50, 50, 1);
        speedSlider.setValue(settings.getSpeed());
        Label speedLabel = new Label(String.format("Change speed: %d", settings.getSpeed()));
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                settings.setSpeed(new_val.intValue());
                speedLabel.setText(String.format("Change speed: %d", settings.getSpeed()));
            }
        });

        Label totalVectorsLabel = new Label(String.format("Lines: %d", settings.getTotalVectors()));
        TextField totalVectorsTextField = new TextField();
        totalVectorsTextField.setPromptText("Set line count");
        totalVectorsTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                settings.setTotalVectors(Integer.parseInt(totalVectorsTextField.getText().replace(",", ".")));
                totalVectorsLabel.setText(String.format("Lines: %d", settings.getTotalVectors()));
                timesTableService.updateTimesTable();
                drawLines(lineCanvas);
            }
        });

        Label lineColorLabel = new Label("Line color:");
        ColorPicker lineColorPicker = new ColorPicker();
        lineColorPicker.setValue(settings.getLineMainColor());
        lineColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                settings.setLineMainColor(lineColorPicker.getValue());
                drawLines(lineCanvas);
            }
        });

        Label backgroundColorLabel = new Label("Background color:");
        ColorPicker backgroundColorPicker = new ColorPicker();
        backgroundColorPicker.setValue(settings.getBgMainColor());
        backgroundColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                settings.setBgMainColor(backgroundColorPicker.getValue());
                drawLines(lineCanvas);
            }
        });

        Button saveSettings = new Button("Save settings");
        saveSettings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                userDao.update(new User(currentUser, settings));
            }
        });

        grid.add(titleLabel, 0, 0);
        grid.add(quitButton, 1, 0);

        grid.add(lineCanvas, 0, 1, 1, 16);

        grid.add(playBox, 1, 2);
        grid.add(progress, 1, 3);
        grid.add(multiplierTextField, 1, 4);
        grid.add(speedLabel, 1, 5);
        grid.add(speedSlider, 1, 6);
        grid.add(totalVectorsLabel, 1, 7);
        grid.add(totalVectorsTextField, 1, 8);

        grid.add(lineColorLabel, 1, 9);
        grid.add(lineColorPicker, 1, 10);
        grid.add(backgroundColorLabel, 1, 11);
        grid.add(backgroundColorPicker, 1, 12);

        grid.add(saveSettings, 1, 13);

        Scene generatorScene = new Scene(grid);

        return generatorScene;
    }

    public Scene initGreeterScene(Stage primaryStage, Scene generatorScene, Label titleLabel, Canvas lineCanvas) {
        GridPane greeter = initGrid(Pos.CENTER);

        Label nameLabel = new Label("Make a new user: ");
        TextField nameField = new TextField();

        Button welcome = new Button("Add user");
        welcome.setDefaultButton(true);
        welcome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                currentUser = nameField.getText();
                timesTableService.saveUser(new User(currentUser));
                titleLabel.setText("TimesTable | User: " + currentUser);
                primaryStage.setScene(generatorScene);
            }
        });

        Label chooseUserLabel = new Label("or choose from the list: ");
        List<String> names = timesTableService.getAllUsersNames();

        ChoiceBox userList = new ChoiceBox(FXCollections.observableArrayList(names));
        userList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                currentUser = names.get(new_value.intValue());

                Settings temp = timesTableService.findUserByName(currentUser).getSettings();
                settings.setSpeed(temp.getSpeed());
                settings.setTotalVectors(temp.getTotalVectors());
                settings.setMultiplier(temp.getMultiplier());
                settings.setLineMainColor(temp.getLineMainColor());
                settings.setBgMainColor(temp.getBgMainColor());

                timesTableService.updateTimesTable();
                drawLines(lineCanvas);

                titleLabel.setText("TimesTable | User: " + currentUser);
                primaryStage.setScene(generatorScene);
            }
        });

        greeter.add(nameLabel, 0, 0);
        greeter.add(nameField, 0, 1);
        greeter.add(welcome, 1, 1);

        greeter.add(chooseUserLabel, 0, 2);
        greeter.add(userList, 0, 3);

        Scene greeterScene = new Scene(greeter);
        return greeterScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TimesTable");
        Label titleLabel = new Label("TimesTable | User: ");
        Canvas lineCanvas = initLineCanvas();

        Scene generatorScene = initGeneratorScene(primaryStage, titleLabel, lineCanvas);
        Scene greeterScene = initGreeterScene(primaryStage, generatorScene, titleLabel, lineCanvas);

        primaryStage.setScene(greeterScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
        }
    }
}
