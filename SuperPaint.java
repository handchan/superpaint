/*
 * Name: Hengcheng Yu
 * Date: 2017/04/27
 * Desc: This is the test class provided by Mr. Rao to determine the functionality of the SUPERPAINT program.
 */

import javax.swing.JFrame;
class SuperPaint {
    public static void main(String[] args) {
        DrawFrame application = new DrawFrame();
        application.setSize( 600, 400 );
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.setVisible( true ); 
    }
}