package hr.fer.zemris.java.hw08.shell.commands;

import java.nio.file.Path;

public class FilterResult {

	private Path path;
	private String[] groups;
	
	public FilterResult(Path path,  String[] groups) {
		super();
		this.path = path;
		this.groups = groups;
	}

	public String toString() {
		return path.getFileName().toString();
	}
	
	public Path getPath() {
		return path;
	}
	
	public int numberOfGroups() {
		return groups.length;
	}
	
	public String group(int index) {
		return groups[index];
	}
	
}
