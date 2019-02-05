abstract class Shape {
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Shape() {
		x = 0;
		y = 0;
	}
	public Shape (int x, int y) {
		setX(x);
		setY(y);
	}
	public String getShapeType() {
		return "shape";
	}
	public abstract double findArea();
	public abstract double findPerim();
	@Override
	public String toString() {
		return String.format("%s %d %d",getShapeType(),x,y);
	}
}
class Circle extends Shape {
	private int radius;
	public int getRadius() {
		return radius;
	}
	public void setRadius(int r) {
		if (r < 0) {
			radius = 0;
		} else {
			radius = r;
		}
	}
	public Circle() {
		radius = 0;
	}
	public Circle(int x, int y, int r) {
		super(x,y);
		setRadius(r);
	}
	public double findArea() {
		return Math.PI * radius * radius;
	}
	public double findPerim() {
		return 2 * Math.PI * radius;
	}
	@Override
	public String getShapeType() {
		return "circle";
	}
	@Override
	public String toString() {
		return String.format("%s %d", super.toString(),radius);
	}
}
class Rectangle extends Shape {
	private int width;
	private int length;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = Math.abs(width);
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = Math.abs(length);
	}
	public Rectangle() {
		width = 0;
		length = 0;
	}
	public Rectangle(int x, int y, int w, int len) {
		super(x,y);
		setWidth(w);
		setLength(len);
	}
	public double findArea() {
		return length * width;
	}
	public double findPerim() {
		return 2 * (length + width);
	}
	@Override
	public String getShapeType() {
		return "rectangle";
	}
	@Override
	public String toString() {
		return String.format("%s %d %d", super.toString(),width,length);
	}
}
