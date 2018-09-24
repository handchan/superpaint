import java.awt.Color;
import java.awt.Graphics;

/*
 * Name: Michael Lin
 * Date: 2017/04/27
 * Desc: This class defines a Oval class for the paint program
 * 
 */

public class Oval extends FillableShape{
    //This constructor takes 4 integer parameter represents (x1,y1) and (x2,y2) coordinates
    //of the shape and one Color object. It takes one more boolean parameter represents filled
    public Oval (int x1,int y1,int x2,int y2, Color color, boolean filled){
        super(x1,y1,x2,y2,color,filled);
    }
    
    // This method takes one Graphics object and draws the oval by its coordinates.
    // it will draw a filled one if the filled is set to true
    @Override
    public void draw(Graphics g){
        g.setColor(getColor());
        if (getFilled()){
            g.fillOval(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        }
        else{
            g.drawOval(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        }
    }
}