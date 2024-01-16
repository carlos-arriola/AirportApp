// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

import java.util.Iterator;

/** An implementation of the Graph ADT with edges having specified direction */
public class DirectedGraph<T> implements GraphInterface<T>{
	private DictionaryInterface<T, VertexInterface<T>> vertices;
	private int edgeCount;
	
	/** Constructs a new DirectedGraph, initializing the edgeCount and inner HashedDictionary*/
	public DirectedGraph() {
		vertices = new HashedDictionary<>(70);
		edgeCount = 0;
	}
	
	/** Adds a new vertex to the graph 
	@param vertexLabel The name for the vertex to be called by 
	@return addOutcome True if the add was successful */
	public boolean addVertex(T vertexLabel) {
		VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
		return addOutcome == null;
	}

	/** Adds a new edge path to the graph, with specified weight *
	@param begin The beginning vertex label 
	@param end The ending vertex label
	@param edgeWeight The weight of the path from begin to end 
	@return result True if the connection between vertices was successful */
	public boolean addEdge(T begin, T end, double edgeWeight) {
		boolean result = false;
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		if ( (beginVertex != null) && (endVertex != null) )
			result = beginVertex.connect(endVertex, edgeWeight);
		if (result)
			edgeCount++;
		return result;
	}

	/** Adds an edge path with an unspecified weight 
	@param begin The beginning vertex label 
	@param end The ending vertex label 
	@return True if the connection between vertices was successful */
	public boolean addEdge(T begin, T end) {
		return addEdge(begin, end, 0);	
	}

	/** Checks if there exists an edge between two vertices, going in one direction 
	@param begin The beginning vertex label 
	@param end The ending vertex label 
	@return found True if an edge has been found from begin to end */
	public boolean hasEdge(T begin, T end) {
		boolean found = false;
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		if ( (beginVertex != null) && (endVertex != null) )
		{
			Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
			while (!found && neighbors.hasNext())
			{
				VertexInterface<T> nextNeighbor = neighbors.next();
				if (endVertex.equals(nextNeighbor))
					found = true;
			}
		}
		return found;
	}

	/** Checks if the graph is currently empty 
	@return True if the graph is empty*/
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	/** Returns the number of vertices in the graph
	@return The number of vertices in the graph */
	public int getNumberOfVertices() {
		return vertices.getSize();
	}

	/** Returns the number of edges in the graph 
	@return The number of edges in the graph*/
	public int getNumberOfEdges() {
		return edgeCount;
	}

	/** Removes all vertices and their paths from the graph */
	public void clear() {
		vertices.clear();
		edgeCount = 0;
	}

	/** UNIMPLEMENTED: Returns a queue of the breadth first traversal of the graph 
	@param origin The vertex label to begin traversal from 
	@return null Method is unimplemented */
	public QueueInterface<T> getBreadthFirstTraversal(T origin) {
	/*	resetVertices();
		QueueInterface<T> traversalOrder = new ArrayQueue<>();
		QueueInterface<VertexInterface<T>> vertexQueue = new ArrayQueue<>();
		VertexInterface<T> originVertex = vertices.getValue(origin);
		originVertex.visit();
		traversalOrder.enqueue(origin);
		vertexQueue.enqueue(originVertex);
		while (!vertexQueue.isEmpty())
		{
			VertexInterface<T> frontVertex = vertexQueue.dequeue();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while (neighbors.hasNext())
			{
				VertexInterface<T> nextNeighbor = neighbors.next();
				if (!nextNeighbor.isVisited())
				{
					nextNeighbor.visit();
					traversalOrder.enqueue(nextNeighbor.getLabel());
					vertexQueue.enqueue(nextNeighbor);
				}
			}
		}
		return traversalOrder; */
		return null;
		}

	/** UNIMPLEMENTED: Returns a queue of the depth first traversal of the graph 
	@param origin The vertex label to begin traversal from 
	@return null Method is unimplemented */
	public QueueInterface<T> getDepthFirstTraversal(T origin) {
		return null;
	}

	/** UNIMPLEMENTED: Returns a stack of the topological order of the graph 
	@return null Method is unimplemented */
	public StackInterface<T> getTopologicalOrder() {
		return null;
	}

