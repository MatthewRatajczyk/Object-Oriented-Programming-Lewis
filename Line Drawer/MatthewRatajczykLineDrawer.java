import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
/**
 *  
 * @author motorola
 * Class Point is the object a point which has data relating to its 
 *  size, shape, location, color, and weather or not to draw a line to the next object in the array list
 *  This is ment to be in an array list
 */
class Point {
    private int x;
    private int y;
    private int dotSize;
	private Color col;
	private boolean line;
	private String colPrint;
	
	/**
	 * This is returns the current x location of the object
	 * @return
	 */
    public int getX() {
        return x;
    }
    /**
     * This returns the current Y location of the object
     * @return
     */
    public int getY() {
        return y;
    }
    /**
     * This can be called to set the Objects X cordinates
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * This can be called to set the Objects Y cordinates
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * This is the default constructor 
     */
    public Point() {
        x = 0;
        y = 0;
    }
    
    /**
     * The setSize function sets the Objects size to an int passed into it
     * @param dotSize
     */
    public void setSize(int dotSize) {
        this.dotSize = dotSize;
    }
    /**
     * This returns the Objects size
     * @return
     */
    public int getSize() {
        return dotSize;
    }
    /**
     * This is the Construtor that creates a point when passed in all the relavent infomation
     * @param x
     * @param y
     * @param col
     * @param size
     * @param line
     */
    public Point(int x, int y, Color col, int size, boolean line) {
        setX(x);
        setY(y);
        setColor(col);
        setSize(size);
        setConnectedState(line);
    }
    /**
     * This sets the Objects color
     * @param col
     */
    public void setColor(Color col) {
    	this.col = col;
    }
    
    /**
     * This returns a boolen true it will draw a line to the next point flase if it will not.
     * @return
     */
    public Boolean getConnectedState() {
    	return line;
    }
    /**
     * This changes the state of weather or not the Object will have an line
     * @param line
     */
    public void setConnectedState(boolean line) {
    	this.line = line;
    }
    /**
     * This is returns the color of an object    
     * @return
     */
    public Color getColor() {
    	return col;
    }
    /**
     * This is the toString function returns all relevant information about the object as a string, useful for saving.
     */
    @Override
    public String toString() {
    	if(col == Color.RED) {
    		colPrint = "Red";
    	} else if (col == Color.BLUE) {
    		colPrint = "Blue";
    	}else if (col == Color.GREEN) {
    		colPrint = "Green";
    	}else if (col == Color.BLACK) {
    		colPrint = "Black";
    	}
        return String.format("%d %d %s %d %s",x,y,colPrint,dotSize,line);
    }
}
/**
 * This is the LinePanel class it I don't really know what it does tbh this could be merged with the other class, but im pretty sure it has to do with 
 *  listing to user input on the jpannel
 * @author motorola
 *
 */
class LinePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private ArrayList<Point> points;
    private String message;
    private Color color;
    private boolean line = true;
    private int currentX;
    private int currentY;
    /**
     * This function changes the current state of weather or not the next line will have a line
     */
    public void setConnectedState() {
    	line = !line;
    }
    /**
     * Class points adds the action listeners and sets the default color to black
     * @param points
     */
    public LinePanel(ArrayList<Point> points) {    	
        this.points = points;
        addMouseListener(this);
        message = "Click the mouse where you want points to show up.";
        addMouseMotionListener(this);
        setFocusable(true);
        color = Color.BLACK;
        addKeyListener(this);
    }
    /**
     * This is the paint compenet and paints all the objects to the main screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point prevPoint = null;        
        for (Point p : points) {
        	g.setColor(p.getColor());
            g.fillOval(p.getX(), p.getY(), p.getSize(), p.getSize());            
            if (prevPoint != null) {
            	if (p.getConnectedState() == true) {
            		g.drawLine(prevPoint.getX()+prevPoint.getSize()/2, prevPoint.getY()+prevPoint.getSize()/2, p.getX()+p.getSize()/2, p.getY()+p.getSize()/2);            		
            	}
            }
            prevPoint = p;
        }        
        g.drawString(message, 300, 300);        
        if (prevPoint != null) {
        	if (line == true) {
        		g.drawLine(prevPoint.getX()+prevPoint.getSize()/2, prevPoint.getY()+prevPoint.getSize()/2, currentX, currentY);
        	}
        }
    }
    /**
     * This listens for when the mouse is pressed and gathers all the info on what the state of the object is and 
     * Creates an new point and adds it to the array list.
     */
    public void mousePressed(MouseEvent e) {
        Point p = new Point(e.getX()-LineFrame.Size/2,e.getY()-LineFrame.Size/2,color,LineFrame.Size,line);
        points.add(p);
        repaint();
    }
    /**
     * You left this in here.
     */
    public void keyTyped(KeyEvent e) {}
    /**
     * This class listens for when the keys are pressed and changes the state of what the next dot should be.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            color = color.RED;
        } else if (e.getKeyCode() == KeyEvent.VK_G) {
            color = color.GREEN;
        } else if (e.getKeyCode() == KeyEvent.VK_B) {
            color = color.BLUE;
        } else if (e.getKeyCode() == KeyEvent.VK_K) {
            color = color.BLACK;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	line = !line; 
        }
        repaint();
    }
    /**
     * Klump left these here.
     */
    public void keyReleased(KeyEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    /**
     * This tracks where the mouse is currently on the screen.
     */
    public void mouseMoved(MouseEvent e) {
    	currentX = e.getX();
    	currentY = e.getY();
        message = String.format("You are at (%d, %d)",e.getX(),e.getY());
        requestFocusInWindow();  // the panel receives the key events
        repaint();
    }
    /**
     * This is when the mouse is dragged does what the mouse click listen does just at fast intervals
     */
    public void mouseDragged(MouseEvent e) {
        Point p = new Point(e.getX(),e.getY(),color,LineFrame.Size,line);
        //p.setColor(color);
        //p.setSize(LineFrame.Size);
        //System.out.println(LineFrame.Size);
        p.setConnectedState(line);
        points.add(p);
        repaint();
    }
}
/**
 * This class is the Save and Load functions.
 * @author motorola
 *
 */
class PointsIO {
	/**
	 * Checks weather or not the save was sucessfull
	 * @param points
	 * @param fname
	 * @return
	 */
    public boolean savePoints(ArrayList<Point> points, String fname) {
        try {
            File f = new File(fname);
            return savePoints(points, f);
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * Writes the arraylist of points to a text document.
     * @param points
     * @param f
     * @return
     */
    public boolean savePoints(ArrayList<Point> points, File f) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            for (Point p : points) {
                pw.println(p);
            }
            pw.close();         
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * This loads in a text files and adds all the objects to an array list to be painted 
     * @param points
     * @param f
     * @return
     */
    public boolean loadPoints(ArrayList<Point> points, File f) {
        try {
            Scanner sc = new Scanner(f);
            String line;
            String[] parts;
            int x,y;
            Point p;
            String thing;
            Color col = null;
            int size;
            String lineStateString;
            Boolean lineSatetBoolean = null;
            
//          points.clear();
            //We could have an option weather on not the user wants to stack files
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (!line.equals("")) {
                    parts = line.split(" ");
                    x = Integer.parseInt(parts[0]);
                    y = Integer.parseInt(parts[1]);
                    thing = parts[2];
                    size = Integer.parseInt(parts[3]);
                    lineStateString = parts[4];
                    
                    if (thing.equals("Red")){
                    	col = Color.RED;
                    } else if(thing.equals("Blue")){
                    	col = Color.BLUE;
                    } else if(thing.equals("Green")){
                    	col = Color.GREEN;
                    } else if(thing.equals("Black")){
                    	col = Color.BLACK;
                    } 
                    
                    if (lineStateString.equals("true")){
                    	lineSatetBoolean = true;
                    } else {
                    	lineSatetBoolean = false;
                    } 
                    System.out.println(lineSatetBoolean);
                    p = new Point(x,y,col,size,lineSatetBoolean);
                    points.add(p);
                }
            }
            sc.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
/**
 * This is the Line LineFrame that creates the Jframe
 * @author motorola
 *
 */
class LineFrame extends JFrame implements ActionListener {
    private ArrayList<Point> points;
    private Timer tim;
    private Random rnd;
    private LinePanel panLines;
    static int Size = 10;   
    /**
     * this is the action listner for the timmer its values are dedermined in the menues.
     */
    public void actionPerformed(ActionEvent e) {
        int dx, dy;
        for (Point p : points) {
            dx = -10 + rnd.nextInt(20);
            dy = -10 + rnd.nextInt(20);
            p.setX(p.getX() + dx);
            p.setY(p.getY() + dy);
        }
        repaint();
    }
    /**
     * button that clears all the points on the screens.
     */
    public void clearPoints() {
    	//Clear removed all the objects from an Array list. this is java not our class!
        points.clear();
        repaint();
    }
    /**
     * Thsi functions sets up all the drop down menu options.
     */
    public void setupMenu() {
        JMenuBar mbar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenu Times = new JMenu("Change Time speed");
        JMenuItem miStartTimer = new JMenuItem("Start timer");
        miStartTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tim.setDelay(2500);
                tim.start();
            }
        });
        Times.add(miStartTimer);
        JMenuItem miStartMidTimer = new JMenuItem("Start MID timer");
        miStartMidTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tim.setDelay(1000);
            	tim.start();
            }
        });
        Times.add(miStartMidTimer);
        JMenuItem miStartFastTimer = new JMenuItem("Start FAST timer");
        miStartFastTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tim.setDelay(500);
            	tim.start();
            }
        });
        Times.add(miStartFastTimer);
        JMenuItem miStopFastTimer = new JMenuItem("Stop Timer!");
        miStopFastTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tim.stop();
            }
        });
        Times.add(miStopFastTimer);
        mnuFile.add(Times);
        
        //DOT SIZE
        JMenu dotSizeSubmenu = new JMenu("Change Dot Size");
        JMenuItem dotSizeSmall = new JMenuItem("Small");
        dotSizeSmall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Size = 5;
            }
        });
        dotSizeSubmenu.add(dotSizeSmall);
        mnuFile.add(dotSizeSubmenu);
        
        JMenuItem dotSizeMed = new JMenuItem("Medium");
        dotSizeMed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Size = 10;
            }
        });
        dotSizeSubmenu.add(dotSizeMed);
        mnuFile.add(dotSizeSubmenu);
        
        JMenuItem dotSizeLarge = new JMenuItem("Large");
        dotSizeLarge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Size = 20;
            }
        });
        dotSizeSubmenu.add(dotSizeLarge);
        mnuFile.add(dotSizeSubmenu);
        // DOT Size END
        
        //Chage Line state
        JMenuItem changeLinestate = new JMenuItem("Change Line State");
        changeLinestate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	panLines.setConnectedState();            	
            }
        });
        mnuFile.add(changeLinestate);
        //End line state
        JMenuItem miLoad = new JMenuItem("Load");
        miLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    PointsIO pio = new PointsIO();
                     if (pio.loadPoints(points,jfc.getSelectedFile())) {
                         JOptionPane.showMessageDialog(null, "Points were read from the file.");
                     } else {
                         JOptionPane.showMessageDialog(null, "Boo. Hiss. Drats. The file could not be read.");
                     }
                    repaint();
                }
            }
        });
        mnuFile.add(miLoad);
        JMenuItem miSave = new JMenuItem("Save");
        miSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PointsIO pio = new PointsIO();
