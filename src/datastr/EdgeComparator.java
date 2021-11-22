package datastr;

import java.util.Comparator;

public class EdgeComparator<V> implements Comparator<Edge<V>>{

	@Override
	public int compare(Edge<V> edge1, Edge<V> edge2) {
		return edge1.getWeight() - edge2.getWeight();
	}

}
