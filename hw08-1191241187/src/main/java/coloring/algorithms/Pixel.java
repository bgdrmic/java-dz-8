package coloring.algorithms;

import java.util.Objects;

/**
 * A class which represents a pixel.
 * @author Božidar Grgur Drmić
 *
 */
public class Pixel {

	/**
	 * x-coordinate of his pixel.
	 */
	public int x;
	/**
	 * y-coordinate of his pixel.
	 */
	public int y;
	

	/**
	 * A constructor which accepts location of pixel as argument. 
	 * @param x - x-coordinate of the pixel
	 * @param y - y-coordinate of the pixel
	 */
	public Pixel(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pixel))
			return false;
		Pixel other = (Pixel) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
		
}
