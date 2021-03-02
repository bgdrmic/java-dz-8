package coloring.demo;

import java.util.Arrays;

import coloring.algorithms.*;
import marcupic.opjj.statespace.coloring.*;

/**
 * A demo program for coloring some picture. 
 * @author Božidar Grgur Drmić.
 *
 */
public class Bojanje2 {

	/**
	 * Main function of this class which runs a console for
	 * coloring a picture of an owl.
	 * @param args - has no effect.
	 */
	public static void main(String[] args) {
		FillApp.run(FillApp.OWL, Arrays.asList(bfs, dfs, bfsv));
	}
	
	/**
	 * A BFS-based algorithm for coloring a picture.
	 */
	private static final FillAlgorithm bfs = new FillAlgorithm() {
		@Override
		public String getAlgorithmTitle() {
			return "Moj bfs!";
		}
		
		@Override
		public void fill(int x, int y, int color, Picture picture) {
			Coloring col = new Coloring(new Pixel(x,y), picture, color);
			SubspaceExploreUtil.bfs(col.s0, col.process, col.succ, col.acceptable);
		}
	};
	
	/**
	 * A DFS-based algorithm for coloring a picture.
	 */
	private static final FillAlgorithm dfs = new FillAlgorithm() {
		@Override
		public String getAlgorithmTitle() {
			return "Moj dfs!";
		}
		
		@Override
		public void fill(int x, int y, int color, Picture picture) {
			Coloring col = new Coloring(new Pixel(x,y), picture, color);
			SubspaceExploreUtil.dfs(col.s0, col.process, col.succ, col.acceptable);
		}	
	};
	
	/**
	 * A BFS-based algorithm for coloring a picture which never
	 * visits the same pixel twice.
	 */
	private static final FillAlgorithm bfsv = new FillAlgorithm() {
		@Override
		public String getAlgorithmTitle() {
			return "Moj bfsv!";
		}
		
		@Override
		public void fill(int x, int y, int color, Picture picture) {
			Coloring col = new Coloring(new Pixel(x,y), picture, color);
			SubspaceExploreUtil.bfsv(col.s0, col.process, col.succ, col.acceptable);
		}
	};
	
}
