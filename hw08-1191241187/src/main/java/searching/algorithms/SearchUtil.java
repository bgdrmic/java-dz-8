package searching.algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A utility class which contains algorithms for finidng a solution for Slagalica problem.
 * @author Božidar Grgur Drmić
 *
 */
public class SearchUtil {

	/**
	 * A BFS algorithm implementation for finding a solution for Slagalica.
	 * Ends up in never-ending loop if solution cannot be reached.
	 * @param s0 - supplier for initial state.s
	 * @param succ - A function for finding states reachable from some state.
	 * 				 Returns list of Transitions from current state to those state. 
	 * @param goal - a test which compares whether some state is equal to the goal state.
	 * @return Node whose state is the goal state. When parent nodes are followed they form a
	 * 		   step-by-step solution. Returns {@code null} if goal cannot be reached.
	 */
	public static <S> Node<S> bfs(Supplier<S> s0, Function<S,List<Transition<S>>> succ, Predicate<S> goal) {
		var toBeChecked = new LinkedList<Node<S>>();
		toBeChecked.add(new Node<S>(null, s0.get(), 0.0));
		
		while(!toBeChecked.isEmpty()) {
			var elem = toBeChecked.pop();
			
			if(goal.test(elem.getState())) {
				return elem;
			}
			
			for(var nextNode : succ.apply(elem.getState())) {
				toBeChecked.add(new Node<S>(elem, nextNode.getState(), elem.getCost() + nextNode.getCost()));				
			}
		}
		
		return null;
	}
	
	/**
	 * A BFS algorithm implementation for finding a solution for Slagalica
	 * which doesn't process same state more than once.
	 * @param s0 - supplier for initial state.s
	 * @param succ - A function for finding states reachable from some state.
	 * 				 Returns list of Transitions from current state to those state. 
	 * @param goal - a test which compares whether some state is equal to the goal state.
	 * @return Node whose state is the goal state. When parent nodes are followed they form a
	 * 		   step-by-step solution. Returns {@code null} if goal cannot be reached.
	 */
	public static <S> Node<S> bfsv(Supplier<S> s0, Function<S,List<Transition<S>>> succ, Predicate<S> goal) {
		var toBeChecked = new LinkedList<Node<S>>();
		Set<S> alreadyChecked = new HashSet<S>();
		toBeChecked.add(new Node<S>(null, s0.get(), 0.0));
		
		while(!toBeChecked.isEmpty()) {
			var elem = toBeChecked.pop();
			
			alreadyChecked.add(elem.getState());
			
			if(goal.test(elem.getState())) {
				return elem;
			}
			
			for(var nextNode : succ.apply(elem.getState())) {
				if(alreadyChecked.contains(nextNode.getState())) continue;
				toBeChecked.add(new Node<S>(elem, nextNode.getState(), elem.getCost() + nextNode.getCost()));			
			}
		}
		
		return null;
	}

}
