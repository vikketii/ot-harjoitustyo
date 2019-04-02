package timestable.ui;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timestable.domain.TimesTable;
import timestable.domain.Vector;

/**
 * Starting program and drawing UI
 * @author vikke
 */

public class TimesTableUi extends Application {
    private TimesTable timestable;
    
    @Override
    public void init() throws Exception {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));
        System.out.println("initializing timestable");
        
        timestable = new TimesTable();
        timestable.init(2, 10); // 2 is multiplier and 10 is totalVectors
    }
    
    public void initLines(Pane lines) {
        for (Vector vector : timestable.getVectors()) {
            Line line = new Line(vector.getStartX(),
                                 vector.getStartY(),
                                 vector.getEndX(),
                                 vector.getEndY());
            line.setStroke(Color.BLACK);
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
        lines.setStyle("-fx-background-color: #DDDDDD;");

        initLines(lines);
        
        // Circle for testing
        Circle circle = new Circle(timestable.getCircleRadius(),
                                   timestable.getCircleRadius(),
                                   timestable.getCircleRadius(), 
                                   Color.TRANSPARENT);
        circle.setStroke(Color.GRAY);
        lines.getChildren().add(circle);
        
        
        
        Button quitButton = new Button();
        quitButton.setText("Quit");
        Button dummyButton1 = new Button();
        dummyButton1.setText("dummy1");
        Button dummyButton2 = new Button();
        dummyButton2.setText("dummy2");
        
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("quit pressed");
                primaryStage.close();
            }
        });
        
        grid.add(new Text("TimesTable"), 0, 0);
        grid.add(lines, 0, 1);
        grid.add(quitButton, 1, 0);
        grid.add(dummyButton1, 1, 1);
        grid.add(dummyButton2, 1, 2);

        Scene scene = new Scene(grid, 800, 800);
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
