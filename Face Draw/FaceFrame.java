import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

class FacePanel extends JPanel {
	private ArrayList <Face> faces ;
		public FacePanel(ArrayList <Face> faces) {
		this.faces= faces;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Face f : faces ) {
			g.drawOval(f.getX( ),f.getY( ),2*f.getRadius( ),2*f.getRadius( )); 
			g.drawOval(f.getLeftEyeX(), f.getEyeY(), f.getEyeDiameter(), f.getEyeDiameter());
			g.drawOval(f.getRightEyeX(), f.getEyeY(), f.getEyeDiameter(), f.getEyeDiameter()); 
		
			if ( f.getSmileType( ) == 0) {
				g.drawRect(f.getMouthX(),f.getMouthY(),f.getMouthWidth(),f.getMouthHeight());
			} else {
				g.drawArc( f.getMouthX(), f.getMouthY(), f.getMouthWidth(), f.getMouthHeight() , 180 , f.getMouthAngle()) ;
			}
		} 
	}		
} 


public class FaceFrame extends JFrame {
	public FaceFrame( ArrayList<Face> faces) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds ( 100, 100 ,500,500) ;
		Container c = getContentPane( ) ;
		c.setLayout( new BorderLayout());
		FacePanel fp = new FacePanel(faces);
		c.add(fp,BorderLayout.CENTER); 
	}
}