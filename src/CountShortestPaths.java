import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Class to find shortest path count between 2 nodes
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
public class CountShortestPaths {

	int vertices;
	int edges;
	GraphNode[] nodes;
	int start;
	int end;

	/*
	 * get user input nodes and edge information
	 */
	public void getInput() {
		Scanner sc = new Scanner(System.in);
		vertices = Integer.parseInt(sc.next());
		edges = Integer.parseInt(sc.next());
		nodes = new GraphNode[vertices];

		// initialize graph nodes
		for (int i = 0; i < vertices; i++) {
			nodes[i] = new GraphNode(i);
		}

		start = Integer.parseInt(sc.next());
		end = Integer.parseInt(sc.next());
		// initialize neighbors
		for (int i = 0; i < edges; i++) {
			int v1 = Integer.parseInt(sc.next());
			int v2 = Integer.parseInt(sc.next());
			nodes[v1].addNeighbor(nodes[v2]);
			nodes[v2].addNeighbor(nodes[v1]);
		}
	}

	/*
	 *find shortest path count between 2 given nodes 
	 */
	public void findShortestPath() {
		Queue<GraphNode> Bfs = new LinkedList<GraphNode>();
		Bfs.add(nodes[start]);
		int shortestPath[] = new int[vertices];
		int shortestCount[] = new int[vertices];
		boolean[] visited = new boolean[vertices];

		int level=0;
		shortestPath[start] =0;
		shortestCount[start]=1;
		
		
		while (!Bfs.isEmpty()) {
			int count = Bfs.size();
			
			level++;
			while (count-- > 0) {
				GraphNode curr = Bfs.remove();
				//System.out.print(curr.label + " ");

				// add neighbors of current to BFS
				Iterator<GraphNode> neighborAccess = curr.getNeighborAccess();
				while (neighborAccess.hasNext()) {
					GraphNode n = neighborAccess.next();
					//if not visited add it to BFS
					if (!visited[n.label]) {
						Bfs.add(n);
						visited[n.label] = true;
						shortestPath[n.label]=level;
					}
					//If visited at same level, increase count
					if(	shortestPath[n.label] == level){
						shortestCount[n.label]+=shortestCount[curr.label];
					}	
				}
			}
			//System.out.println("");

		}
		/*
		for(int i=0;i<vertices;i++){
			System.out.print(shortestCount[i]+"  ");
		}
		*/
		System.out.println(shortestCount[end]);

	}

	public static void main(String[] args) {
		CountShortestPaths count = new CountShortestPaths();
		count.getInput();
		count.findShortestPath();
	}

}
