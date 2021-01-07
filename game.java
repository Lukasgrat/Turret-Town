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
import java.util.ArrayList;
public class game extends Application{
    /*
    In order:
    Buttonlist is the list of all buttons
    Pane is just the usual pane
    whiteturn says if it is white's turn, and thereby if it is black's
    buttonPressed will be used later on to see what button has been pressed.
    */
    public  Rectangle[] Buttonlist = new Rectangle[144]; 
    public Pane pane = new Pane();
    public boolean whiteturn = true;
    public int buttonPressed = 144;
    public ArrayList<piece> pieceList = new ArrayList();
    public void start(Stage primaryStage){
        gridsetup();
        pieceList.add( new piece(33,true,"Pawn"));
        pane.getChildren().add((pieceList.get(0)).text);
        Scene scene = new Scene(pane, 1000, 800);
        primaryStage.setTitle("Turret Town");
        primaryStage.setScene(scene);
        primaryStage.show();
}
    //The function sets up the basic 12x12 grid, with a checkerboard pattern, along with the interaction starting for each rectangle.
    public void gridsetup(){
        for(int x = 0; x < 144;x++){
            Buttonlist[x] = new Rectangle();
            pane.getChildren().add(Buttonlist[x]);
            Buttonlist[x].setX((x%12)*50);
            Buttonlist[x].setY((x/12)*50);
            Buttonlist[x].setWidth(50);
            Buttonlist[x].setHeight(50);
            if((x/12 ==0)||(x/12 ==2)||(x/12 ==4)||(x/12 ==6)||(x/12 ==8)||(x/12 == 10)){
            if(x%2 != 0){Buttonlist[x].setFill(Color.GRAY);
            }
            else{
                Buttonlist[x].setFill(Color.BROWN);
            }}
            else{
                 if(x%2 != 0){Buttonlist[x].setFill(Color.BROWN);
            }
            else{
                Buttonlist[x].setFill(Color.GRAY);
            }}
            }
            pane.setOnMouseClicked(e ->{
                if((e.getX()<600)&&(e.getY()<600)){
                    buttonPressed = (int)e.getX()/50+(int)e.getY()/50*12;
                    System.out.println(buttonPressed);
                    }
                    });
    }
    }
