package hr.fer.zemris.java.hw08.shell.commands;

import java.nio.file.InvalidPathException;

/**
 * A simple parser class.
 * @author Božidar Grgur Drmić
 *
 */
public class Parser {

	/**
	 * Symbol for escaping.
	 */
	public static final char ESC = '\\';
	
	/**
	 * A method which parses one path from the given string.
	 * @param arg - string which is parsed.
	 * @return pair of path which was parsed and the rest of the string which hasn't yet been parsed.
	 * @throws InvalidPathException if the path is wrong.
	 */
	public static Pair readPath(String arg) {
		int index = arg.length() - arg.stripLeading().length();
		
		boolean escaped = false;
		boolean quote = false;
		
		StringBuilder sb = new StringBuilder();
		
		for(; index < arg.length(); index++) {			
			char c = arg.charAt(index); 	
			
			if(escaped) {
				escaped = false;
				if(c != '"' && c != ESC) {
					sb.append(ESC);
				}
				sb.append(c);
				continue;
			}
			
			if(c == '"') {
				if(quote) {
					quote = false;
				} else {
					quote = true;
				}
				if(quote == false) {
					if(index+1 != arg.length() && arg.charAt(index+1) != ' ') {
						throw new InvalidPathException(null, null);
					}
				}
				continue;
			}
			if(c == ESC) {
				if(quote) {
					escaped = true;
				} else {
					sb.append(ESC);
				}
				continue;
			}
			if(Character.isWhitespace(c) && !quote) {
				index++;
				break;
			}
			sb.append(c);
		}
		arg = arg.substring(index);
		return new Pair(arg, sb.toString());
	}
	
	public static Pair parseMask(String arg) {
		int index = arg.length() - arg.stripLeading().length();
		
		if(arg.charAt(index) == '"') {
			index++;
			boolean escaped = false;
			StringBuilder sb = new StringBuilder();
			
			while(index < arg.length()) {
				char c = arg.charAt(index);
				if(escaped) {
					escaped = false;
					if(c != '"' && c != '\\') {
						sb.append('\\');
					}
					sb.append(c);
				} else if(c == '\\') {
					escaped = true;
				} else if(c == '"') {
					index++;
					break;
				} else {
					sb.append(c);
				}
				index++;
			}
			return new Pair(arg.substring(index), sb.toString());
		}
		
		int i = index;
		while(index < arg.length() && arg.charAt(index) != ' ') index++;
		return new Pair(arg.substring(index), arg.substring(i, index));
	}
	
	public static Pair parseCMD(String arg) {
		String remaining = arg.stripLeading();
		if(remaining.startsWith("execute")) {
			return new Pair(remaining.substring(7), "execute");
		}
		if(remaining.startsWith("filter")) {
			return new Pair(remaining.substring(6), "filter");
		}
		if(remaining.startsWith("groups")) {
			return new Pair(remaining.substring(6), "groups");
		}
		if(remaining.startsWith("show")) {
			return new Pair(remaining.substring(4), "show");
		}
		return null;
	}
	
}
