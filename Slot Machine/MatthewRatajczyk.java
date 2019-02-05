/**
 * 
 * @author Matthew Ratajczyk 
 * This program is a slot machine 
 * After corprate gets word of this it will be mutilated into a senseless cash grab slot machine game for the app store!
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.awt.SystemColor.window;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
/**
 * 
 * This is the MyPanel Class
 * It's main purpose is to take random int's from the action Listener  
 * and to paint the appropriate shape.
 */
class MyPanel extends JPanel{
	private Color col;
	private int i;

	public void setI(int i) {
		this.i = i;
	}

	public MyPanel (int i) {
		setI(i);
	}
	/**
	 * 
	 * @param i
	 * MyPanel Spin is what allows the class to take in varaibles, and store them localy.
	 * 
	 */
	public void Spin(int i) {
		setI(i);
        repaint();
	}
	/**
	 * paintComponent is the painter of the program and takes all the information established 
	 * from the main window and paints it.
	 */
    @Override
    public void paintComponent(Graphics g) {
    	//System.out.println(z+" SHAPE "+i);
        if (i == 4 || i == 1) {
            col = Color.RED;
        } else if (i == 5 || i == 2) {
            col = Color.BLUE;
        } else if (i == 6 || i == 3) {
            col = Color.GREEN;
        }
        super.paintComponent(g);
        g.setColor(col);
        System.out.println(" PAINT "+i);
        if (i >= 4) {
            g.fillOval(65,50,200,200);
        }else {
        	g.fillRect(65,50,200,200);
        }
    }
    
}
/**
 * 
 * MainWindow is the heart of the program which holds the JFrame as well as classes 
 * ment to calcuate weather you win or lose and if you have enough money as 
 * well as manage how much you have total
 *
 */
