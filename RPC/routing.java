import java.util.*;
import java.io.*;

class routing {
	static class IntegerPair implements Comparable{
		Integer _first, _second;
		
		public IntegerPair (Integer f, Integer s){
			_first = f;
			_second = s;
		}
		public int compareTo(Object o){
			if (this.first() != ((IntegerPair)o).first())
				return this.first()-((IntegerPair)o).first();
			 else
				return this.second()-((IntegerPair)o).second();
		}
		Integer first() {return _first;}
		Integer second() {return _second;}
	}
	
	public static final int INF = 1000000000;
	private static Vector <Vector <IntegerPair>> AdjList = new Vector <Vector <IntegerPair>>();
	
	public static void main(String[] args) throws IOException{
		int nVertices, nEdges, initialNode, goalNode; //Dual graph
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String line = br.readLine();
		nEdges = Integer.parseInt(line); //number of servers, edges on dual graph
		for (int i=0; i<nEdges; i++){ 
			line = br.readLine();
			String [] split = line.split(" ");
			int m = Integer.parseInt(split[0]); //number of outgoing connections, m*n for each server = nVertices on dual graph
				nVertices += m;
			int t = Integer.parseInt(split[1]); //weight of edge on dual graph
			for (int j=0; j<m; j++){
				line = br.readLine();
				String [] split2 = line.split(" ");
				int s = Integer.parseInt(split2[0]); //number of edges on the dual graph to be removed
					nVertices -= s; //Is this correct?
				int x = Integer.parseInt(split2[1]); //destination
				AdjList.get(nEdges).add(new IntegerPair(x, t));
				//Should a conditional go here?
					int [] distinct = new int [s]; //key to remove edges on the dual graph
					for (int k=0; k<s; k++){
						distinct [k] = Integer.parseInt(split2[2+k]);
					}
				
			}
		}
		
		//Where should this code block go?
		AdjList.clear();
		for (int i=0; i<nVertices; i++){
			Vector <IntegerPair> Neighbor = 
				new Vector <IntegerPair>();
			AdjList.add(Neighbor);
		}
		
		    // Dijkstra routine
		    Vector < Integer > dist = new Vector < Integer > ();
		    dist.addAll(Collections.nCopies(nVertices, INF)); dist.set(s, 0); // INF = 1*10^9 not MAX_INT to avoid overflow
		    PriorityQueue < IntegerPair > pq = new PriorityQueue < IntegerPair >(1, 
		      new Comparator< IntegerPair >() { // overriding the compare method 
				System.out.println(IntegerPair i);
		        public int compare(IntegerPair i, IntegerPair j) {
					int subtraction = i.first() - j.first();
					System.out.println("subtraction: " + subtraction);
		          return subtraction;
		        }
		      }
		    );
		    pq.offer(new IntegerPair (0, s)); // sort based on increasing distance
		
		    while (!pq.isEmpty()) { // main loop, change var names for the ones of the dual graph
		      IntegerPair top = pq.poll(); // greedy: pick shortest unvisited vertex
		      int d = top.first(); u = top.second();
		      if (d > dist.get(u)) continue; // This check is important! We want to process vertex u only once but we can
		      Iterator it = AdjList.get(u).iterator();
		      while (it.hasNext()) { // all outgoing edges from u
		        IntegerPair p = (IntegerPair) it.next();
		        v = p.first();
		        int weight_u_v = p.second();
		        if (dist.get(u) + weight_u_v < dist.get(v)) { // if can relax      (note: Record SP spanning tree)
		          dist.set(v, dist.get(u) + weight_u_v); // relax                  (here if needed. This is similar)
		          pq.offer(new IntegerPair(dist.get(v), v)); //      (as printpath in BFS)
		          // enqueue this neighbor regardless whether it is already in pq or not    
		    } } }
		
		 if (E!=0 || dist.get(t)!=1000000000){
			System.out.println(dist.get(t));
		 }else{
			System.out.println("unreachable");
		 }
	}
}