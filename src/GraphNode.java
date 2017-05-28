import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class GraphNode {
	int label;
	List<GraphNode> neighbor = new LinkedList<GraphNode>();
	
	GraphNode(int index){
		label=index;
		neighbor = new LinkedList<GraphNode>();
	}
	 void addNeighbor(GraphNode n){
			 neighbor.add(n);
	 }
	 
	 void printallNeigh(){
		 ListIterator<GraphNode> iter = neighbor.listIterator();
		 while(iter.hasNext()){
			 System.out.println(iter.next().label);
		 }
	 }
	 
	 Iterator<GraphNode> getNeighborAccess(){
		 return neighbor.listIterator();
	 }
}
