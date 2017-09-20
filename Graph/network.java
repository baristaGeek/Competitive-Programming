//ACCEPTED BY UVA

import java.io.*;
import java.util.*;
import java.util.LinkedList;
 
class Main
{
    private int V;   
 
    private LinkedList<Integer> adj[];
    int time = 0;
    static final int NIL = -1;
 
    Main(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    void addEdge(int v, int w)
    {
        adj[v].add(w);  
        adj[w].add(v);  
    }

    void APUtil(int u, boolean visited[], int disc[],
                int low[], int parent[], boolean ap[])
    {
 
        int children = 0;
 
        visited[u] = true;
 
        disc[u] = low[u] = ++time;
 
        Iterator<Integer> i = adj[u].iterator();
        while (i.hasNext())
        {
            int v = i.next();  
            if (!visited[v])
            {
                children++;
                parent[v] = u;
                APUtil(v, visited, disc, low, parent, ap);

                low[u]  = Math.min(low[u], low[v]);
 
                if (parent[u] == NIL && children > 1)
                    ap[u] = true;
 
                if (parent[u] != NIL && low[v] >= disc[u])
                    ap[u] = true;
            }
 
            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }
 
    void AP()
    {
    		int ans = 0;

        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];
        boolean ap[] = new boolean[V]; 
 
        for (int i = 0; i < V; i++)
        {
            parent[i] = NIL;
            visited[i] = false;
            ap[i] = false;
        }

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                APUtil(i, visited, disc, low, parent, ap);

        for (int i = 0; i < V; i++)
            if (ap[i] == true)
                ans++;
        
        System.out.println(ans);
    }
 
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String in = br.readLine();
       // System.out.println("firstScan: " + in);
        
        while (!in.equals("0")) {
            int nPlaces = Integer.parseInt(in);
            Main g1 = new Main(nPlaces);
            while(!in.equals("0")) {
            		in = br.readLine();
            		//System.out.println("ScannedIf: " + in);
            		String [] split = in.split(" ");
            		int vertex = Integer.parseInt(split[0])-1;
            		for (int i=1; i<split.length; i++) {
            			int adjVertex = Integer.parseInt(split[i])-1;
            			g1.addEdge(vertex, adjVertex);
            		}
            } /*if (in.equals("0")) {
            		in = br.readLine();
            		System.out.println("found 0");
            		continue;
            }*/
        	in = br.readLine();
        //	System.out.println("scannedOut: " + in);
            g1.AP();
        }
    }
}
