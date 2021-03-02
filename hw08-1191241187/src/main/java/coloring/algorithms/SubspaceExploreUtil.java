package coloring.algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A utility class which contains algorithms for coloring shapes.
 * @author Božidar Grgur Drmić
 *
 */
public class SubspaceExploreUtil {

	
	/**
	 * A BFS-based algorithm for performing some operation on all the acceptable objects of some type.
	 *
	 * @param s0 - supplier for first object from which the algorithm is started.
	 * @param process - performs an operation with some object as parameter.
	 * @param succ - A function which accepts some object and finds successive objects
	 * 				 reachable from that one based on some rule. 
	 * @param acceptable - a test which checks whether some object is acceptable.
	 */
	public static <S> void bfs(Supplier<S> s0, Consumer<S> process,
			Function<S,List<S>> succ, Predicate<S> acceptable) {
		
		LinkedList<S> toBeChecked = new LinkedList<>();
		toBeChecked.add(s0.get());
		
		while(!toBeChecked.isEmpty()) {
			var elem = toBeChecked.pop();
			
			if(!acceptable.test(elem)) continue;
			
			process.accept(elem);
		
			toBeChecked.addAll(succ.apply(elem));
		}
	}
	
	/**
	 * A DFS-based algorithm for performing some operation on all the acceptable objects of some type.
	 *
	 * @param s0 - supplier for first object from which the algorithm is started.
	 * @param process - performs an operation with some object as parameter.
	 * @param succ - A function which accepts some object and finds successive objects
	 * 				 reachable from that one based on some rule. 
	 * @param acceptable - a test which checks whether some object is acceptable.
	 */
	public static <S> void dfs(Supplier<S> s0, Consumer<S> process,
			Function<S,List<S>> succ, Predicate<S> acceptable) {
		
		LinkedList<S> toBeChecked = new LinkedList<>();
		toBeChecked.add(s0.get());
		
		while(!toBeChecked.isEmpty()) {
			var elem = toBeChecked.pop();
			
			if(!acceptable.test(elem)) continue;
			
			process.accept(elem);

			toBeChecked.addAll(0, succ.apply(elem));
		}
	}
	
	/**
	 * A BFS-based algorithm for performing some operation on all the acceptable objects of some type.
	 * One object is never visited twice.
	 *
	 * @param s0 - supplier for first object from which the algorithm is started.
	 * @param process - performs an operation with some object as parameter.
	 * @param succ - A function which accepts some object and finds successive objects
	 * 				 reachable from that one based on some rule. 
	 * @param acceptable - a test which checks whether some object is acceptable.
	 */
	public static <S> void bfsv(Supplier<S> s0, Consumer<S> process,
			Function<S,List<S>> succ, Predicate<S> acceptable) {
		
		Set<S> alreadyVisited = new HashSet<>();
		LinkedList<S> toBeChecked = new LinkedList<>();
		toBeChecked.add(s0.get());
		alreadyVisited.add(s0.get());
		
		while(!toBeChecked.isEmpty()) {
			var elem = toBeChecked.pop();
			
			if(!acceptable.test(elem)) continue;
			
			process.accept(elem);
		
			for(var child : succ.apply(elem)) {
				if(alreadyVisited.contains(child)) continue;
				
				alreadyVisited.add(child);
				toBeChecked.add(child);
			}
		}
	}
	
}
