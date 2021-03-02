package hr.fer.zemris.java.hw08.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.zemris.java.hw08.shell.Environment;
import hr.fer.zemris.java.hw08.shell.ShellCommand;
import hr.fer.zemris.java.hw08.shell.ShellStatus;

public class MassrenameCommand implements ShellCommand {
	
	private static List<FilterResult> filter(Path dir, String pattern) throws IOException {
		if(!Files.isDirectory(dir)) {
			throw new IOException("This is not a direcotry");
		}
		
		List<FilterResult> acceptable = new ArrayList<>();
		
		
		Pattern pat = Pattern.compile(pattern);
		for(var file: dir.toFile().listFiles()) {
			if(file.isDirectory()) continue;
			
			if(!file.getName().matches(pattern)) continue;

			Matcher m = pat.matcher(file.getName());
			
			if(!m.find()) continue;
			
			String[] groups = new String[m.groupCount() + 1];
			for(int i = 0; i < groups.length; i++) {
				groups[i] = m.group(i);
			}
			
			acceptable.add(new FilterResult(file.toPath(), groups));
		}
		
		return acceptable;
	}
	
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		var result = Parser.readPath(arguments);
		arguments = result.getRemainingString();
		Path origin = Paths.get(result.getResult());
		
		result = Parser.readPath(arguments);
		arguments = result.getRemainingString();
		Path destination = Paths.get(result.getResult());
		
		result = Parser.parseCMD(arguments);
		arguments = result.getRemainingString();
		String cmd = result.getResult();
		
		result = Parser.parseMask(arguments);
		arguments = result.getRemainingString();
		String mask = result.getResult();
		
		if(!Files.isDirectory(origin) || !Files.isDirectory(destination)) {
			throw new IllegalArgumentException("Two directories required");
		}
		

		List<FilterResult> files;
		try {
			files = filter(origin, mask);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		if(cmd == "filter") {
			for(var file : files) {
				env.writeln(file.toString());
			}
			
			return ShellStatus.CONTINUE;
		}
		
		
		if(cmd == "groups") {
			for(var file : files) {
				env.write(file.toString());
				for(int i = 0; i < file.numberOfGroups(); i++) {
					env.write(" " + i + ": " + file.group(i));
				}
				env.writeln("");
			}
			return ShellStatus.CONTINUE;
		}

		result = Parser.parseMask(arguments);
		arguments = result.getRemainingString();
		String izraz = result.getResult();
		
		if(cmd != "show" && cmd != "execute") {
			throw new IllegalArgumentException("Unkownwn command");
		}
		
		NameBuilderParser parser = new NameBuilderParser(izraz);
		NameBuilder builder = parser.getNameBuilder();
		for(FilterResult file : files) {
			StringBuilder sb = new StringBuilder();
			builder.execute(file, sb);
			String novoIme = sb.toString();			
			env.writeln(file.toString() + " => " + novoIme);
			
			if(cmd == "show") continue;
			
			try {
				if(origin != destination) {
					Files.move(file.getPath(), Paths.get(destination.toString() + "/" +  novoIme));
				} else {
					Files.move(file.getPath(), Paths.get(file.getPath().getParent().toString() + "/" +  novoIme));
				}
			} catch(IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return ShellStatus.CONTINUE;
	}

	@Override	
	public String getCommandName() {
		return "massrename";
	}

	@Override
	public List<String> getCommandDescription() {
		var list = new ArrayList<String>();
		list.add("Accepts five arguments. First argument is path to origin directory,");
		list.add("second is path to destination directory. Third is CMD, fourth is MASK, and fifth is the rest");
		list.add("If CMD is filter than olny those files which fit the mask are moved");
		list.add("If CMD is groups than ");
		
		return Collections.unmodifiableList(list);
	}

}
