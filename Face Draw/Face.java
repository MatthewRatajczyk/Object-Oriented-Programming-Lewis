
public class Face extends Circle {
	private int smileType;
	private int diameter;
	public int getSmileType() {
		return smileType;
	}
	public void setSmileType(int st) {
	if (st < 0) {
		smileType = 0;
	} else if (st > 2) {
		smileType = 2;
	} else {
			smileType = st;
		}
	}
	public Face() {
		smileType = 0;
		diameter= 0;
	}
	public Face(int x, int y, int r, int st) {
		super(x,y,r);
		setSmileType(st);
		diameter= 2 * r;
	}
	@Override
	public String getShapeType() {
		return "face";
	}
	public String getSmileTypeAsString( ) {
		if (smileType == 0) {
			return "meh" ;
		} else if (smileType == 1) {
			return "smile";
		} else {
			return "frown ";
		}
	}
	@Override
	public String toString() {
		return String .format( "%s %s ", super.toString(), getSmileTypeAsString());
	}
	public int getLeftEyeX() {
		return (int)(getX() + 0.1*diameter);
	}
	public int getRightEyeX() {
		return (int)(getX() + 0.7*diameter);
	}
	public int getEyeDiameter() {
		return (int)(0.2*diameter) ;
	}
	public int getEyeY() {
		return (int)(getY() + 0.2*diameter);
	}
	public int getMouthX() {
		return (int)(getX( ) + 0.1*diameter);
	}
	public int getMouthY( ) {
		return (int)(getY( ) + 0.7*diameter);
	}
	public int getMouthHeight() {
		return (int)(0.1*diameter);
	}
	public int getMouthWidth() {
		return (int)(0.8* diameter) ;
	}
	public int getMouthAngle() {
		if (smileType == 0) {
			return 0;
		} else if (smileType == 1 ) {
			return 180;
		} else {
			return -180;
		}
	}
}
