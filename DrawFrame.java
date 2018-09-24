import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JColorChooser;


/*
 * Name: Hengcheng Yu
 * Date: 2017/04/28
 * Desc: This class creates a simple paint program for users to draw different shapes such as 
 *       oval, rectangle and line. Users can also undo, redo and clear drawings using the buttons
 *       provided.
 */

//This class creates a main frame for the program.
public class DrawFrame extends JFrame {
    
    private DynamicStack<Shape> drawings = new DynamicStack<>();
    private DynamicStack<Shape> undoDrawings = new DynamicStack<>();
    private String[] shapeList = { "Line", "Oval", "Rectangle" };
    private JComboBox<String> shapeChooser;
    private JLabel statusBar; 
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    private JButton colorButton;
    private JCheckBox fillBox;
    private DrawPanel drawPanel;
    
    
    // This constructor takes no parameters. It sets up all the widgets and add them to the 
    // frame. 
    public DrawFrame( ) {
        setBackground( Color.WHITE ); 
        
        //Creates the mouse label.
        JLabel statusLabel = new JLabel();
        getContentPane().setBackground( Color.WHITE);
        add(statusLabel,BorderLayout.SOUTH );
        
        //Create a tool panel and each widget.
        shapeChooser = new JComboBox<String>(shapeList);
        shapeChooser.addItemListener(new ShapeChooserListener() );
        ButtonListener buttonListener = new ButtonListener();
       
        undoButton = new JButton ("Undo");
        undoButton.addActionListener( buttonListener );
        
        redoButton = new JButton ("Redo");
        redoButton.addActionListener( buttonListener );
        
        clearButton = new JButton ("Clear All");
        clearButton.addActionListener( buttonListener );
        
        fillBox = new JCheckBox ("filled");
        fillBox.addItemListener(new FilledCheckBoxListener() );
        
        colorButton = new JButton ("Colour");
        colorButton.addActionListener( buttonListener );

        
        //Creates the panel for all the buttons and widgets.
        JPanel toolPanel = new JPanel();
        toolPanel.add(undoButton);
        toolPanel.add(redoButton);
        toolPanel.add(clearButton);
        
        toolPanel.add(shapeChooser);
        toolPanel.add(fillBox);       
        toolPanel.add(colorButton);
        toolPanel.setBackground( Color.GRAY ); 
        
        add(toolPanel, BorderLayout.NORTH);
        
        // Add draw panel to the frame
        drawPanel = new DrawPanel(drawings,undoDrawings,statusLabel);
        add(drawPanel, BorderLayout.CENTER);
        
    }
    
    //Event listener for filled check box
    class FilledCheckBoxListener implements ItemListener{
        public void itemStateChanged( ItemEvent e ) {
            drawPanel.setFilled(fillBox.isSelected());
        }
    }
    
    //Event listener for shape chooser combo box
    class ShapeChooserListener implements ItemListener{
        // This method will be called automatically whenever a JComboBox event occurs.
        @Override
        public void itemStateChanged( ItemEvent e ) {
            drawPanel.setChoice(shapeChooser.getSelectedIndex());
        }         
    }
    
    //The Event listener for all the buttons.
    class ButtonListener implements ActionListener{ 
        // This method will be called automatically whenever a button event occurs.
        @Override 
        public void actionPerformed( ActionEvent e ){
            int size;
            
             // Undo shapes
            if (e.getSource() == undoButton && drawings.size() != 0){
                undoDrawings.push(drawings.pop());
                drawPanel.setSelectedShapeIndex(-1);
            }
            //Redo the undoshapes
            else if (e.getSource() == redoButton && undoDrawings.size() != 0){
                drawings.push(undoDrawings.pop());
                drawPanel.setSelectedShapeIndex(-1);
            }
           
            //Clears all drawings
            else if (e.getSource() == clearButton){
                undoDrawings.clear();
                drawings.clear();
                drawPanel.setSelectedShapeIndex(-1);
            }
            
            //Calls the JColorChooser for the user to select a color.
            else if ( e.getSource() == colorButton ){
                drawPanel.setColor(JColorChooser.showDialog( null, "Select a colour", drawPanel.getColor() ));
            }


            drawPanel.repaint();
        }

    }
    
} 