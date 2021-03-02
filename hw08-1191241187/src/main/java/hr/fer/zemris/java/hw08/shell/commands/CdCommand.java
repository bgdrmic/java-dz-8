package hr.fer.zemris.java.hw08.shell.commands;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw08.shell.Environment;
import hr.fer.zemris.java.hw08.shell.ShellCommand;
import hr.fer.zemris.java.hw08.shell.ShellStatus;

public class CdCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		try {
			env.setCurrentDirectory(Paths.get(arguments));
		} catch(RuntimeException e) {
			env.writeln("Wrong path");
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "cd";
	}

	@Override
	public List<String> getCommandDescription() {
		ArrayList<String> description = new ArrayList<>();
		description.add("Accepts one argument, a path to some directory. Changes the current directory to that directory.");
		return Collections.unmodifiableList(description);
	}

}
