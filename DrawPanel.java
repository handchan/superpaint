import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/*
 * Name: Hengcheng Yu
 * Date: 2017/04/28
 * Desc: This class defines the drawing panel that is used to draw lines, rectangles, and ovals. The mouse location
 * is also tracked on the panel.
 */

// Defines the DrawPanel Class.
public class DrawPanel extends JPanel{
    private Color color = Color.BLACK;
    private int shapeChoice;
    private Shape currentShape = null;
    private Rectangle preRect = null;
    private int selectedShapeIndex = -1;
    private DynamicStack<Shape> shapes;
    private DynamicStack<Shape> undoShapes;
    private DynamicStack<Shape> drawAction;
    private JLabel statusBar;
    private boolean filled = false;
    
    //This constructer method accepts the 3 parameters: the shapes, the stack, the undoDrawing stack and the statusBar
    //JLabel. The mouse event listener is set up and added to the panel.
    public DrawPanel (DynamicStack<Shape> shapes, DynamicStack<Shape> undoShapes, JLabel statusBar){
        
        this.shapes= shapes;
        this.undoShapes = undoShapes;
        this.statusBar = statusBar;
        drawAction = new DynamicStack<Shape>();
        
        // Creates listener for mouse and mouse motion events.
        MouseEventListener drawPanelListener = new MouseEventListener(); 
        addMouseListener( drawPanelListener ); 
        addMouseMotionListener( drawPanelListener );
    }
    
    // This mutator method accepts an integer as a parameter and assigns it to the shapeChoice instance variable.
    public void setChoice (int shapeChoice){
        this.shapeChoice = shapeChoice;
    }
    
    // This mutator method accepts a boolean parameter to determine if the the shape is to be filled.
    public void setFilled (boolean filled){
        this.filled = filled;
    }
    
    
    // This mutator method accepts a Color as a parameter to determine the color to be used.
    public void setColor (Color color){
        this.color = color;
    }
    
    
    // This mutator takes one integer parameter and assign it to selectedShapeIndex instance variable.
    public void setSelectedShapeIndex (int index){
        selectedShapeIndex = index;
        if (index == -1){
            preRect = null;
        }
    }
    
    // This accessor method returns a the color.
    public Color getColor(){
        return color;
    }
    
    // This accessor method returns an integer value for the selected shape index.
    public int getSelectedShapeIndex(){
        return selectedShapeIndex;
    }
    
    
    // This method will be called automatically when the window is drawn or redrawn.
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        
        drawAll(g);
        // If a shape is being drawn have it drawn on the top.
        if ( currentShape != null )
            currentShape.draw( g );
        
        
    }
    
    // This helper method draws all the shapes in the stack.
    private void drawAll(Graphics g){
        Shape figure;
        int size = shapes.size();
        for (int i = 0 ; i < size ; i++ ){
            drawAction.push(shapes.pop());
        }
        // goes through the stack and pops it to the helper stack and draws the shape while pushing them back.
        for ( int i = 0 ; i < size ; i++ ){
            figure = drawAction.pop();

            figure.draw(g);
            shapes.push(figure);
        }
    }
    
    
    // Inner class mouse event handling. 
    class MouseEventListener extends MouseAdapter {
        // This method is called automatically whenever the mouse is pressed.
        @Override
        public void mousePressed( MouseEvent event ) {
            // un-select the shape.
            setSelectedShapeIndex(-1);
            // if user right clicks the mouse, draw a pre-rectangle that wont be drawn shapes.
            if (event.isMetaDown()){
                // make it if no shapes are being drawn
                if (currentShape == null){
                    preRect = new Rectangle( event.getX(), event.getY(), event.getX(), event.getY(), color,false );
                }
            }
            // Draw the shape that was chosen.
            else{
                if (preRect == null){
                    if (shapeChoice == 0){
                        
                        currentShape = new Line( event.getX(), event.getY(), event.getX(), event.getY(), color);
                    }
                    else if (shapeChoice == 1){
                        currentShape = new Oval( event.getX(), event.getY(), event.getX(), event.getY(), color,filled );
                    }
                    else {
                        currentShape = new Rectangle( event.getX(), event.getY(), event.getX(), event.getY(), color,filled );
                    }
                }
                //Calls paintComponent( g )
                repaint();
            }
        }
        
        // This method is called automatically whenever the mosue is released.
        @Override
        public void mouseReleased( MouseEvent event ) {
            // When the mouse is released it searches the shapes stack for the shape that user has selected.
            if (event.isMetaDown()){
                
              if (preRect != null){
                    Shape figure;
                    int size = shapes.size();
                    
                    for (int i = 0 ; i < size ; i++){
                        figure = shapes.pop();
                        drawAction.push(figure);
                      
                        if (figure.isSelected(preRect.getUpperLeftX(), preRect.getUpperLeftY(), preRect.getLowerRightX(), preRect.getLowerRightX())){
                            setSelectedShapeIndex(size-i-1);
                            break;
                        }
                        // selecting the shape by just right-clicking
                        else if (figure.isSelected(  (preRect.getLowerRightX() + preRect.getUpperLeftX())/2, (preRect.getLowerRightY()+preRect.getUpperLeftY())/2 )){
                            setSelectedShapeIndex(size-i-1);
                            break;
                        }
                    }
                    size = drawAction.size();
                    // pushes  the shapes back to shapes stack
                    for (int i = 0 ; i < size ; i++){
                        shapes.push(drawAction.pop());
                    }
                    preRect = null;
                }
            }
            // When the user releases the mouse by left-clicking, draw the shape and push it to the stack.
            else{
                //Do nothing if there is no current shape being drawn
                if (currentShape != null){
                    // Updates the final coordinates
                    currentShape.setX2( getFinalX(event) );
                    currentShape.setY2( getFinalY(event) );
                    shapes.push(currentShape);
                    //clears the undo stack.
                    undoShapes.clear();
                    //prepares for the next shape
                    preRect = null;
                    currentShape = null;   

                }
            }

            repaint();   
        } 
        
        // This method is  called automatically whenever the mouse is dragged.
        @Override
        public void mouseDragged( MouseEvent event ) {
            // While the right button is pushed as mouse is dragged, update ending coordinates of preRect
           
          if (preRect != null){
                preRect.setX2( getFinalX(event));
                preRect.setY2( getFinalY(event));
            }
            // Updates the final coordinates of currentShape.
            else if (currentShape != null){
                currentShape.setX2( getFinalX(event));
                currentShape.setY2( getFinalY(event));
            }
            // Updates the statusBar 
            statusBar.setText( String.format( "(%d, %d)", getFinalX(event), getFinalY(event) ) );
            repaint();
        } 
        
        // This method is called automatically whenever a mosue is moved.
        @Override
        public void mouseMoved( MouseEvent event ) {
            //update the statusBar
            statusBar.setText( String.format( "(%d, %d)", getFinalX(event), getFinalY(event) ) );
        }
        
        //This method returns the X coordinate.
        private int getFinalX(MouseEvent event){
            if (event.getX() <= 0){
                return 0;
            }
            else if (event.getX() >= getWidth()){
                return getWidth() - 1;
            }
            return event.getX();
        }
        
        //This method returns the Y coordinate.
        private int getFinalY(MouseEvent event){
            if (event.getY() <= 0){
                return 0;
            }
            else if (event.getY() >= getHeight()){
                return getHeight() - 1;
            }
            return event.getY();
        }
        
    }
    
}