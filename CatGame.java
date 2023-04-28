import edu.princeton.cs.algs4.EdgeWeightedGraph; 
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import java.util.Random;
import edu.princeton.cs.algs4.Stack; 

public class CatGame{
	
	EdgeWeightedGraph board; 
	DijkstraUndirectedSP shortestPath; 
	
	boolean[] marked; 
	int size; 
	int catPosition;
	int freedom;
	
	public CatGame(int n){
		size = n; 
		freedom = n * n; 
		//new graph of size n*n 
		board = new EdgeWeightedGraph(n * n + 1); 
		marked = new boolean[n * n + 1]; 
		catPosition = index(n / 2, n / 2);
		boolean[][] hasEdge = new boolean[n][n];
		
		//makes edges for interior squares
		for (int row = 1; row < n-1; row++){
			for (int col = 1; col < n -1; col++){
				int  v = index(row, col);
				board.addEdge(new CatEdge(v, v-1));
				board.addEdge(new CatEdge(v, v+1));
				board.addEdge(new CatEdge(v, v - n - 1));
				board.addEdge(new CatEdge(v, v - n));
				board.addEdge(new CatEdge(v, v + n));
				board.addEdge(new CatEdge(v, v + n - 1));
			}
		}
		
		//makes edges for squares bordering freedom
		for (int i = 0; i < n; i ++){
			int v; 
			//top row:
			v = index(0 , i); 
			board.addEdge(new CatEdge(v, freedom)); 
			
			//bottom row 
			v = index((n-1), i);
			board.addEdge(new CatEdge(v, freedom)); 
			
			//left col
			v = index(i, 0);
			board.addEdge(new CatEdge(v, freedom)); 
			
			//right col 
			v = index(i, (n-1)); 
			board.addEdge(new CatEdge(v, freedom)); 
		}
		
		//Random rand = new Random();
		//int num = rand.nextInt(n / 2);
		//while(num > 0){
			//int row = rand.nextInt(n * n); 
			//int col = rand.nextInt(n * n); 
			//while (marked(row, col)){												NOTE TO SELF: FIX RANDOM !!!!
				//row = rand.nextInt(n * n);
				//col = rand.nextInt(n * n);
			//}
			//markTile(row, col);
			//num--; 
		//}
		
	}
	
	void markTile(int row, int col){
		marked[index(row,col)] = true;
		int v = index(row, col);
		
		for(Edge i : board.adj(v)){
			CatEdge e = (CatEdge) i;
			e.changeWeight(); 
		}
		
		shortestPath = new DijkstraUndirectedSP(board, catPosition); 
		
		Stack stack = (Stack) shortestPath.pathTo(freedom); 
		CatEdge nextMove = (CatEdge) stack.pop();
		catPosition = nextMove.other(catPosition);
		
	}
	
	boolean marked(int row, int col){
		return marked[index(row, col)];
	}
	
	int[] getCatTile(){
		int col = catPosition % size; 
		int row = catPosition / size;
		
		int[] result = {row, col};
		return result;
		
	}
	
	boolean catHasEscaped(){
		return catPosition == freedom; 
	}
	
	boolean catIsTrapped(){
		for(Edge i : board.adj(catPosition)){
			CatEdge e = (CatEdge) i;
			if (e.weight() == 1)
				return false; 
		}
		return false;//true;			NOTE FIX THIS
	}
	
	
	public int index(int row, int col){
		return row * (size) + col;
	}
	
}
