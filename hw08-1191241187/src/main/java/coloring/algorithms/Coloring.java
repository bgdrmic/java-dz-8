package coloring.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import marcupic.opjj.statespace.coloring.Picture;

/**
 * A class which represents a strategy for coloring some shape.
 * @author Božidar Grgur Drmić
 *
 */
public class Coloring {

	/**
	 * Pixel form which coloring starts.
	 */
	private Pixel reference;
	/**
	 * Picture which is modified.
	 */
	private Picture picture;
	/**
	 * Color with which the painting is done.
	 */
	private int fillColor;
	/**
	 * Color of the reference pixel.
	 */
	private int refColor;	
	
	/**
	 * A constructor which accepts all the relevant data.
	 * {@code refColor} is determined depending on {@code reference} pixel.
	 * @param reference - a pixel from which the paining is started.
	 * @param picture - picture which is modified.
	 * @param fillColor - color with which the painting is done.
	 */
	public Coloring(Pixel reference, Picture picture, int fillColor) {
		super();
		this.reference = reference;
		this.picture = picture;
		this.fillColor = fillColor;
		refColor = picture.getPixelColor(reference.x, reference.y);
	}

	/**
	 * A supplier for first pixel.
	 */
	public Supplier<Pixel> s0 = () -> {
		return reference;
	};
	
	/**
	 * A consumer which changes the color of some pixel.
	 */
	public Consumer<Pixel> process = pixel -> picture.setPixelColor(pixel.x, pixel.y, fillColor);
	
	/**
	 * A functions which accepts a pixel and returns a list of adjacent pixels.
	 */
	public Function<Pixel,List<Pixel>> succ = pixel -> {
		var list = new LinkedList<Pixel>();
		if(picture.getHeight() > pixel.y+1) {
			list.add(new Pixel(pixel.x, pixel.y+1));
		}
		if(pixel.y-1 >= 0) {
			list.add(new Pixel(pixel.x, pixel.y-1));
		}
		if(picture.getWidth() > pixel.x+1) {
			list.add(new Pixel(pixel.x+1, pixel.y));
		}
		if(pixel.x-1 >= 0) {
			list.add(new Pixel(pixel.x-1, pixel.y));
		}
		return list;
	};
	
	/**
	 * A tester which check whether some pixel should or shouldn't be painted.
	 */
	public Predicate<Pixel> acceptable = pixel -> picture.getPixelColor(pixel.x, pixel.y) == refColor && refColor != fillColor;
	
}
