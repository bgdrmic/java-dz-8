package hr.fer.zemris.java.hw08.shell.commands;

/**
 * A class which represents a pair of two strings.
 * @author Božidar Grgur Drmić
 *
 */
public class Pair {

	/**
	 * Read-only property which represents the string part of pair.
	 */
	private String remainingString;
	/**
	 * Read-only property which represents the path part of pair.
	 */
	private String result;
		
	/**
	 * A getter for the remainingString variable.
	 * @return remainingString
	 */
	public String getRemainingString() {
		return remainingString;
	}
	/**
	 * A getter for the path variable.
	 * @return path
	 */
	public String getResult() {
		return result;
	}

	/**
	 * A constructor which accepts all the relevant fields as arguments.
	 * @param remainingString - remainingString variable is set to this value.
	 * @param result - result variable is set to this value.
	 */
	public Pair(String remainingString, String result) {
		super();
		this.remainingString = remainingString;
		this.result = result;
	}	
	
}
