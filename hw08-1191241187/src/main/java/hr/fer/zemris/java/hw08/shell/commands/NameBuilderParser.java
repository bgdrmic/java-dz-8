package hr.fer.zemris.java.hw08.shell.commands;

import java.util.ArrayList;
import java.util.List;

public class NameBuilderParser {

	String izraz;
	char[] data;
	int index;	
	NameBuilder result;
	List<NameBuilder> builders = new ArrayList<NameBuilder>();
	
	private static NameBuilder text(String t) {
		return (FilterResult result, StringBuilder sb) -> sb.append(t);
	}
	
	private static NameBuilder group(int index) {
		return (FilterResult result, StringBuilder sb) -> sb.append(result.group(index));
	}
	
	private static NameBuilder group(int ind, char padding, int minWidth) {
		return (FilterResult result, StringBuilder sb) -> {
			for(int i = 0; i < minWidth - result.group(ind).length(); i++) {
				sb.append(padding);
			}
			sb.append(result.group(ind));
		};
	}
	
	private static NameBuilder composit(List<NameBuilder> builders) {
		return (FilterResult result, StringBuilder sb) -> {
			for(int i = 0; i < builders.size(); i++) {
				builders.get(i).execute(result, sb);
			}
		};
	}
	
	public NameBuilderParser(String izraz) {
		this.izraz = izraz;
		this.data = izraz.toCharArray();
		this.index = 0;
		this.result = null;
		parse();
	}
	
	public NameBuilder getNameBuilder() {
		return result;		
	}
	
	private void parse() {
		while(result == null) {
			if(index == data.length) {
				result = composit(builders);
				continue;
			}
			int i = index;
			while(index < data.length && (index+1 == data.length || data[index] != '$' || data[index+1] != '{')) {
				index++;
			}
			if(i != index) {
				builders.add(text(izraz.substring(i, index)));
				continue;
			}
			
			index += 2;
			i = index;
			while(!Character.isWhitespace(data[index]) && data[index] != '}' && data[index] != ',') {
				index++;
			}
			
			int group = Integer.parseInt(izraz.substring(i, index));
			skipBlanks();
			if(data[index] == '}') {
				index++;
				builders.add(group(group));
				continue;
			}
			
			if(data[index] != ',') {
				throw new IllegalArgumentException("Wrong format");
			}		
			index++;
			skipBlanks();
			i = index;
			
			char padding = ' ';
			if(data[index] == '0') padding = '0';
			while(index < data.length && data[index] != '}') {
				index++;
			}
			
			if(data[index] != '}') {
				throw new IllegalArgumentException("Wrong format");
			}
			int minWidth = Integer.parseInt(izraz.substring(i, index));
			
			index++;
			builders.add(group(group, padding, minWidth));
		}
	}	
	
	private void skipBlanks() {
		while(index < data.length) {
			if(!Character.isWhitespace(data[index])) {
				return;
			}
			index++;
		}
	}

}
