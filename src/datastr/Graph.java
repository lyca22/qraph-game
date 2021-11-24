package datastr;

import java.util.ArrayList;

public abstract class Graph<V> implements IGraph<V> {

	private ArrayList<ArrayList<Integer>> minimumWeightPaths;

	public Graph() {
		setMinimumWeightPaths(new ArrayList<ArrayList<Integer>>());
	}

	public ArrayList<ArrayList<Integer>> getMinimumWeightPaths() {
		return minimumWeightPaths;
	}

	public void setMinimumWeightPaths(ArrayList<ArrayList<Integer>> minimumWeightPaths) {
		this.minimumWeightPaths = minimumWeightPaths;
	}

}
