package hr.fer.zemris.java.hw08.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import hr.fer.zemris.java.hw08.shell.Environment;
import hr.fer.zemris.java.hw08.shell.ShellCommand;
import hr.fer.zemris.java.hw08.shell.ShellStatus;

public class PopdCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
	
		var stack = env.getSharedData("cdstack");
		if(stack == null) {
			throw new NoSuchElementException();
		}
		
		@SuppressWarnings("unchecked")
		var path = ((Stack<Path>) stack).pop();
		
		if(Files.exists(path)) {
			env.setCurrentDirectory(path);
		}
		
		return ShellStatus.CONTINUE;
	}
	
	@Override
	public String getCommandName() {
		return "popd";
	}

	@Override
	public List<String> getCommandDescription() {
		var list = new ArrayList<String>();
		list.add("Removes one path from stack of paths and if ");
		list.add("it exists makes it the currently active one");
		return Collections.unmodifiableList(list);
	}

}
