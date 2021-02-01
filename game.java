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
    public ArrayList<Piece> pieceList = new ArrayList();
    public int selectedPiece = -1;
    public void start(Stage primaryStage){
        gridsetup();
        pieceSetup();
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
    public int pieceFind(int locate){
        for(int x = 0; x < pieceList.size();x++){
            if(pieceList.get(x).location == locate){
                return x;
            }
        }
        return -1;
    }
    public void pieceOptions(Piece piece){
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
        }
    public void pieceMovement(){      
    int whitePieces = 0;
    int blackPieces = 0;
        for(int x = 0; x < pieceList.size(); x++){
            if(pieceList.get(x).location == buttonPressed){
                if(x < selectedPiece){
                    selectedPiece--;
                }
                pane.getChildren().remove(pieceList.get(x).text);
                pieceList.remove(x);

                System.out.println(pieceList.size());
            }
            if(pieceList.get(x).isWhite){
                whitePieces++;
            }
            else{
                blackPieces++;
            }
        }
        pieceList.get(selectedPiece).setLocation(buttonPressed);
        whiteturn = !whiteturn;
        colorReset();
        if(whitePieces == 0){
            System.out.println("Black wins!");
        }
        if(blackPieces == 0){
            System.out.println("White wins!");
        }
    }
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
        }
    public void pieceAdd(int location, boolean isWhite,  String type){
        pieceList.add( new Piece(location, isWhite, type));
        pane.getChildren().add((pieceList.get(pieceList.size()-1)).text);
    }
    public void pieceSetup(){
        pieceAdd(1,false,"Pawn");
        pieceAdd(2,false,"Rook");
        pieceAdd(3, false, "Knight");
        pieceAdd(4, false, "Bishop");
        pieceAdd(5, false,"Queen");
        pieceAdd(6, false, "Queen");
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
        pieceAdd(138, true,"Queen");
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
