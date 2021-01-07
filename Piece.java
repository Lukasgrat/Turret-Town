import javafx.scene.text.Text;
import javafx.scene.paint.Color;
public class Piece{ 
    public int location;
    public boolean isWhite;
    public Text text;
    public int xLocation;
    public int yLocation;
    public  Piece(int location, boolean isWhite, String text){
        this.location = location;
        this.text = new Text(text);
        this.xLocation = this.location%12*50+10;
        this.yLocation = this.location/12*50+25;
        this.text.setX(this.xLocation);
        this.text.setY(this.yLocation);
        this.isWhite = isWhite;
        if(this.isWhite == true){
            this.text.setStroke(Color.WHITE);
        }
        else{
            this.text.setStroke(Color.BLACK);
        }
    }
}
