package hr.fer.zemris.java.hw08.shell.commands;

public interface NameBuilder {
	
	void execute(FilterResult result, StringBuilder sb);
	
	default NameBuilder then(NameBuilder other){
		return (FilterResult result, StringBuilder sb) -> {
			this.execute(result, sb);
			other.execute(result, sb);			
		};
	}
	
}
