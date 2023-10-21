import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collection;
import java.util.Collections;

public class Graph {
    private HashMap<Integer, LinkedList<Integer>> adjacency;

    public Graph() {
        adjacency = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacency.put(vertex, new LinkedList<>());
    }

    public void addEdge(int source, int destination) {
        adjacency.get(source).add(destination);
        adjacency.get(destination).add(source);
    }

    public LinkedList<Integer> getNeighbors(int vertex) {
        return adjacency.get(vertex);
    }

    public LinkedList<Integer> getAllVertices() {
        return new LinkedList<>(adjacency.keySet());
    }
    
    public LinkedList<Integer> BFS(int source, int end) {
    	int[] parent = new int[this.vertexNumber()];
    	boolean[] visited = new boolean[this.vertexNumber()];
    	int[] distance = new int[this.vertexNumber()];
    	Arrays.fill(distance, 1000);
    	
    	Queue<Integer> queue = new LinkedList<>();
    	queue.add(source);
    	parent[source] = source;
    	visited[source] = true;
    	distance[source] = 0;
    	
    	while (!queue.isEmpty()) {
    		int current = queue.poll();
    		LinkedList<Integer> randomNeighbors = new LinkedList<>(this.getNeighbors(current));
    		Collections.shuffle(randomNeighbors);
    		for (int neighbor:randomNeighbors) {
    			if (!visited[neighbor]) {
    				visited[neighbor] = true;
    				queue.add(neighbor);
    				distance[neighbor] = distance[current] + 1;
    				parent[neighbor] = current;
    			}
    		}
    	}
    	
    	LinkedList<Integer> path = new LinkedList<>();
    	int current = end;
    	path.addFirst(current);
    	while (true) {
    		if (parent[current] != source) {
    			current = parent[current];
    			path.addFirst(current);
    		}
    		else {
    			break;
    		}
    	}
    	return path;
    }
    
    public int vertexNumber() {
    	return this.getAllVertices().size();
    }

    public void print() {
        for (int i : this.getAllVertices()) {
            System.out.println(adjacency.get(i));
        }
    }
    
}
