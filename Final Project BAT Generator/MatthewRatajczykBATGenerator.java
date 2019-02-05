import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static java.awt.SystemColor.window;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import java.awt.Robot;
/**
 * Class Task is an object that holds all the info for the list of task events.
 * @author motorola
 *
 */
class Task{
	private String executable;
	private String path;
	
/**
 * Get and set functions for the .exe file
 * @return
 */
    public String getExecutable() {
        return executable;
    }
    public void setExecutable(String executable) {
        this.executable = executable;
    }
    /**
     * Get and Set functions for the path to the execatable file
     * @return
     */
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * Returns and string of path code ready for a bat file
     * @return
     */
    public String getPathCode() {
    	this.path = "cd \"" + this.path + "\"";
    	System.out.println(path);
        return path;
    }
    /**
     * Returns a the comands required to lauch the exe
     * @return
     */
    public String getExecutableCode() {
    	this.executable = "start " + this.executable;
    	System.out.println(executable);
        return executable;
    }

    /**
     * Construtors. They construct the opbejct when called.
     * @param path
     * @param executable
     */
    public Task(String path, String executable){
    	setExecutable(executable);
    	setPath(path);
    }
    public Task() {

    }
}

/**
 * The Main class Window this creates the Jframe and all its options
 * @author motorola
 *
 */
class MainWindow extends JFrame implements ActionListener{

	private ArrayList<Task> events;
	private boolean areWeLive = false;
	private String comands;
	JLabel label1;
	JButton b;
	 