class MainWindow extends JFrame{
	private double x = 1;
	private boolean money2spin = true;
	/**
	 * MainWindow
	 * This is where the action listners are as well as the framework for the jFrame
	 */
	public MainWindow() {
              setTitle("JSlotMachine Version 1.0");
              setResizable(false);
              setBounds(300,300,1000,350);
              setDefaultCloseOperation(EXIT_ON_CLOSE);
              Container c = getContentPane();
              c.setLayout(new BorderLayout());
              JPanel subPanel = new JPanel();
              Random rand = new Random();
              
              JPanel Slots = new JPanel();
              Slots.setLayout(new GridLayout(1,3));
              MyPanel Pan1 = new MyPanel(1);
              Slots.add(Pan1);
              MyPanel Pan2 = new MyPanel(1);
              Slots.add(Pan2);
              MyPanel Pan3 = new MyPanel(1);
              Slots.add(Pan3);

              
              c.add(Slots, BorderLayout.CENTER); 
              
              JLabel Title = new JLabel("  Current balance:   $" + x+"0");
              c.add(Title, BorderLayout.NORTH); 
              /**
               * Button that bets .50 cents and puts the newly painted pictuers into a JPannel 
               * and onto the containter, all contained in a action listener.
               */
              JButton MAX = new JButton("Bet Max");
              subPanel.add(MAX);
              MAX.addActionListener(new ActionListener() {
            	  public void actionPerformed(ActionEvent e) {
            		  Bet(x,.5,money2spin);
            		  String finx = String.format("%.2f", x);
            		  Title.setText("Current balance:   $" + finx );
            		  
            		  if (money2spin == true){
              			  int  s1 = rand.nextInt(6) + 1;
              			  Pan1.Spin(s1);
              			  Slots.add(Pan1);
              			  int  s2 = rand.nextInt(6) + 1;
            			  Pan2.Spin(s2);
            			  Slots.add(Pan2);
            			  int  s3 = rand.nextInt(6) + 1;
              			  Pan3.Spin(s3);
              			  Slots.add(Pan3);
                		  c.add(Slots, BorderLayout.CENTER);  
              			  DidWin(s1,s2,s3,x,.5);
                		  String finx1 = String.format("%.2f", x);
                		  Title.setText("Current balance:   $" + finx1 );
            		  }
            	  }


              });
              /**
               * Button that bets .1 cents and puts the newly painted pictuers into a JPannel 
               * and onto the containter, all contained in a action listener.
               */
              JButton MIN = new JButton("BetMin");
              subPanel.add(MIN);
              MIN.addActionListener(new ActionListener() {
            	  public void actionPerformed(ActionEvent e) {
            		  Bet(x,.1,money2spin);
            		  String finx = String.format("%.2f", x);
            		  Title.setText("Current balance:   $" + finx );

            		  if (money2spin == true){
              			  int  s1 = rand.nextInt(6) + 1;
              			  Pan1.Spin(s1);
              			  Slots.add(Pan1);
              			  int  s2 = rand.nextInt(6) + 1;
            			  Pan2.Spin(s2);
            			  Slots.add(Pan2);
            			  int  s3 = rand.nextInt(6) + 1;
              			  Pan3.Spin(s3);
              			  Slots.add(Pan3);
                		  c.add(Slots, BorderLayout.CENTER);  
              			  DidWin(s1,s2,s3,x,.1);
                		  String finx1 = String.format("%.2f", x);
                		  Title.setText("Current balance:   $" + finx1 );
            		  }
            	  }
              });
              /**
               * Button that bets .25 cents and puts the newly painted pictuers into a JPannel 
               * and onto the containter, all contained in a action listener.
               */
              JButton SPIN = new JButton("$pin");
              subPanel.add(SPIN);
              SPIN.addActionListener(new ActionListener() {
            	  public void actionPerformed(ActionEvent e) {
            		  Bet(x,.25,money2spin);
            		  String finx = String.format("%.2f", x);
            		  Title.setText("Current balance:   $" + finx );
          			  
            		  if (money2spin == true){
              			  int  s1 = rand.nextInt(6) + 1;
              			  Pan1.Spin(s1);
              			  Slots.add(Pan1);
              			  int  s2 = rand.nextInt(6) + 1;
            			  Pan2.Spin(s2);
            			  Slots.add(Pan2);
            			  int  s3 = rand.nextInt(6) + 1;
              			  Pan3.Spin(s3);
              			  Slots.add(Pan3);
                		  c.add(Slots, BorderLayout.CENTER);  
              			  DidWin(s1,s2,s3,x,.25);
                		  String finx1 = String.format("%.2f", x);
                		  Title.setText("Current balance:   $" + finx1 );
            		  }            		  
            	  }
              });
              c.add(subPanel, BorderLayout.SOUTH);                         
       }
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @param x
	 * @param y
	 * 
	 * This here takes in all the valuabes used to calculate money and slots and compares them.
	 * It then outputs the correct amount of winnings per what the user spined and is added to 
	 * their balance.
	 */
		private void DidWin(int s1, int s2, int s3, double x, double y) {
			this.x = x;
			double win = y;
			if (s1==1 || s1==4) {
				if(s2==1 || s2==4) {
					if(s3==1 || s3==4) {
						if(s1==s2 && s1==s3) {
							this.x = this.x+win+win+win+win;
							System.out.print(this.x + "this  local"+ x);
							JOptionPane.showMessageDialog(null, "WOW ALL RED and ALL THE SAME SHAPE!!");
							return;
						}else {
							JOptionPane.showMessageDialog(null, "YOU WON ALL RED");
							this.x = this.x+win + win;
						}
					}
				}
				
			}
			if (s1==2 || s1==5) {
				if(s2==2 || s2==5) {
					if(s3==2 || s3==5) {
						if(s1==s2 && s1==s3) {
							this.x = this.x+win+win+win+win;
							System.out.print(this.x + "this  local"+ x);
							JOptionPane.showMessageDialog(null, "WOW ALL BLUE and ALL THE SAME SHAPE!!");
							return;
						}else {
						JOptionPane.showMessageDialog(null, "YOU WON ALL BLUE");
						this.x = this.x+win+win;
						}
					}
				}
				
			}
			if (s1==3 || s1==6) {
				if(s2==3 || s2==6) {
					if(s3==3 || s3==6) {
						if(s1==s2 && s1==s3) {
							this.x = this.x+win+win+win+win;
							System.out.print(this.x + "this  local"+ x);
							JOptionPane.showMessageDialog(null, "WOW ALL RED and ALL THE SAME SHAPE!!");
							return;
						}else {
						JOptionPane.showMessageDialog(null, "YOU WON ALL GREEN");
						this.x = this.x+win+win;
						}
					}
				}
				
			}
			
			if (s1==1 || s1==2 || s1==3) {
				if(s2==1 || s2==2 || s2==3) {
					if(s3==1 || s3==2 || s3==3) {
						JOptionPane.showMessageDialog(null, "YOU WON ALL SQUARE");
						this.x = this.x+ win+win;
					}
				}
				
			}
			if (s1==4 || s1==5 || s1==6) {
				if(s2==4 || s2==5 || s2==6) {
					if(s3==4 || s3==5 || s3==6) {
						JOptionPane.showMessageDialog(null, "YOU WON ALL CIRCLE");
						this.x = this.x+win+win;
					}
				}
				
			}
			return;
		}
		/**
		 * 
		 * @param x
		 * @param y
		 * @param money2spin
		 * 
		 * This here is the bet function it takes in whatever bet button was pushed
		 * and subtracts that amount from the private X. It also checks if it dips into the negative 
		 * not only does this solve the round off error it also allows the user to continue playing 
		 * the .1 if the hit .5 by acident and only have .4
		 */
       public void Bet(double x,double y, boolean money2spin) {
	   		this.x = x - y;
	   		if(this.x <= 0) {
	   			JOptionPane.showMessageDialog(null, "you don't have enought money. Therefore YOU SHALL NOT PASS!!");
	   			this.x = x;
	   			this.money2spin = false;
	   		}else {
	   			System.out.println(this.x);
	   			this.money2spin = true;
	   		}
	   		return;
   		}
       
}


/**
 * 
 * This was going to be a score board but I'm sick and can't get this done although its total
 * possible, thank god this isn't required and me just wanting to my some SCPICE to the program.
 *
 */
class Score extends JFrame {
       public Score() {
    	   //going to stop for a while NONE OF THIS WORKS FFS
              setTitle("SCORES");
              setResizable(false);
              setBounds(1310,300,200,350);
              Container c = getContentPane();
              JPanel pane = new JPanel();

              
          }

}
/**
 * 
 * the start of the program. It class Mainwindow to be created as well as Score to be created!
 *
 */
public class MatthewRatajczyk {
    public static void main(String[] args) {
    	
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
        
        Score guide = new Score();
        guide.setVisible(true);
    }
    
}
