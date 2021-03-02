package searching.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import searching.algorithms.Node;
import searching.algorithms.SearchUtil;
import searching.slagalica.KonfiguracijaSlagalice;
import searching.slagalica.Slagalica;
import searching.slagalica.gui.SlagalicaViewer;


/**
 * A demo program which prints the solution to Slagalica
 * for some specific example entered through command line on call.
 * @author Božidar Grgur Drmić
 *
 */
public class SlagalicaMain {

	
	/**
	 * Main function of this class. Prints the solution to Slagalica for a specific example.
	 * @param args - initial configuration.
	 */
	public static void main(String[] args) {
		
		if(args.length != 1 || args[0].length() != 9) {
			System.out.println("Wrong input format");
			return;
		}

		int konfig[] = new int[9];

		for(int i = 0; i < 9; i++) {
			konfig[i] = args[0].charAt(i) - '0';
			
			if(!args[0].contains(String.valueOf(i))) {
				System.out.println("Wrong input format");
				return;
			}
		}
		Slagalica slagalica = new Slagalica(new KonfiguracijaSlagalice(konfig));
		Node<KonfiguracijaSlagalice> rješenje = SearchUtil.bfsv(slagalica.s0, slagalica.succ, slagalica.goal);
		if(rješenje == null) {
			System.out.println("Nisam uspio pronaći rješenje.");
		} else {
			SlagalicaViewer.display(rješenje);
			System.out.println("Imam rješenje. Broj poteza je: " + rješenje.getCost());
			List<KonfiguracijaSlagalice> lista = new ArrayList<>();
			Node<KonfiguracijaSlagalice> trenutni = rješenje;
			while(trenutni != null) {
				lista.add(trenutni.getState());
				trenutni = trenutni.getParent();
			}
			
			Collections.reverse(lista);
			lista.stream()
				.forEach(
					k -> {
						System.out.println(k);
						System.out.println();
					}
				);
		}
	}
	
}
