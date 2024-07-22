import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UndirectedGraphAdjacencyList
{
    private LinkedList<Integer>[] adj;  
    private int V;
    private int E;
    
    //suppress warnings given at line 16
    @SuppressWarnings("unchecked")
    public UndirectedGraphAdjacencyList(int nodes){
        this.V = nodes;
        this.E = 0;
        //initializing the array part of the adjacency list
        this.adj = new LinkedList[nodes];
        //initializing each list stored in each index of the array (they all point to null for now)
        for(int i = 0; i < nodes; i++){
            this.adj[i] = new LinkedList<>();
        }
    }

    //to add the edge (here we just put the values themselves into the to show that that value has edge with these two values)
    //we do for vand u as this is an undirected graph (i.e if u has an edge with v then v has an edge with u)
    public void addEdge(int u, int v){
        this.adj[u].add(v);
        this.adj[v].add(u);
        E++;
    }

    //method to return the graph to be printed
    public String toString(){
        //we use string builder to be able to append stuff to a string
        StringBuilder sb = new StringBuilder();
        sb.append(V + " vertices, " + E + " edges " + "\n");
        for(int v = 0; v < V; v++){
            sb.append(v + ": ");
            for(int w : adj[v]){
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        //we return the string builder object as string by converting it to string using the inbuilt function toString()
        return sb.toString();
    }

    //breadth first seartch
    //s is the source node, i.e the starting node
    public void bfs(int s){
        //array to keep track of what vertice is visited
        boolean[] visited = new boolean[V];
        //use queue to keep track of nodes and to print them in order
        Queue<Integer> q = new LinkedList<>();
        //source node is considered visited as it is passed to the method
        visited[s] = true;
        //enter that to queue
        q.offer(s);
        //if queue is not empty, execute
        while(!q.isEmpty()){
            //get vertice from queue and print it
            int u = q.poll();
            System.out.print(u + " ");
            //use the adjacency list to find which vertice is connected to the current vertice
            for(int v : adj[u]){
                //is not visited, mark the vertice visited and push it to queue
                if(!visited[v]){
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
    }

    //depth first search
    public void dfs(int s){
        //array to keep track of what vertice is visited
        boolean[] visited = new boolean[V];
        //stack to print and keep order of elements visited
        Stack<Integer> stack = new Stack<>();
        //push source vertice to stack
        stack.push(s);
        //if stack is not empty, execute
        while(!stack.isEmpty()){
            //take topmost element of stack
            int u = stack.pop();
            //if current vertice not visited, execute
            if(!visited[u]){
                visited[u] = true;
                System.out.print(u + " ");
                //check if adjacent vertices to current vertices are visited using the adjacency list
                for(int v : adj[u]){
                    //is not visited, push it to the stack
                    if(!visited[v]){
                        stack.push(v);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        UndirectedGraphAdjacencyList g = new UndirectedGraphAdjacencyList(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        g.addEdge(2, 4);

        System.out.println(g.toString());

        //breadth first search
        g.bfs(0);

        System.out.println();

        //depth first search
        g.dfs(0);
    }
}
