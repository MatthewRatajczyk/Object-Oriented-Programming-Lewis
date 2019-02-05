import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Random; 


public class FaceTilesMatthewRatajczyk {
	private static Random rnd;
	/**
	 * Creates a New face object that is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public static Face randomFace(int x, int y) {
		return new Face(x,y,25,rnd.nextInt(3)); 
	}
	/**
	 * Creates a lists of face objects.
	 * @param faces
	 */
	public static void GenerateFaces(ArrayList<Face> faces) {
		for (int y= 0; y < 500 ; y=y+50) {
			for (int x= 0; x < 500 ; x=x+50) {
				faces.add(randomFace(x,y));
			} 
		} 
	}
	/**
	 * Prints the list of facess.
	 * @param faces
	 */
	public static void printFaces(ArrayList <Face> faces) {
		for (Face f : faces) {
			System.out.println(f);
		}
	} 
	/**
	 * Start of the programs creates all the objects
	 * and set the jframe to visiable.
	 * @param args
	 */
	public static void main(String [ ] args) {
		ArrayList<Face > faces= new ArrayList<Face>( );
		rnd = new Random();
		GenerateFaces(faces);
		printFaces(faces);
		FaceFrame ff= new FaceFrame(faces);
		ff.setVisible(true); 
	}
}
