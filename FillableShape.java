import java.awt.Color;
import java.awt.Graphics;

/*
 * Name: Hengcheng Yu
 * Date: 2017/04/27
 * Desc: This abstract class defines a FillableShape class for the paint program
 * 
 */

public abstract class FillableShape extends Shape{
    private boolean filled;
    
    //This constructor takes 4 integer parameter represents (x1,y1) and (x2,y2) coordinates
    //of the shape and one Color object. It takes one more boolean parameter represents filled
    public FillableShape(int x1,int y1,int x2,int y2, Color color, boolean filled){
        super(x1,y1,x2,y2,color);
        setFilled(filled);
    }
    
    // This accessor returns a boolean value represents filled instance variable
    public boolean getFilled (){
        return filled;
    }
    
    // This method returns upper left x coordinate
    public int getUpperLeftX(){
        return Math.min(getX1(),getX2());
    }
    
    // This method returns upper left y coordinate
    public int getUpperLeftY(){
        return Math.min(getY1(),getY2());
    }
    
    // This method returns lower right x coordinate
    public int getLowerRightX(){
        return Math.max(getX1(),getX2());
    }
    
    // This method returns lower right y coordinate
    public int getLowerRightY(){
        return Math.max(getY1(),getY2());
    }
    
    // This method returns a integer represents width of the shape
    public int getWidth(){
        return Math.abs(getX1()-getX2());
    }
    
    // This method returns a integer represents height of the shape
    public int getHeight(){
        return Math.abs(getY1()-getY2());
    }
    
    // This mutator takes one boolean parameter and assign it to filled instance variable
    public void setFilled(boolean filled){
        this.filled = filled;
    }
    
    // This method takes two integer parameters represents the centre coordiantes
    // and detects if the shape is selected by the user. return true of the shape
    // is selected and false otherwise
    @Override
    public boolean isSelected(int x,int y){
        if ((x >= getUpperLeftX() && x <= getLowerRightX()) && (y >= getUpperLeftY() && y <= getLowerRightY()) ){
            return true;
        }
        return false;
    }
    
    // This method takes four integer parameters represents a rectangular area 
    // and detects if the shape is selected by the user. return true of the shape
    // is selected and false otherwise
    @Override
    public boolean isSelected(int x1, int y1, int x2, int y2){
        if ( (x1<= getUpperLeftX() && x2>= getLowerRightX() ) && ( y1<= getUpperLeftY() && y2>= getLowerRightY() ) ){
            return true;
        }
        return false;
    }
}