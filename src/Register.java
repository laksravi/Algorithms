import java.util.List;

/**
 * Edge in the n/w flow graph
 * @author Lakshmi Ravi
 *@author Aarti Gorade
 */
class Edge{
	int weight;
	NetworkNode dest;
	
	Edge(int wt, NetworkNode d){
		weight = wt;
		dest = d;
	}
}

/**
 * A node in the n/w flow graph
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
class NetworkNode{
	String label;
	List<Edge> neighs;
	NetworkNode(String label){
		this.label = label;
	}
	public void setEdges(int wt, NetworkNode dest){
		Edge e = new Edge(wt, dest);
	}
}


public class Register {
	
	int students;
	int courses;
	int currentMinCapacity;
	public Edge[] Bfs(NetworkNode Start, NetworkNode end){
		return null;
	}
	public void findPath(){
		//Do DFS form S to t and get edge-list and min-Capacity left
		//for every edge 
		     //update the flow for f.w edges and b.w edges
		     //add backward edge for the edges
	}
	
	public void getInput(){
		
		//create s student nodes
		//create c course nodes
		// create 3 weight edge from each of s student nodes
		//for every student : read line, assign weight 1 edge from 
		
	}
}
