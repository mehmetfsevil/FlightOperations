import java.util.*;
//-----------------------------------------------------
//Title: Ship Tour Class
//Author: Mehmet Fatih Sevil
//ID: 36058703754
//Section: 3
//Assignment: 1
//Description: This Class shows the lexicographically shortest way to circle tour around islands with staring and including point
//-----------------------------------------------------

public class ShipTour {
    static int n, m, x, y;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static List<Integer> path;
    static List<Integer> ans;
    static TreeSet<Integer> nodes;
    static boolean isLoop = false; // flag to check if loop is completed

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        x = scan.nextInt();
        y = scan.nextInt();

        visited = new boolean[n + 1];
        path = new ArrayList<>();
        ans = null;
        nodes = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            nodes.add(i);
        }

        dfs(x);

        if (ans == null) {
            System.out.println("No path found");
        } else {
            for (int i : ans) {
                System.out.print(i + " ");
            }
        }
    }
    /* Compares two paths based on their node values.*/
    static boolean isSmaller(List<Integer> path1, List<Integer> path2) {
        int i = 0, j = 0;
        while (i < path1.size() && j < path2.size()) {
            if (path1.get(i) < path2.get(j)) {
                return true;
            } else if (path1.get(i) > path2.get(j)) {
                return false;
            }
            i++;
            j++;
        }
        return i == path1.size();
    }

    static void dfs(int curr) {
        visited[curr] = true;
        nodes.remove(curr);
        path.add(curr);

        if (curr == y) {
            if (ans == null || isSmaller(path, ans)) {
                ans = new ArrayList<>(path);
            }
            // Check if node 1 and node 3 are visited
            if (visited[1] && visited[3]) {
                isLoop = true;
            }
        } else {
            for (int neighbor : graph.get(curr)) {
                if (!visited[neighbor]) {
                    dfs(neighbor);
                }
            }
        }

        path.remove(path.size() - 1);
        visited[curr] = false;

        // If loop is completed and node 1 is not in path, add node 1 back to path
        if (isLoop && !path.contains(1) && nodes.isEmpty()) {
            path.add(1);
            dfs(1);
            path.remove(path.size() - 1); // remove node 1 from path after completing the loop
        }

        // Reset the isLoop flag if loop is not completed
        if (!nodes.isEmpty()) {
            isLoop = false;
        }
    }


}
