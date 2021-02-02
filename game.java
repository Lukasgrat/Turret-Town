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
    public int whiteLost = 0;
    public int blackLost = 0;
    public  Rectangle[] Buttonlist = new Rectangle[144]; 
    public Pane pane = new Pane();
    public boolean whiteturn = true;
    public boolean canAddPiece = true;
    public int buttonPressed = 144;
    public int blackCurrentMana = 3;
    public int whiteCurrentMana = 0;
    public Text whiteCurrentManaText = new Text("White Current Mana:"+ whiteCurrentMana);
    public Text blackCurrentManaText = new Text("Black Current Mana:" + blackCurrentMana);
    public ArrayList<Piece> pieceList = new ArrayList();
    public String addingString = "Current Adding Piece: None";
    public Text addingText = new Text(addingString);
    public int selectedPiece = -1;
    public void start(Stage primaryStage){
        gridsetup();
        pieceSetup();
        buttonSetup();
        Scene scene = new Scene(pane, 1000, 800);
        primaryStage.setTitle("Giga-Chess");
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
                    colorOptions();
                    }
                    });
    }
    //It is a subfunction that sets up the buttons to make new pieces
    public void buttonSetup(){
        whiteCurrentManaText.setX(650);
        whiteCurrentManaText.setY(625);
        blackCurrentManaText.setX(650);
        blackCurrentManaText.setY(25);
        pane.getChildren().add(whiteCurrentManaText);
        pane.getChildren().add(blackCurrentManaText);
        addingText.setX(650);
        addingText.setY(200);
        pane.getChildren().add(addingText);
        Button pawnAdd = new Button("Add Pawn");
        pawnAdd.setLayoutX(650);
        pawnAdd.setLayoutY(50);
        pane.getChildren().add(pawnAdd);
        pawnAdd.setOnAction(e ->{
            colorReset();
            addingPiecePlacements("Pawn");
        });
        Button rookAdd = new Button("Add Rook");
        rookAdd.setLayoutX(650);
        rookAdd.setLayoutY(75);
        pane.getChildren().add(rookAdd);
        rookAdd.setOnAction(e ->{
            colorReset();
            addingPiecePlacements("Rook");
        });
        Button queenAdd = new Button("Add Queen");
        queenAdd.setLayoutX(650);
        queenAdd.setLayoutY(100);
        pane.getChildren().add(queenAdd);
        queenAdd.setOnAction(e ->{
            colorReset();
            addingPiecePlacements("Queen");
        });
        Button bishopAdd = new Button("Add Bishop");
        bishopAdd.setLayoutX(650);
        bishopAdd.setLayoutY(125);
        pane.getChildren().add(bishopAdd);
        bishopAdd.setOnAction(e ->{
            colorReset();
            addingPiecePlacements("Bishop");
        });
        Button knightAdd = new Button("Add Knight");
        knightAdd.setLayoutX(650);
        knightAdd.setLayoutY(150);
        pane.getChildren().add(knightAdd);
        knightAdd.setOnAction(e ->{
            colorReset();
            addingPiecePlacements("Knight");
        });
    }
    public void addingPiecePlacements(String selected){
        if(canAddPiece == true){
        boolean isPiece = false;
        addingString = selected;
        addingText.setText("Current Adding Piece: " +addingString);
        if(whiteturn == true &&((addingString.equals("Pawn")&&whiteCurrentMana >= 1)
        ||((addingString.equals("Bishop")||addingString.equals("Knight"))&&whiteCurrentMana >= 3)
        ||(addingString.equals("Rook")&&whiteCurrentMana >= 7)||(addingString.equals("Queen")&&whiteCurrentMana >= 11))){
            for(int x = 120; x < 144; x++){
                if(pieceFind(x) != -1) isPiece = true;
                if(isPiece == false){
                    Buttonlist[x].setFill(Color.BLUE);
                }
                isPiece = false;
            }
        }
        else if(whiteturn == false &&((addingString.equals("Pawn")&&blackCurrentMana >= 1)
        ||((addingString.equals("Bishop")||addingString.equals("Knight"))&&blackCurrentMana >= 3)
        ||(addingString.equals("Rook")&&blackCurrentMana >= 7)||(addingString.equals("Queen")&&blackCurrentMana >= 11))){
            for(int x = 0; x < 24; x++){
                if(pieceFind(x) != -1) isPiece = true;
                if(isPiece == false){
                    Buttonlist[x].setFill(Color.BLUE);
                }
                isPiece = false;
            }
        }}
    }
    //It finds a piece, what did you think it did? Only thing to note is that it returns a negative one of there is none.
    public int pieceFind(int locate){
        for(int x = 0; x < pieceList.size();x++){
            if(pieceList.get(x).location == locate){
                return x;
            }
        }
        return -1;
    }

    //Checks for all the possible options of movement for every piece. If we have time we could do something with the king or double moving pawn
    public void pieceOptions(Piece piece){
        //Deals with all the movements for a pawn. If we have time, I can deal with getting the two movements to work, but I won't want to deal with the whole transformation thing.
         if(piece.text.getText().equals("Pawn")){
            if(piece.isWhite == true){
                if(piece.location >= 12&& pieceFind(buttonPressed-12) == -1){
                    Buttonlist[buttonPressed-12].setFill(Color.GREEN);
                }
                if(piece.location >= 13 && pieceFind(buttonPressed-13) != -1&&pieceList.get(pieceFind(buttonPressed-13)).isWhite != piece.isWhite){
                    Buttonlist[buttonPressed-13].setFill(Color.GREEN);
                }
                if(piece.location >= 11 && pieceFind(buttonPressed-11) != -1&&pieceList.get(pieceFind(buttonPressed-11)).isWhite != piece.isWhite){
                    Buttonlist[buttonPressed-11].setFill(Color.GREEN);
                }
            }
            if(piece.isWhite == false){
                if(piece.location <= 131&& pieceFind(buttonPressed+12) == -1){
                    Buttonlist[buttonPressed+12].setFill(Color.GREEN);
                }
                if(piece.location <= 132 &&pieceFind(buttonPressed+11) != -1&& pieceList.get(pieceFind(buttonPressed+11)).isWhite != piece.isWhite){
                    Buttonlist[buttonPressed+11].setFill(Color.GREEN);
                }
                if(piece.location <= 130 && pieceFind(buttonPressed+13) != -1&&pieceList.get(pieceFind(buttonPressed+13)).isWhite != piece.isWhite){
                    Buttonlist[buttonPressed+13].setFill(Color.GREEN);
                }
            }
        }
        else if(piece.text.getText().equals("Bishop")){
            for(int x = 1;x < 12; x++){
                if(piece.location + x*13< 144&&(piece.location + x*13)%12 != 0){
                    if(pieceFind(buttonPressed+ x*13) == -1){
                        Buttonlist[buttonPressed+13*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed+ x*13)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed+13*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12;x++){
                if(piece.location + x*11< 144&&(piece.location + x*11)%12 != 11){
                    if(pieceFind(buttonPressed+ x*11) == -1){
                        Buttonlist[buttonPressed+11*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed+ x*11)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed+11*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12;x++){
                if(piece.location - x*13>= 0&&(piece.location - x*13)%12 != 11){
                    if(pieceFind(buttonPressed- x*13) == -1){
                        Buttonlist[buttonPressed-13*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed- x*13)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed-13*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12; x++){
                if(piece.location - x*11>= 0 &&(piece.location - x*11)%12 != 0){
                    if(pieceFind(buttonPressed- x*11) == -1){
                        Buttonlist[buttonPressed-11*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed- x*11)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed-11*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
                }
            }
            else if(piece.text.getText().equals("Rook")){
            for(int x = 1;x < 12; x++){
                if((piece.location + x)%12 != 0){
                    if(pieceFind(buttonPressed+ x) == -1){
                        Buttonlist[buttonPressed+x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed+ x)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed+x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12;x++){
                if((piece.location - x) %12 != 11){
                    if(pieceFind(buttonPressed- x) == -1){
                        Buttonlist[buttonPressed-x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed-x)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed-x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12;x++){
                if(piece.location - x*12>= 0){
                    if(pieceFind(buttonPressed- x*12) == -1){
                        Buttonlist[buttonPressed-12*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed- x*12)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed-12*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12; x++){
                if(piece.location + x*12< 144){
                    if(pieceFind(buttonPressed+ x*12) == -1){
                        Buttonlist[buttonPressed+12*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed+ x*12)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed+12*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
                }
            }
            else if(piece.text.getText().equals("Queen")){
                for(int x = 1;x < 12; x++){
                if(piece.location + x*13< 144&&(piece.location + x*13)%12 != 0){
                    if(pieceFind(buttonPressed+ x*13) == -1){
                        Buttonlist[buttonPressed+13*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed+ x*13)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed+13*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12;x++){
                if(piece.location + x*11< 144&&(piece.location + x*11)%12 != 11){
                    if(pieceFind(buttonPressed+ x*11) == -1){
                        Buttonlist[buttonPressed+11*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed+ x*11)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed+11*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12;x++){
                if(piece.location - x*13>= 0&&(piece.location - x*13)%12 != 11){
                    if(pieceFind(buttonPressed- x*13) == -1){
                        Buttonlist[buttonPressed-13*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed- x*13)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed-13*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12; x++){
                if(piece.location - x*11>= 0 &&(piece.location - x*11)%12 != 0){
                    if(pieceFind(buttonPressed- x*11) == -1){
                        Buttonlist[buttonPressed-11*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed- x*11)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed-11*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
                }
                for(int x = 1;x < 12; x++){
                if((piece.location + x)%12 != 0){
                    if(pieceFind(buttonPressed+ x) == -1){
                        Buttonlist[buttonPressed+x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed+ x)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed+x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12;x++){
                if((piece.location - x) %12 != 11&&piece.location - x >=0){
                    if(pieceFind(buttonPressed- x) == -1){
                        Buttonlist[buttonPressed-x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed-x)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed-x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12;x++){
                if(piece.location - x*12>= 0){
                    if(pieceFind(buttonPressed- x*12) == -1){
                        Buttonlist[buttonPressed-12*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed- x*12)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed-12*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
            }
            for(int x = 1;x < 12; x++){
                if(piece.location + x*12< 144){
                    if(pieceFind(buttonPressed+ x*12) == -1){
                        Buttonlist[buttonPressed+12*x].setFill(Color.GREEN);
                    }
                    else if(pieceList.get(pieceFind(buttonPressed+ x*12)).isWhite == piece.isWhite){
                        break;
                    }
                    else{
                        Buttonlist[buttonPressed+12*x].setFill(Color.GREEN);
                        break;
                    }}
                else{
                    break;}
                }
            }
            else if(piece.text.getText().equals("Knight")){
                if(piece.location -23 >= 0 &&(piece.location -23) % 12 !=0 &&(pieceFind(piece.location-23) == -1 || pieceList.get(pieceFind(piece.location-23)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed-23].setFill(Color.GREEN);
                }
                if(piece.location -25 >= 0 &&(piece.location -25) % 12 !=11 &&(pieceFind(piece.location-25) == -1 || pieceList.get(pieceFind(piece.location-25)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed-25].setFill(Color.GREEN);
                }
                if(piece.location -14 >= 0 &&(piece.location -14) % 12 <=9 &&(pieceFind(piece.location-14) == -1 || pieceList.get(pieceFind(piece.location-14)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed-14].setFill(Color.GREEN);
                }
                if(piece.location -10 >= 0 &&(piece.location -10) % 12 >=2 &&(pieceFind(piece.location-10) == -1 || pieceList.get(pieceFind(piece.location-10)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed-10].setFill(Color.GREEN);
                }
                if(piece.location +10 <= 143 &&(piece.location +10) % 12 <=9 &&(pieceFind(piece.location+10) == -1 || pieceList.get(pieceFind(piece.location+10)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed+10].setFill(Color.GREEN);
                }
                if(piece.location +14 <=143 &&(piece.location +14) % 12 >=2 &&(pieceFind(piece.location+14) == -1 || pieceList.get(pieceFind(piece.location+14)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed+14].setFill(Color.GREEN);
                }
                if(piece.location +25 <= 143 &&(piece.location +25) % 12 !=0 &&(pieceFind(piece.location+25) == -1 || pieceList.get(pieceFind(piece.location+25)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed+25].setFill(Color.GREEN);
                }
                if(piece.location +23 <= 143 &&(piece.location +23) % 12 != 11 &&(pieceFind(piece.location+23) == -1 || pieceList.get(pieceFind(piece.location+23)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed+23].setFill(Color.GREEN);
                }
            }
            else if(piece.text.getText().equals("Earl")){
                if(piece.location -13 >= 0 &&(piece.location -13) % 12 !=0 &&(pieceFind(piece.location-13) == -1 || pieceList.get(pieceFind(piece.location-13)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed-13].setFill(Color.GREEN);
                    if(piece.location -26 >= 0 &&(piece.location -26) % 12 !=0 &&(pieceFind(piece.location-26) == -1 || pieceList.get(pieceFind(piece.location-26)).isWhite != piece.isWhite)){
                        Buttonlist[buttonPressed-26].setFill(Color.GREEN);
                    }
                }
                if(piece.location -12 >= 0 &&(piece.location -12) % 12 !=11 &&(pieceFind(piece.location-12) == -1 || pieceList.get(pieceFind(piece.location-12)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed-12].setFill(Color.GREEN);
                    if(piece.location -24 >= 0 &&(piece.location -24) % 12 !=11 &&(pieceFind(piece.location-24) == -1 || pieceList.get(pieceFind(piece.location-24)).isWhite != piece.isWhite)){
                        Buttonlist[buttonPressed-24].setFill(Color.GREEN);
                    }
                }
                if(piece.location -11 >= 0 &&(piece.location -11) % 12 <=9 &&(pieceFind(piece.location-11) == -1 || pieceList.get(pieceFind(piece.location-11)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed-11].setFill(Color.GREEN);
                    if(piece.location -22 >= 0 &&(piece.location -22) % 12 <=9 &&(pieceFind(piece.location-22) == -1 || pieceList.get(pieceFind(piece.location-22)).isWhite != piece.isWhite)){
                        Buttonlist[buttonPressed-22].setFill(Color.GREEN);
                    }
                }
                if(piece.location -1 >= 0 &&(piece.location -1) % 12 >=2 &&(pieceFind(piece.location-1) == -1 || pieceList.get(pieceFind(piece.location-1)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed-1].setFill(Color.GREEN);
                    if(piece.location -2 >= 0 &&(piece.location -2) % 12 >=2 &&(pieceFind(piece.location-2) == -1 || pieceList.get(pieceFind(piece.location-1)).isWhite != piece.isWhite)){
                        Buttonlist[buttonPressed-2].setFill(Color.GREEN);
                    }
                }
                if(piece.location +1 <= 143 &&(piece.location +1) % 12 <=9 &&(pieceFind(piece.location+1) == -1 || pieceList.get(pieceFind(piece.location+1)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed+1].setFill(Color.GREEN);
                    if(piece.location +2 <= 143 &&(piece.location +2) % 12 <=9 &&(pieceFind(piece.location+2) == -1 || pieceList.get(pieceFind(piece.location+1)).isWhite != piece.isWhite)){
                        Buttonlist[buttonPressed+2].setFill(Color.GREEN);
                    }
                }
                if(piece.location +11 <=143 &&(piece.location +11) % 12 >=2 &&(pieceFind(piece.location+11) == -1 || pieceList.get(pieceFind(piece.location+11)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed+11].setFill(Color.GREEN);
                    if(piece.location +22 <=143 &&(piece.location +22) % 12 >=2 &&(pieceFind(piece.location+22) == -1 || pieceList.get(pieceFind(piece.location+22)).isWhite != piece.isWhite)){
                        Buttonlist[buttonPressed+22].setFill(Color.GREEN);
                    }
                }
                if(piece.location +12 <= 143 &&(piece.location +12) % 12 !=0 &&(pieceFind(piece.location+12) == -1 || pieceList.get(pieceFind(piece.location+12)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed+12].setFill(Color.GREEN);
                    if(piece.location +24 <= 143 &&(piece.location +24) % 12 !=0 &&(pieceFind(piece.location+24) == -1 || pieceList.get(pieceFind(piece.location+14)).isWhite != piece.isWhite)){
                        Buttonlist[buttonPressed+24].setFill(Color.GREEN);
                    }
                }
                if(piece.location +13 <= 143 &&(piece.location +13) % 12 != 11 &&(pieceFind(piece.location+13) == -1 || pieceList.get(pieceFind(piece.location+13)).isWhite != piece.isWhite)){
                    Buttonlist[buttonPressed+13].setFill(Color.GREEN);
                    if(piece.location +26 <= 143 &&(piece.location +26) % 12 != 11 &&(pieceFind(piece.location+26) == -1 || pieceList.get(pieceFind(piece.location+26)).isWhite != piece.isWhite)){
                        Buttonlist[buttonPressed+26].setFill(Color.GREEN);
                    }
                }
            }
        }
        //The function moves a piece, and removes the overlapping piece if on a different team.
    public void pieceMovement(){      
        for(int x = 0; x < pieceList.size(); x++){
            if(pieceList.get(x).location == buttonPressed){
                if(x < selectedPiece){
                    selectedPiece--;
                }
                 if(pieceList.get(x).isWhite){
                    whiteLost++;
                }
                else{
                    blackLost++;
                }
                pane.getChildren().remove(pieceList.get(x).text);
                pieceList.remove(x);
            }}
        pieceList.get(selectedPiece).setLocation(buttonPressed);
        //Every time the player moves a piece will increase their mana by 3
        if(whiteturn == true){
            whiteCurrentMana+=3;
        }
        else{
            blackCurrentMana+=3;
        }
        blackCurrentManaText.setText("Black Current Mana:"+ blackCurrentMana);
        whiteCurrentManaText.setText("White Current Mana:"+ whiteCurrentMana);
        whiteturn = !whiteturn;
        if(whiteLost >= 20){
            System.out.println("Black wins!");
        }
        if(blackLost >= 20){
            System.out.println("White wins!");
        }
        canAddPiece = true;
        colorReset();
    }
    //The function below adds a board piece and subtracts the amount of mana that it costs to make one.
    //I doubled the costs for rooks and queens because they were a bit too broken in testing.
    public void addBoardPiece(){
        pieceAdd(buttonPressed,whiteturn,addingString);
        if(addingString.equals("Pawn")){
            if(whiteturn== true){
                whiteCurrentMana--;
            }
            else{
                blackCurrentMana--;
            }}
        if(addingString.equals("Knight")){
            if(whiteturn== true){
                whiteCurrentMana-=3;
            }
            else{
                blackCurrentMana-=3;
                }}
        if(addingString.equals("Bishop")){
            if(whiteturn== true){
                whiteCurrentMana-=3;
            }
            else{
                blackCurrentMana-=3;
            }}
        if(addingString.equals("Rook")){
            if(whiteturn== true){
                whiteCurrentMana-=10;
            }
            else{
                blackCurrentMana-=10;
            }}
        if(addingString.equals("Queen")){
            if(whiteturn== true){
                whiteCurrentMana-= 11;
            }
            else{
                blackCurrentMana-= 11;
            }}
        colorReset();
        canAddPiece = false;
        blackCurrentManaText.setText("Black Current Mana:"+ blackCurrentMana);
        whiteCurrentManaText.setText("White Current Mana:"+ whiteCurrentMana);
    }
    //Resets all the colors of the tiles when called. To either gray or brown.
    public void colorReset(){
        for(int x = 0; x < 144;x++){
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
    }
    //The function under is called to determine what color and conditions are when you click a tile and to be sent to different functions to do certain actions.
    public void colorOptions(){
        if(Buttonlist[buttonPressed].getFill() == Color.BROWN ||Buttonlist[buttonPressed].getFill() == Color.GRAY ){
            if(pieceFind(buttonPressed) != -1&&pieceList.get(pieceFind(buttonPressed)).isWhite == whiteturn){
            selectedPiece = pieceFind(buttonPressed);
                    if(this.selectedPiece != -1){
                        colorReset();
                        pieceOptions(pieceList.get(selectedPiece));}
                    else{
                    colorReset();
                    }}}
        else if(Buttonlist[buttonPressed].getFill() == Color.GREEN){
            pieceMovement();
        }
        else if(Buttonlist[buttonPressed].getFill() == Color.BLUE){
            addBoardPiece();
        }
        addingString = "None";
        addingText.setText("Current Adding Piece: "+ addingString);
        }
        // A function that just helps to streamline the adding of pieces
    public void pieceAdd(int location, boolean isWhite,  String type){
        pieceList.add( new Piece(location, isWhite, type));
        pane.getChildren().add((pieceList.get(pieceList.size()-1)).text);
    }
    //The function sets up all the starting pieces, with a couple of for loops for pawns
    public void pieceSetup(){
        pieceAdd(1,false,"Pawn");
        pieceAdd(2,false,"Rook");
        pieceAdd(3, false, "Knight");
        pieceAdd(4, false, "Bishop");
        pieceAdd(5, false,"Queen");
        pieceAdd(6, false, "Earl");
        pieceAdd(7, false, "Bishop");
        pieceAdd(8, false, "Knight");
        pieceAdd(9, false, "Rook");
        pieceAdd(10, false, "Pawn");
        for(int x = 14; x < 22; x++){
            pieceAdd(x,false,"Pawn");;
        }
        pieceAdd(142,true,"Pawn");
        pieceAdd(141,true,"Rook");
        pieceAdd(140, true, "Knight");
        pieceAdd(139, true, "Bishop");
        pieceAdd(138, true,"Earl");
        pieceAdd(137, true, "Queen");
        pieceAdd(136, true, "Bishop");
        pieceAdd(135, true, "Knight");
        pieceAdd(134, true, "Rook");
        pieceAdd(133, true, "Pawn");
        for(int x = 129; x > 121; x--){
            pieceAdd(x,true,"Pawn");
        }
    }
    }