/*               if (pio.savePoints(points,"C:\\Users\\klumpra\\Dropbox\\2017Fall\\cpsc24500\\3pmExamples\\points.txt")) {
                     JOptionPane.showMessageDialog(null,"Points written successfully!");
                 } else {
                     JOptionPane.showMessageDialog(null,"Something bad happened.");
                 }
*/
                JFileChooser jfc = new JFileChooser();
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    pio.savePoints(points, jfc.getSelectedFile());
                }
            }
        });
        mnuFile.add(miSave);
        JMenuItem miExit = new JMenuItem("Exit");
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuFile.add(miExit);
        JMenu mnuEdit = new JMenu("Edit");
        JMenuItem miClear = new JMenuItem("Clear");
        miClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearPoints();
            }
        });
        mnuEdit.add(miClear);
        mbar.add(mnuFile);
        mbar.add(mnuEdit);
        setJMenuBar(mbar);
    }
    /**
     * This funtion sets up the UI and Jpannel.
     */
    public void setupUI() {
        setBounds(100,100,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Line Drawing App");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panSouth = new JPanel();
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearPoints();
            }
        });
        panSouth.add(btnClear);
        c.add(panSouth, BorderLayout.SOUTH);
        panLines = new LinePanel(points);
        c.add(panLines, BorderLayout.CENTER);
        setupMenu();
    }
    /**
     * This decalres the points array list and set up the timers
     * @param points
     */
    public LineFrame(ArrayList<Point> points) {
        this.points = points;
        tim = new Timer(1000, this);
        rnd = new Random();
        setupUI();
    }
}
/**
 * This is the main function that calls all other classes and sets them visable
 * @author motorola
 *
 */
public class MatthewRatajczykLineDrawer {
    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<Point>();
        LineFrame frmLine = new LineFrame(points);
        frmLine.setVisible(true);
    }
}