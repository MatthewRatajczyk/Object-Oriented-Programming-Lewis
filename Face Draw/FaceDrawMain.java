import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random; 


public class FaceDrawMain {
	private static Random rnd;
	public static Face randomFace() {
		return new Face(rnd.nextInt(400), rnd.nextInt(400),50+rnd.nextInt(100),rnd.nextInt(3)); 
	}
	public static void printFaces(ArrayList <Face> faces) {
		for (Face f : faces) {
			System.out.println(f);
		}
	} 
	public static void main(String [ ] args) {
		ArrayList<Face > faces= new ArrayList<Face>( );
		rnd = new Random();
		int faceCount = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter number of faces : "));
		for (int i= 0; i < faceCount ; i++) {
			faces.add(randomFace());
		} 
		printFaces(faces);
		FaceFrame ff= new FaceFrame(faces);
		ff.setVisible(true); 
	}
}
