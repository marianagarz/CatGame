import edu.princeton.cs.algs4.Edge;

public class CatEdge extends Edge{
	
	private double weight; 
	
	public CatEdge(int v, int w, double weight){
		super(v, w, weight);
	}
	public void changeWeight(){
		weight = Double.POSITIVE_INFINITY;
	}
	
	public double weight(){
		return weight;
	}
}