	/** UNIMPLEMENTED: Returns the shortest path of specified ends, does not account for weights
	@param begin The vertex label to begin from
	@param end The vertex label to end at
	@param path A stack that will be filled with the path from begin to end
	@return 0 Method is unimplemented */
	public int getShortestPath(T begin, T end, StackInterface<T> path) {
	/*		resetVertices();
			boolean done = false;
			QueueInterface<VertexInterface<T>> vertexQueue = new ArrayQueue<>();
			VertexInterface<T> originVertex = vertices.getValue(begin);
			VertexInterface<T> endVertex = vertices.getValue(end);
			originVertex.visit();
			vertexQueue.enqueue(originVertex);
			while (!done && !vertexQueue.isEmpty())
			{
				VertexInterface<T> frontVertex = vertexQueue.dequeue();
				Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
				while (!done && neighbors.hasNext())
				{
					VertexInterface<T> nextNeighbor = neighbors.next();
					if (!nextNeighbor.isVisited())
					{
						nextNeighbor.visit();
						nextNeighbor.setCost(1 + frontVertex.getCost());
						nextNeighbor.setPredecessor(frontVertex);
						vertexQueue.enqueue(nextNeighbor);
					}
					if (nextNeighbor.equals(endVertex))
						done = true;
				}
			}
			int pathLength = (int)endVertex.getCost();
			path.push(endVertex.getLabel());
			VertexInterface<T> vertex = endVertex;
			while (vertex.hasPredecessor())
			{
				vertex = vertex.getPredecessor();
				path.push(vertex.getLabel());
			}
			return pathLength; */
		return 0;
	}

	/** Returns the cheapest path of specified ends, accounts for weights
	@param begin The vertex label to begin from
	@param end The vertex label to end at
	@param path A stack that will be filled with the path from begin to end
	@return pathCost The cheapest cost of traversing to end from begin */
	public double getCheapestPath(T begin, T end, StackInterface<T> path) 
	{
		resetVertices();
		boolean done = false;
		PriorityQueueInterface<EntryPQ> pq = new HeapPriorityQueue<>();
		pq.add(new EntryPQ(vertices.getValue(begin), 0, null));
		while (!done && !pq.isEmpty())
		{
			EntryPQ frontEntry = pq.remove();
			VertexInterface<T> frontVertex = frontEntry.getVertex();
			
			if (!frontVertex.isVisited())
			{
				frontVertex.visit();
				frontVertex.setCost(frontEntry.getPathLength());
				frontVertex.setPredecessor(frontEntry.getPredecessor());
				if (frontVertex.getLabel().equals(end))
				{
					done = true;
				}
				else
				{
					Iterator<VertexInterface<T>> n = frontVertex.getNeighborIterator();
					Iterator<Double> w = frontVertex.getWeightIterator();
					while (n.hasNext())
					{
						VertexInterface<T> nextNeighbor = n.next();
						double weightOfEdgeToNeighbor = w.next();
						
						if (!nextNeighbor.isVisited())
						{
							double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
							pq.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
						}
					}
				}		
			}
		}
	double pathCost = vertices.getValue(end).getCost();
	VertexInterface<T> vertex = vertices.getValue(end);
	path.push(vertex.getLabel());
	while (vertex.hasPredecessor())
	{
		vertex = vertex.getPredecessor();
		path.push(vertex.getLabel());
	}
	return pathCost;
	}
	
	/** A private class that allows vertices to be compared */
	private class EntryPQ implements Comparable<EntryPQ>
	{
		VertexInterface<T> vertex;
		VertexInterface<T> predecessor;
		double pathLength;
		
		/** Constructs an EntryPQ object 
		@param vertex The main vertex
		@param pathLength The specified length to this main vertex 
		@param predecessor The vertex that preceds the main vertex */
		public EntryPQ(VertexInterface<T> vertex, double pathLength, VertexInterface<T> predecessor) {
			this.vertex = vertex;
			this.pathLength = pathLength;
			this.predecessor = predecessor;
		}

		/** Returns the main vertex 
		@return vertex The main vertex */
		public VertexInterface<T> getVertex() {
			return vertex;
		}
		
		/** Returns the preceding vertex
		@return predecssor The preceding vertex*/
		public VertexInterface<T> getPredecessor() {
			return predecessor;
		}
		
		/** Returns the path length to the main vertex
		@return pathLength The path length to the main vertex */
		public double getPathLength() {
			return pathLength;
		}
		
		@Override
		/** Compares two EntryPQs
		@param o A distinct EntryPQ 
		@return An integer value that corresponds with the comparison made */
		public int compareTo(DirectedGraph<T>.EntryPQ o) 
		{
			if (this.pathLength < o.pathLength)
			{
				return -1;
			}
			else if (this.pathLength > o.pathLength)
			{
				return 1;
			}
			else // Assert this.pathLength == o.pathLength
				return 0;
			}
		}
	
	/** Resets the visits, costs, and predecessors of every vertice in the graph */
	protected void resetVertices()
	{
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		while (vertexIterator.hasNext())
		{
			VertexInterface<T> nextVertex = vertexIterator.next();
			nextVertex.unvisit();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		}
	}
}
