package datastr;

import java.util.Comparator;

public class ListEdgeComparator<V> implements Comparator<ListEdge<V>>{

	@Override
	public int compare(ListEdge<V> edge1, ListEdge<V> edge2) {
		return edge1.getWeight() - edge2.getWeight();
	}

}
