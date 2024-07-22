public class UndirectedGraph 
{
    //the matrix that is used to represent the edge
    int[][] adjMatrix;
    private int V;//no. of nodes
    private int E;//no. of edges
    
    //initializes the matrix
    public UndirectedGraph(int nodes){
        this.V = nodes;
        this.E = 0;
        this.adjMatrix = new int[nodes][nodes];
    }

    //method to add edges
    //the nodes(indexes of the matrix) which are connected by an edge
    //are represented by value '1' (stored in the matrix at the index which represents the nodes)
    public void addEdge(int u, int v){
        //as it is an undirected graph we need to show that there is a connection from both
        //u to v and from v to u
        this.adjMatrix[u][v] = 1;
        this.adjMatrix[v][u] = 1;
        E++;
    }

    //method to return the graph to be printed
    public String toString(){
        //we use string builder to be able to append stuff to a string
        StringBuilder sb = new StringBuilder();
        sb.append(V + " vertices, " + E + " edges " + "\n");
        for(int v = 0; v < V; v++){
            sb.append(v + ": ");
            for(int w : adjMatrix[v]){
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        //we return the string builder object as string by converting it to string using the inbuilt function toString()
        return sb.toString();
    }

    public static void main(String args[]){
        //creating the graph
        UndirectedGraph g = new UndirectedGraph(4);
        //adding edges
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);

        //printing the graph
        //the matrix as a whole is printed as a string (dues to everything being appended at end)
        System.out.println(g.toString());
    }
}
