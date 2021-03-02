package searching.slagalica;

import java.util.Arrays;

/**
 * A class which represents a configuration of Slagalica.
 * @author Božidar Grgur Drmić
 *
 */
public class KonfiguracijaSlagalice {

	/**
	 * configuration.
	 */
	private int polje[];

	/**
	 * Constructor which accepts initial configuration as argument.s
	 * @param polje - initial configuration
	 */
	public KonfiguracijaSlagalice(int[] polje) {
		super();
		this.polje = polje;
	}

	/**
	 * A getter for polje variable.
	 * @return {@code polje}
	 */
	public int[] getPolje() {
		return polje;
	}
	
	/**
	 * A getter for index of space field in current cofiguration.
	 * @return index of space.
	 */
	public int indexOfSpace() {
		for(int i = 0; i < 9; i++) {
			if(polje[i] == 0) return i;
		}
		
		return -1;
	}
	
	/**
	 * A method which creates a new field identical to the current one except that
	 * two fields are swaped. 
	 * @param i1 - index of first field
	 * @param i2 - index of second field
	 * @return new configuration.
	 */
	public KonfiguracijaSlagalice zamjeni(int i1, int i2) {
		var konfig = new KonfiguracijaSlagalice(Arrays.copyOf(polje, 9));
		int a = konfig.polje[i1];
		konfig.polje[i1] = konfig.polje[i2];
		konfig.polje[i2] = a;
		return konfig;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(polje);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof KonfiguracijaSlagalice))
			return false;
		KonfiguracijaSlagalice other = (KonfiguracijaSlagalice) obj;
		return Arrays.equals(polje, other.polje);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 9; i++) {
			if(i == 0) {
				sb.append(polje[i]);
			} else if(i % 3 == 0) {
				sb.append("\n");
				sb.append(polje[i]);
			} else {
				sb.append(" ");
				sb.append(polje[i]);
			}
		}
		return sb.toString().replace('0', '*');
	}
	
}
