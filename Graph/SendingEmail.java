import java.util.*;
import java.io.*;

public class SendingEmail{
	
	
static class IntegerPair implements Comparable {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(Object o) {
    if (this.first() != ((IntegerPair )o).first())
      return this.first() - ((IntegerPair )o).first();
    else
      return this.second() - ((IntegerPair )o).second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}
	
	
  public static final int INF = 1000000000;
  private static Vector< Vector< IntegerPair > > AdjList = new Vector< Vector< IntegerPair > >();
  
  public static void main(String[] args) throws Exception {
    int V, E, s, t, u, v, w; //esa t está bien?
	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	PrintWriter pr = new PrintWriter (new BufferedWriter(new OutputStreamWriter(System.out)));
	
	String in = br.readLine();
	int Cases = Integer.parseInt(in);
	
	for (int tc=0; tc<Cases; tc++){
		in = br.readLine();
		String [] split = in.split(" ");
		
	    V = Integer.parseInt(split[0]); //nVertices
	    E = Integer.parseInt(split[1]); //nEdges
	    s = Integer.parseInt(split[2]); //initial node
		t = Integer.parseInt(split[3]); //goal node

	    AdjList.clear();
	    for (int i = 0; i < V; i++) {
	      Vector< IntegerPair > Neighbor = 
	        new Vector < IntegerPair >();
	      AdjList.add(Neighbor); // add neighbor list to Adjacency List
	    }

	    for (int i = 0; i < E; i++) { //number of edges/cables
		  in = br.readLine();
		  String [] inSplit = in.split(" ");
			
	      u = Integer.parseInt(inSplit[0]); //one node
	      v = Integer.parseInt(inSplit[1]); //other node
	      w = Integer.parseInt(inSplit[2]); //weight of the edge between

	      AdjList.get(u).add(new IntegerPair (v, w)); // first time using weight
	    }

	    // Dijkstra routine
	    Vector < Integer > dist = new Vector < Integer > ();
	    dist.addAll(Collections.nCopies(V, INF)); dist.set(s, 0); // INF = 1*10^9 not MAX_INT to avoid overflow
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

	    while (!pq.isEmpty()) { // main loop
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
}