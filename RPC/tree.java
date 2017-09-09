import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
 
public class tree
{
    public static List<Integer>[] getRandomTree2(int n, Random rnd)
    {
        @SuppressWarnings("unchecked")
        List<Integer>[] t = new List[n];
        for (int i = 0; i < n; i++)
            t[i] = new ArrayList<>();
        int[] p = new int[n];
        for (int i = 0, j; i < n; j = rnd.nextInt(i + 1), p[i] = p[j], p[j] = i, i++)
            ; // random permutation
        for (int i = 1; i < n; i++)
        {
            int parent = p[rnd.nextInt(i)];
            t[parent].add(p[i]);
            t[p[i]].add(parent);
        }
        return t;
    }
 
    public static List<Integer>[] pruferCode2Tree(int[] pruferCode)
    {
        int n = pruferCode.length + 2;
        @SuppressWarnings("unchecked")
        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();
        int[] degree = new int[n];
        Arrays.fill(degree, 1);
        for (int v : pruferCode)
            ++degree[v];
        int ptr = 0;
        while (degree[ptr] != 1)
            ++ptr;
        int leaf = ptr;
        for (int v : pruferCode)
        {
            tree[leaf].add(v);
            tree[v].add(leaf);
            --degree[leaf];
            --degree[v];
            if (degree[v] == 1 && v < ptr)
            {
                leaf = v;
            }
            else
            {
                for (++ptr; ptr < n && degree[ptr] != 1; ++ptr)
                    ;
                leaf = ptr;
            }
        }
        for (int v = 0; v < n - 1; v++)
        {
            if (degree[v] == 1)
            {
                tree[v].add(n - 1);
                tree[n - 1].add(v);
            }
        }
        return tree;
    }
 
    static void pruferDfs(List<Integer>[] tree, int[] parent, int v)
    {
        for (int i = 0; i < tree[v].size(); ++i)
        {
            int to = tree[v].get(i);
            if (to != parent[v])
            {
                parent[to] = v;
                pruferDfs(tree, parent, to);
            }
        }
    }
 
    // precondition: n >= 2
    public static List<Integer>[] getRandomTree(int V, Random rnd)
    {
        int[] a = new int[V - 2];
        for (int i = 0; i < a.length; i++)
        {
            a[i] = rnd.nextInt(V);
        }
        return pruferCode2Tree(a);
    }
 
    // precondition: V >= 2, V-1 <= E <= V*(V-1)/2
    public static List<Integer>[] getRandomUndirectedConnectedGraph(int V,
            int E, Random rnd)
    {
        List<Integer>[] g = getRandomTree(V, rnd);
        Set<Long> edgeSet = new LinkedHashSet<>();
        for (int i = 0; i < V; i++)
        {
            for (int j = i + 1; j < V; j++)
            {
                edgeSet.add(((long) i << 32) + j);
            }
        }
        for (int i = 0; i < V; i++)
        {
            for (int j : g[i])
            {
                edgeSet.remove(((long) i << 32) + j);
            }
        }
        List<Long> edges = new ArrayList<>(edgeSet);
        for (int x : getRandomArrangement(edges.size(), E - (V - 1), rnd))
        {
            long e = edges.get(x);
            int u = (int) (e >>> 32);
            int v = (int) e;
            g[u].add(v);
            g[v].add(u);
        }
        for (int i = 0; i < V; i++)
            Collections.sort(g[i]);
        return g;
    }
 
    // precondition: V >= 2, V-1 <= E <= V*(V-1)/2
    public static List<Integer>[] getRandomUndirectedConnectedGraph2(int V,
            int E, Random rnd)
    {
        List<Integer>[] g = getRandomTree(V, rnd);
        Set<Long> edgeSet = new LinkedHashSet<>();
        for (int i = 0; i < V; i++)
        {
            for (int j : g[i])
            {
                edgeSet.add(((long) i << 32) + j);
            }
        }
        for (int i = 0; i < E - (V - 1); i++)
        {
            int u;
            int v;
            long edge;
            while (true)
            {
                u = rnd.nextInt(V);
                v = rnd.nextInt(V);
                edge = ((long) u << 32) + v;
                if (u < v && !edgeSet.contains(edge))
                    break;
            }
            edgeSet.add(edge);
            g[u].add(v);
            g[v].add(u);
        }
        for (int i = 0; i < V; i++)
            Collections.sort(g[i]);
        return g;
    }
 
    static int[] getRandomArrangement(int n, int m, Random rnd)
    {
        int[] res = new int[n];
        for (int i = 0; i < n; i++)
        {
            res[i] = i;
        }
        for (int i = 0; i < m; i++)
        {
            int j = n - 1 - rnd.nextInt(n - i);
            int t = res[i];
            res[i] = res[j];
            res[j] = t;
        }
        return Arrays.copyOf(res, m);
    }
 
    static void checkGraph(int V, int E, Random rnd)
    {
        List<Integer>[] g = getRandomUndirectedConnectedGraph(V, E, rnd);
        int n = g.length;
        int[][] a = new int[n][n];
        int edges = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j : g[i])
            {
                ++a[i][j];
                ++edges;
            }
        }
        if (edges != 2 * E)
        {
            throw new RuntimeException();
        }
        for (int i = 0; i < n; i++)
        {
            if (a[i][i] != 0)
            {
                throw new RuntimeException();
            }
            for (int j = 0; j < n; j++)
            {
                if (a[i][j] != a[j][i] || a[i][j] != 0 && a[i][j] != 1)
                {
                    throw new RuntimeException();
                }
            }
        }
    }
 
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		String line = br.readLine();
    		String[] split;
    		while(line!=null&&!line.isEmpty()) {
        
        int n = Integer.parseInt(line)-2;
        int[] code = new int[n];
        line = br.readLine();
        split = line.split(" ");
        for (int i = 0; i < n; i++)
        {
            code[i] = Integer.parseInt(split[i])-1;
        }
        List<Integer>[] tree = pruferCode2Tree(code);
        Random rnd = new Random(1);
        for (int step = 0; step < 1000; step++)
        {
            int V = rnd.nextInt(50) + 2;
            checkGraph(V, V - 1, rnd);
            checkGraph(V, V * (V - 1) / 2, rnd);
            checkGraph(V, rnd.nextInt(V * (V - 1) / 2 - (V - 1) + 1) + V - 1,
                    rnd);
        }
        ArrayList<String> pairs = new ArrayList<String>();
        for (int i=0; i<tree.length; i++) {
        		int v1 = (i+1);
        		int v2 = (tree[i].get(0)+1);
        		if ((v1<v2) && (!pairs.contains(v1 + " " + v2))) {
        			System.out.println(v1 + " " + v2);
        			pairs.add(v1 + " " + v2);
        		}else if ((v1>v2) && (!pairs.contains(v2 + " " +v1))){
        			System.out.println(v2 + " " + v1);
        			pairs.add(v2 + " " + v1);
        		}
        }
        line = br.readLine();
    }
    		}
}