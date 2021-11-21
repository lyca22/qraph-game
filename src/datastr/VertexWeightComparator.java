package datastr;

import java.util.Comparator;

public class VertexWeightComparator<V> implements Comparator<Vertex<V>> {

	@Override
	public int compare(Vertex<V> vertex1, Vertex<V> vertex2) {
		if(vertex1.getWeightFromPoint() == vertex2.getWeightFromPoint()) {
			return 0;
		}else if(vertex1.getWeightFromPoint() > vertex2.getWeightFromPoint()){
			return 1;
		}else {
			return -1;
		}
	}

}
