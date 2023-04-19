import java.util.*;

public class FlightOperationsManager {
    static int N, M, T, C;
    static int[] parent;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Integer>[] adjList;

        
          //Gets the path from the first node to the given node.
             static ArrayList<Integer> getPath(int u) {
        ArrayList<Integer> path = new ArrayList<>();
        while (u != -1) {
            path.add(u);
            u = parent[u];
        }
        Collections.reverse(path);
        return path;
    }
    //Uses BFS from given node
    static void bfs(int first) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(first);
        visited[first] = true;
        dist[first] = 0;
        parent[first] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adjList[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                    dist[v] = dist[u] + 1;
                    parent[v] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        T = scan.nextInt();
        C = scan.nextInt();
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            adjList[u].add(v);
            adjList[v].add(u);
        }

        int firstX = scan.nextInt();
        int lastY = scan.nextInt();

        visited = new boolean[N + 1];
        dist = new int[N + 1];
        parent = new int[N + 1];

        Arrays.fill(parent, -1);
        bfs(firstX);
        ArrayList<Integer> path = getPath(lastY);

        int totalTime = 0;
        for (int i = 1; i < path.size(); i++) {
            totalTime = totalTime + C;
            if (path.get(i) != path.get(path.size() - 1)) {
                if ((totalTime) % T != 0) {
                    int waitTime = T - ((totalTime) % T);
                    totalTime = totalTime + waitTime;
                }
            }
        }

        System.out.println(path.size());
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }

        System.out.println();
        System.out.println(totalTime);
    }
}