	/**
	 * Create the main windows
	 * @param events
	 */
	public MainWindow(ArrayList<Task> events) {
		this.events = events;
        setTitle("Making a BAT file Generator");
        setResizable(false);
        setBounds(300,300,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());        
        
        //Button looks ugly like XP ugly, but J button looks super DOPE!!
        JPanel panSouth = new JPanel();
        b = new JButton("Add an excatable to the list.");
        b.addActionListener(this);
        c.add(b, BorderLayout.SOUTH);
        panSouth.add(b);
        c.add(panSouth, BorderLayout.SOUTH);
        String pt1 = "<html><body width='";
        String pt2 =
            "'><center> <h1>BAT File Generator</h1></center> "
            + "<h2>Comands</h2> "
            + "<p>";
        comands = " ";
        String pt3 = "</p></body> <style> p,h2{ line-height: 70%; } </style>";
        int width = 400;
        String s = pt1 + width + pt2 + comands + pt3;         
        JPanel pnaNorth = new JPanel();        
        System.out.println(s);
        label1 = new JLabel();
        label1.setText(s);
        pnaNorth.add(label1);        
        c.add(pnaNorth, BorderLayout.NORTH);
        setupMenu();
        }
	/**
	 * Creates all the menu items and Submenues
	 */
	public void setupMenu() {
        JMenuBar mbar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");    
        JMenu dotSizeSubmenu = new JMenu("Remove Entries");
        JMenuItem dotSizeSmall = new JMenuItem("Remove Last 1");
        dotSizeSmall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	events.remove(events.size() - 1);
            	setupComandline();
            }
        });
        dotSizeSubmenu.add(dotSizeSmall);
        mnuFile.add(dotSizeSubmenu);        
        JMenuItem dotSizeMed = new JMenuItem("Take _ off the top");
        dotSizeMed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int totalRemoved = Integer.parseInt(JOptionPane.showInputDialog("Insert how many to remove"));
            	System.out.println(totalRemoved);
                for(int i=1; i<=totalRemoved; i++){
                    events.remove(events.size() - 1);
               }
            	setupComandline();
            }
        });
        dotSizeSubmenu.add(dotSizeMed);
        mnuFile.add(dotSizeSubmenu);
        
        JMenuItem dotSizeLarge = new JMenuItem("Clear all");
        dotSizeLarge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        dotSizeSubmenu.add(dotSizeLarge);
        mnuFile.add(dotSizeSubmenu);
        
        JMenuItem miSave = new JMenuItem("Save");
        miSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            	
            	JFileChooser jfc = new JFileChooser();
            	jfc.setSelectedFile(new File("fileToSave.bat"));
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                	File saveThis = new File(jfc.getSelectedFile()+".bat");
                	System.out.println();
                	saveFile(saveThis);
                }
            	
            }
        });
        mnuFile.add(miSave);        
        JMenuItem miLoad = new JMenuItem("Load");
        miLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser jfc = new JFileChooser();
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                	ArrayList<Task> events = new ArrayList<Task>();
                     if (loadPoints(events,jfc.getSelectedFile()) == true) {
                         JOptionPane.showMessageDialog(null, "Data was read from the file.");
                     } else {
                         JOptionPane.showMessageDialog(null, "Boo. Hiss. Drats. The file could not be read.");
                     }
                    repaint();
                }
            }
        });
        mnuFile.add(miLoad);

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
                events.clear();
                label1.setText("All events deleated!");
            }
        });
        mnuEdit.add(miClear);
        mbar.add(mnuFile);
        mbar.add(mnuEdit);
        setJMenuBar(mbar);
    }
	
	/**
	 * Called when the main pannel need to be re wrote. IE after you do like anythign its my repaint call.
	 */
	public void setupComandline() {
        String pt1 = "<html><body width='";
        String pt2 =
            "'><center> <h1>BAT File Generator</h1></center> "
            + "<h2>Comands</h2> "
            + "<p>";
        String pt3 = "</p></body> <style> p,h2{ line-height: 70%; } </style>";
        int width = 400;
        comands = "";
        for (Task p : events) {
        	if (p != null) {
        		comands = comands + "Execute File: " + p.getPath() + "<br/>" + "At Location : " +p.getExecutable() + "<br/> <br/>";
                String s = pt1 + width + pt2 + comands + pt3; 
                label1.setText(s);
        	}else {
            	return;
        	}
        }    
		return;
	}
	/**
	 * Whene the button is pressed this Opens up a Jdialouge box for you to input the file you whish to execute.
	 */
	public void actionPerformed(ActionEvent e) {		
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		}		
		comands = comands + "\n" +jfc.getSelectedFile().getAbsolutePath();
		System.out.println(comands);
		String fileName = jfc.getSelectedFile().getName();
		System.out.println(fileName);
		File fileDirectory = jfc.getCurrentDirectory();
		String ass = fileDirectory.getPath();
		System.out.println(ass);
		
		Task p = new Task(ass,fileName);
		events.add(p);
		setupComandline();
	}
	/**
	 * Saves the file to a .bat file, and even if you don't type an extenssion or a wrong extesion it will allways come out a bat file
	 * @param f
	 */
	public void saveFile(File f) {		
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            pw.println("@echo off");
            pw.println("");
    		for (Task p : events) {
                pw.println(p.getPathCode());
                pw.println(p.getExecutableCode());
                pw.println("");
            }
    		pw.println("");
    		pw.println("exit");
            pw.close();         
            return;
        } catch (Exception ex) {
        	System.out.println("Something Failed");
            return;
        }
	}
	/**
	 * Called when the programs needs to load in the ANY bat file, it just has to be only executables, dont have all the other comands
	 * I can't program bat files.
	 * @param points
	 * @param f
	 * @return
	 */
	 public boolean loadPoints(ArrayList<Task> points, File f) {
	        try {
	            Scanner sc = new Scanner(f);
	            String line;
	            String Path;
	            String Execute;
	            events.clear();	            
	            sc.nextLine();
//	          points.clear();
	            //We could have an option weather on not the user wants to stack files
	            while (sc.hasNextLine()) {
	                line = sc.nextLine();
	                if(line.equals("exit")) {
	                	return true;
	                }
	                if (!line.equals("")) {
	                	//System.out.println(line);
	                	Path = line.substring(4, line.length() - 1);	                	
	                	System.out.println("THE PATH IS:   "+Path);	                	
	                	line = sc.nextLine();
	                	Execute = line.substring(5);
	                	System.out.println("THE EXECUATABLE IS:   " +Execute);	                	
	                	Task p = new Task(Path,Execute);
	            		events.add(p);
	            		setupComandline();	                
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
 * Class that is first calls that creates an Arry list of task calls the bat file generator and passes it the array list
 * of task objects.
 * @author motorola
 *
 */
public class MatthewRatajczykBATGenerator {
	public static void main(String[] args) {
	ArrayList<Task> events = new ArrayList<Task>();
    MainWindow mw = new MainWindow(events);
    mw.setVisible(true);
	}
}
/**
 *  Funny this is I acatualy used this to make a bat file that launches FF7 and FF7Joypad_2_Keys. 
 *  I MADE SOMETHING USEFULL AND USED IT 
 *  I could have just made the bat file by my self but what ever. AUTOMATION
 */




