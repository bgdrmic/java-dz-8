package searching.algorithms;

/**
 * A class which represents a transition from one state to some
 * other state which can be reached from the first state.
 * @author Božidar Grgur Drmić
 *
 * @param <S> type for states.
 */
public class Transition<S> {
	
	/**
 	 * new state. 
	 */
	private S state;
	/** 
	 * cost of this change.
	 */
	private double cost;
	
	/**
	 * Constructor which accepts all the relevant data.
	 * @param state - state to which the transition is made.
	 * @param cost - price of this transition.
	 */
	public Transition(S state, double cost) {
		super();
		this.cost = cost;
		this.state = state;
	}
	
	/**
	 * A getter for cost variable
	 * @return {@code cost}
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * A getter for state variable
	 * @return {@code state}
	 */
	public S getState() {
		return state;
	}
	
}
