import edu.princeton.cs.algs4.EdgeWeightedGraph; 
import edu.princeton.cs.algs4.Edge;
public class CatGame{
	
	EdgeWeightedGraph board; 
	boolean[] marked; 
	int size; 
	
	public CatGame(int n){
		size = n; 
		//new graph of size n*n 
		board = new EdgeWeightedGraph(n * n + 1); 
		marked = new boolean[n * n + 1]; 
		
		for (int row = 1; row < n-1; row++){
			for (int col = 1; col < n -1; col++){
				
			}
		}
	}
	
	void markTile(int row, int col){
		marked[index(row,col)] = true; 
	}
	
	//boolean marked(int row, int col); 
	//int[] getCatTile(); 
	//boolean catHasEscaped(); 
	//boolean catIsTrapped(); 
	public int index(int row, int col){
		return row * size + col
	}
}
