import java.awt.Color;
import java.awt.Graphics;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/*
 * Name: Hengcheng Yu
 * Date: 2017/04/27
 * Desc: This class defines a Rectangle class for the paint program
 *       It also has ability to draw a dashed rectangle
 */

public class Rectangle extends FillableShape{
    private final static float[] dash = {10.0f};
    private final static BasicStroke dashed = new BasicStroke(1.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,  dash , 0.0f);
    
    //This constructor takes 4 integer parameter represents (x1,y1) and (x2,y2) coordinates
    //of the shape and one Color object. It takes one more boolean parameter represents filled
    public Rectangle (int x1,int y1,int x2,int y2, Color color, boolean filled){
        super(x1,y1,x2,y2,color,filled);
    }
    
    // This method takes one Graphics object and draws the rectangle by its coordinates.
    // it will draw a filled one if the filled is set to true
    @Override
    public void draw(Graphics g){
        g.setColor(getColor());
        if (getFilled()){
            g.fillRect(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        }
        else{
            g.drawRect(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        }
    }
   
}