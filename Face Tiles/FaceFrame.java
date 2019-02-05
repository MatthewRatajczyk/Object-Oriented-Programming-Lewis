import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class FacePanel extends JPanel {
	private ArrayList <Face> faces ;
	/**
	 * Imports the array list of faces into the class.
	 * @param faces
	 */
	public FacePanel(ArrayList <Face> faces) {
		this.faces= faces;
	}
	/**
	 * Paint Compenet Paints the faces by calling the Face class 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
		for (Face f : faces ) {
			// My color thing VV
			g.setColor(f.getColor());
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

/**
 * Face Frame creates a Jframe with an implemented actionListner for a timer, and
 * that adds buttons for randomizing and saving.
 * @author motorola
 * 
 */
public class FaceFrame extends JFrame implements ActionListener {
	private Timer tim;
	private ArrayList<Face> faces;
	private Random rnd = new Random();
	
	public FaceFrame( ArrayList<Face> faces) {
		this.faces= faces;
		setTitle("Random Faces");
		tim = new Timer(1000, this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds ( 100, 100, 520, 600) ;
		Container c = getContentPane( ) ;
		c.setLayout( new BorderLayout());
		FacePanel fp = new FacePanel(faces);
		c.add(fp,BorderLayout.CENTER); 
        setResizable(false);
        JMenuBar mbar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenuItem miExit = new JMenuItem("Exit");
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuFile.add(miExit);
        JMenu mnuEdit = new JMenu("Edit");
        JMenuItem miClear = new JMenuItem("Repaint");
        miClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	for (Face f : faces ) {
            		f.randColor();
        			int st = rnd.nextInt(3);
        			f.setSmileType(st);
            	}
            	repaint();
            }
        });
        mnuEdit.add(miClear);
        mbar.add(mnuFile);
        mbar.add(mnuEdit);
        setJMenuBar(mbar);
        
        JButton btnScramble = new JButton("REPAINT");
        btnScramble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("REPAINT NOW.");
            	for (Face f : faces ) {
            		f.randColor();
        			int st = rnd.nextInt(3);
        			f.setSmileType(st);
            	}
                repaint();
            }
        });
        JMenuItem miStartTimer = new JMenuItem("Start timer");
        miStartTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tim.start();
            }
        });
        mnuFile.add(miStartTimer);
        JMenuItem miStopTimer = new JMenuItem("Stop timer");
        miStopTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tim.stop();
                System.out.println("STOP.");
            }
        });
        mnuFile.add(miStopTimer);
        // NEW SHIT
        
        JPanel pnlButton = new JPanel();
        pnlButton.add(btnScramble);
        c.add(pnlButton,BorderLayout.SOUTH); 
        //Save Function
        JMenuItem miSave = new JMenuItem("Save");
        miSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser jfc = new JFileChooser();
                    if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        FileWriter fw = new FileWriter(jfc.getSelectedFile());
                        for (Face f : faces ) {
                            fw.write(f.toString() + "\r\n");
                        }
                        fw.close();                     
                    }
                } catch (Exception ex) {
                     
                }
            }
        });
        mnuFile.add(miSave);
        
	}
	/**
	 * For the action listner and all the buttons we have them call the class to randomize the number and 
	 * in this class they randomize the faces, Though I would do it both ways to see how they worked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
    	for (Face f : faces ) {
    		f.randColor();
			int st = rnd.nextInt(3);
			f.setSmileType(st);
    	}
    	repaint();
	}
	
}