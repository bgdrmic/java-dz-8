package hr.fer.zemris.java.hw08.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw08.shell.Environment;
import hr.fer.zemris.java.hw08.shell.ShellCommand;
import hr.fer.zemris.java.hw08.shell.ShellStatus;

public class PushdCommand implements ShellCommand {

	@SuppressWarnings("unchecked")
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		arguments = Parser.readPath(arguments).getResult();
		if(Files.notExists(Paths.get(arguments))) {
			throw new IllegalArgumentException();
		}
		
		Stack<Path> stack = (Stack<Path>) env.getSharedData("cdstack");
		
		if(stack == null) {
			Stack<Path> newStack = new Stack<>();
			env.setSharedData("cdstack", newStack);
			stack = newStack;
		}
		
		stack.push(env.getCurrentDirectory());
		env.setCurrentDirectory(Paths.get(arguments));

		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "pushd";
	}

	@Override
	public List<String> getCommandDescription() {
		var list = new ArrayList<String>();
		list.add("Takes one argument, path to some directory which must exist.");
		list.add("The previous directory is pushed onto a stack in shared data");
		list.add("under \"cdstack\" which is if necesarry created");
		return Collections.unmodifiableList(list);
	}

}
