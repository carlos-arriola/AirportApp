// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** Implementation of the Vertex ADT */
public class Vertex<T> implements VertexInterface<T>
  {
     private T label;
     private List<Edge> edgeList;						// Edges to neighbors
     private boolean visited;							// True if visited
     private VertexInterface<T> previousVertex;      	// On path to this vertex
     private double cost;                              	// Of path to this vertex
  
     /** Constructs a new vertex with a specified name 
     @param vertexLabel The name of the vertex */
     public Vertex(T vertexLabel) {
        label = vertexLabel;
        edgeList = new ArrayList<>();
        visited = false;
        previousVertex = null;
        cost = 0;
     }
  
	/** Gets this vertex's label.
	@return  The object that labels the vertex. */
	public T getLabel() {
		return label;
	}

	/** Marks this vertex as visited. */
	public void visit() {
		visited = true;
	}

	/** Removes this vertex's visited mark. */
	public void unvisit() {
		visited = false;
	}

	/** Sees whether this vertex is marked as visited.
	@return  True if the vertex is visited. */
	public boolean isVisited() {
		return visited;
	}

	/** Connects this vertex and a given vertex with a weighted edge. 
	The two vertices cannot be the same, and must not already
	have this edge between them. In a directed graph, the edge
	points toward the given vertex.
	@param endVertex   A vertex in the graph that ends the edge. 
	@param edgeWeight  A real-valued edge weight, if any.
	@return  True if the edge is added, or false if not. */ 
	public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
	{
		boolean result = false;
	    if (!this.equals(endVertex))
	    {
	       Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
	       boolean duplicateEdge = false;
	       while (!duplicateEdge && neighbors.hasNext())
	       {
	          VertexInterface<T> nextNeighbor = neighbors.next();
	          if (endVertex.equals(nextNeighbor))
	        	  duplicateEdge = true;
	       }
	       if (!duplicateEdge)
	       {
	           edgeList.add(new Edge(endVertex, edgeWeight));
	       result = true;
	       }
	    }
	    return result;
	}


	/** Connects this vertex and a given vertex with an unweighted edge.
	The two vertices cannot be the same, and must not already
	have this edge between them. In a directed graph, the edge 
	points toward the given vertex.
	@param endVertex   A vertex in the graph that ends the edge.
	         @return  True if the edge is added, or false if not. */
	public boolean connect(VertexInterface<T> endVertex) {
		return connect(endVertex, 0);
	}

	/** Creates an iterator of this vertex's neighbors by following 
	all edges that begin at this vertex.
	@return  An iterator of the neighboring vertices of this vertex. */
	public Iterator<VertexInterface<T>> getNeighborIterator() {
		   return new NeighborIterator();
	}

	/** Creates an iterator of the weights of the edges to this
	vertex's neighbors. 
	@return  An iterator of edge weights for edges to neighbors of this 
	         vertex. */ 
	public Iterator<Double> getWeightIterator() {
		return new WeightIterator();
	}

	/** Sees whether this vertex has at least one neighbor. 
	@return  True if the vertex has a neighbor. */
	public boolean hasNeighbor() {
		return !edgeList.isEmpty();
	}

	/** Gets an unvisited neighbor, if any, of this vertex. 
	@return  Either a vertex that is an unvisited neighbor or null 
	         if no such neighbor exists. */
	public VertexInterface<T> getUnvisitedNeighbor()
	{
		VertexInterface<T> result = null;
	   Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
	   while ( neighbors.hasNext() && (result == null) )
	   {
		   VertexInterface<T> nextNeighbor = neighbors.next();
		   if (!nextNeighbor.isVisited())
			   result = nextNeighbor;
	   }   
	   return result;
	}
	
	/** Records the previous vertex on a path to this vertex.
	@param predecessor  The vertex previous to this one along a path. */ 
	public void setPredecessor(VertexInterface<T> predecessor) {
		previousVertex = predecessor;
	}
	
	/** Gets the recorded predecessor of this vertex.
	@return  Either this vertex's predecessor or null if no predecessor
	         was recorded. */
	public VertexInterface<T> getPredecessor() {
		return previousVertex;
	}
	
	/** Sees whether a predecessor was recorded for this vertex.
	@return  True if a predecessor was recorded. */
	public boolean hasPredecessor() {
		return previousVertex != null;
	}
	
	/** Records the cost of a path to this vertex.
	@param newCost  The cost of the path. */
	public void setCost(double newCost) {
		cost = newCost;
	}
	
	/** Gets the recorded cost of the path to this vertex.
	@return  The cost of the path. */
	public double getCost() {
		return cost;
	}
  
	/** Inner class edge that connects vertices */
     protected class Edge  {
    	 private VertexInterface<T> vertex; // Vertex at end of edge
	     private double weight;
	  
	     /** Constructs a new edge between the vertex it is called upon, a specified endVertex, and with
	     	 a specified edge weight 
	     @param endVertex The specified vertex for the connection to end at 
	     @param edgeWeight The specified weight of the path from vertex to vertex */
	     protected Edge(VertexInterface<T> endVertex, double edgeWeight) {
	        vertex = endVertex;
	        weight = edgeWeight;
	     }
	
	     /** Constructs a new edge between the vertex it is called upon and a specified endVertex
     	 @param endVertex The specified vertex for the connection to end at */
	     protected Edge(VertexInterface<T> endVertex) {
	        vertex = endVertex;
	        weight = 0;
	     }
	
	     /** Returns the end vertex of the edge 
	     @return vertex The end vertex */
	     protected VertexInterface<T> getEndVertex() {
	        return vertex;
	     }
		
	     /** Returns the weight of the edge
	     @return weight The weight of the edge */
	     protected double getWeight() {
	        return weight;
	     }
     }
     
     /** Inner class for iterating the neighbors of the vertex */
     private class NeighborIterator implements Iterator<VertexInterface<T>>
       {
          private Iterator<Edge> edges;
       
          /** Constructs a new NeighborIterator*/
          private NeighborIterator() {
             edges = edgeList.listIterator();
          }
       
         /** Checks if there is another neighbor to iterate 
         @return True if there is another neighbor to iterate */
         public boolean hasNext() {
            return edges.hasNext();
         }
      
         /** Returns the next neighbor of the vertex 
         @return nextNeighbor The next neighbor of the vertex, 
         		 or NoSuchElementException if it does not exist */
         public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext())
            {
               Edge edgeToNextNeighbor = edges.next();
               nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
               throw new NoSuchElementException();
            return nextNeighbor;
         }
    
         /** Unsupported remove function */
         public void remove() {
            throw new UnsupportedOperationException();
         }
      }
     
     /** Inner class for iterating the weights of the edges of the vertex */
     private class WeightIterator implements Iterator<Double>
     {
    	 private Iterator<Edge> edges;
     
         /** Constructs a new WeightIterator*/
    	 private WeightIterator() {
    		 edges = edgeList.listIterator();
    	 }
     
    	 /** Checks if there is another neighbor to iterate 
         @return True if there is another neighbor to iterate */
    	 public boolean hasNext() {
    		 return edges.hasNext();
    	 }
    
    	 /** Returns the weight of the next edge of the vertex 
         @return nextNeighborWeight The next neighbor weight of the vertex, 
         		 or NoSuchElementException if it does not exist */    	 
    	 public Double next() {
	          double nextNeighborWeight = 0;
	          if (edges.hasNext())
	          {
	             Edge edgeToNextNeighbor = edges.next();
	             nextNeighborWeight = edgeToNextNeighbor.getWeight();
	          }
	          else
	             throw new NoSuchElementException();
	          return nextNeighborWeight;
    	 }
  
       /** Unsupported remove function */
       public void remove() {
          throw new UnsupportedOperationException();
       }
    }
  }