package searching.algorithms;

/**
 * A class which represents a node in a tree of finding a solution
 * to some problem.
 * @author Božidar Grgur Drmić
 *
 * @param <S> Possible solution type
 */
public class Node<S> {
	
	/**
	 * current state
	 */
	private S state;
	/**
	 * A parent Node which contains a state from which this state was directly reached
	 */
	private Node<S> parent;
	/**
	 * Total cost of performing operations in order to reach this state.
	 */
	private double cost;
	
	/**
	 * Constructor which accepts all the relevant data.
	 * @param parent - {@code parent} is set to this value.
	 * @param state - {@code state} is set to this value.
	 * @param cost - {@code cost} is set to this value.
	 */
	public Node(Node<S> parent, S state, double cost) {
		super();
		this.state = state;
		this.cost = cost;
		this.parent = parent;
	}

	/**
	 * A getter for state variable.
	 * @return {@code state}.
	 */
	public S getState() {
		return state;
	}

	/**
	 * A getter for cost variable.
	 * @return {@code cost}.
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * A getter for parent variable.
	 * @return {@code parent}.
	 */
	public Node<S> getParent() {
		return parent;
	}
	
}
