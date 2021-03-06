import java.awt.Color;
import java.awt.Graphics;

/*
 * Name:Hengcheng Yu
 * Date: 2017/04/27
 * Desc: This abstract class defines a Shape class for the paint program
 * 
 */

public abstract class Shape{
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;
    
    //This constructor takes 4 integer parameter represents (x1,y1) and (x2,y2) coordinates
    //of the shape and one Color object 
    public Shape(int x1,int y1, int x2, int y2, Color color){
        setX1(x1);
        setX2(x2);
        setY1(y1);
        setY2(y2);
        setColor(color);
    }
    
    // This accessor returns an integer represents x1 instance variable
    public int getX1(){
        return x1;
    }
    
    // This accessor returns an integer represents y1 instance variable
    public int getY1(){
        return y1;
    }
    
    // This accessor returns an integer represents x2 instance variable
    public int getX2(){
        return x2;
    }
    
    // This accessor returns an integer represents y2 instance variable
    public int getY2(){
        return y2;
    }
    
    // This accessor returns a Color object represents color instance variable
    public Color getColor(){
        return color;
    }
    
    // This mutator takes one integer parameter and assign it to x1 instance variable
    public void setX1(int x1){
        this.x1 = x1;
    }
    
    // This mutator takes one integer parameter and assign it to y1 instance variable
    public void setY1(int y1){
        this.y1 = y1;
    }
    
    // This mutator takes one integer parameter and assign it to x2 instance variable
    public void setX2(int x2){
        this.x2 = x2;
    }
    
    // This mutator takes one integer parameter and assign it to y2 instance variable
    public void setY2(int y2){
        this.y2 = y2;
    }
    
    // This mutator takes one Color parameter and assign it to color instance variable
    public void setColor(Color color){
        this.color = color;
    }
    
    // Abstract method for drawing the shape
    public abstract void draw(Graphics g);
    
    // Abstract method for detecting if the shape is selected by the user
    // two integer parameters represents the centre coordiantes
    public abstract boolean isSelected(int x,int y);
    
    // Abstract method for detecting if the shape is selected by the user
    // four integer parameters represents a rectangular area 
    public abstract boolean isSelected(int x1, int y1, int x2, int y2);
}