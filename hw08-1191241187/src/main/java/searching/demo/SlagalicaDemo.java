package searching.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import searching.algorithms.Node;
import searching.algorithms.SearchUtil;
import searching.slagalica.KonfiguracijaSlagalice;
import searching.slagalica.Slagalica;

/**
 * A demo program which prints the solution to Slagalica for some specific example.
 * @author Božidar Grgur Drmić
 *
 */
public class SlagalicaDemo {
	
	/**
	 * Main function of this class. Prints the solution to Slagalica for a specific example
	 * @param args - has no effect
	 */
	public static void main(String[] args) {		
		int[] initialConfig = {1,6,4,5,0,2,8,7,3};
		Slagalica slagalica = new Slagalica(new KonfiguracijaSlagalice(initialConfig));
		Node<KonfiguracijaSlagalice> rješenje = SearchUtil.bfsv(slagalica.s0, slagalica.succ, slagalica.goal);
		if(rješenje == null) {
			System.out.println("Nisam uspio pronaći rješenje.");
		} else {
			System.out.println("Imam rješenje. Broj poteza je: " + rješenje.getCost());
			List<KonfiguracijaSlagalice> lista = new ArrayList<>();
			Node<KonfiguracijaSlagalice> trenutni = rješenje;
			while(trenutni != null) {
				lista.add(trenutni.getState());
				trenutni = trenutni.getParent();
			}
			
			Collections.reverse(lista);
			lista.stream()
				.forEach(k -> {
						System.out.println(k);
						System.out.println();
					}
				);
		}
	}
	
}