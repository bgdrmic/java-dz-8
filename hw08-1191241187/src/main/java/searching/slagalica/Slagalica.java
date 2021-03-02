package searching.slagalica;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import searching.algorithms.Transition;

/**
 * A class which represents a simple puzzle.
 * @author Božidar Grgur Drmić
 *
 */
public class Slagalica {

	/**
	 * Cost of each move.
	 */
	private final double COST = 1.0;
	/**
	 * Goal state of puzzle.
	 */
	private final KonfiguracijaSlagalice SOLUTION = new KonfiguracijaSlagalice(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 0});
	
	/**
	 * current configuration of puzzle.
	 */
	private KonfiguracijaSlagalice config;

	/**
	 * Constructor which accepts initial configuration as parameter.
	 * @param config - initial configuration
	 */
	public Slagalica(KonfiguracijaSlagalice config) {
		super();
		this.config = config;
	}
	
	/**
	 * A supplier for current configuration.
	 */
	public Supplier<KonfiguracijaSlagalice> s0 = () -> { return config; };
	
	/**
	 * A function which accepts some configuration and returns a list
	 * of Transitions which can directly be made in one step.
	 */
	public Function<KonfiguracijaSlagalice, List<Transition<KonfiguracijaSlagalice>>> succ = konfiguracija -> {
		LinkedList<Transition<KonfiguracijaSlagalice>> list = new LinkedList<>();
		
		int i = konfiguracija.indexOfSpace();
		if(i < 6) {
			list.add(new Transition<KonfiguracijaSlagalice>(konfiguracija.zamjeni(i, i+3), COST));
		}
		if(i > 2) {
			list.add(new Transition<KonfiguracijaSlagalice>(konfiguracija.zamjeni(i, i-3), COST));
		}
		if(i%3 > 0) {
			list.add(new Transition<KonfiguracijaSlagalice>(konfiguracija.zamjeni(i, i-1), COST));
		}
		if(i%3 < 2) {
			list.add(new Transition<KonfiguracijaSlagalice>(konfiguracija.zamjeni(i, i+1), COST));
		}
		
		return list;
	};
	
	/**
	 * A tester which checks whether for configuration is a solution.
	 */
	public Predicate<KonfiguracijaSlagalice> goal = konfiguracija -> {
		return konfiguracija.equals(SOLUTION);
	};

	@Override
	public String toString() {
		return config.toString();
	}	
}
