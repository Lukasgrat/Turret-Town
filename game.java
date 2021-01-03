import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import java.util.Optional;
import javafx.scene.Scene;
import java.util.Arrays;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
public class game extends Application{
    public  Rectangle[] Buttonlist = new Rectangle[100]; 
    public void start(Stage primaryStage){
        Pane pane = new Pane();
        for(int x = 0; x < 100;x++){
            pane.getChildren().add(Buttonlist[x]);
            Buttonlist[x].setX((x%10)*50);
            Buttonlist[x].setY((x/10)*50);
            Buttonlist[x].setWidth(50);
            Buttonlist[x].setHeight(50);
            if((x/10 ==0)||(x/10 ==2)||(x/10 ==4)||(x/10 ==6)||(x/10 ==8)){
            if(x%2 != 0){Buttonlist[x].setFill(Color.GRAY);
            }
            else{
                Buttonlist[x].setFill(Color.BLACK);
            }}
            else{
                 if(x%2 != 0){Buttonlist[x].setFill(Color.BLACK);
            }
            else{
                Buttonlist[x].setFill(Color.GRAY);
            }}
            }
            Scene scene = new Scene(pane, 1000, 700);
        primaryStage.setTitle("Turret Town");
        primaryStage.setScene(scene);
        primaryStage.show();
}}
