package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/6/26 15:34
 */
public class Graph {
    private int vNum;
    private List<Integer>[] adj;

    public Graph(int n) {
        vNum = n;
        for(int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }
    }

    public void connect(int v, int u) {
        if(v >= vNum || u >=vNum) return;
        adj[v].add(u);
    }

    /**
     * Do a breadth first search starting from node v
     * @param v
     */
    public void BFS(int v) {
        boolean[] visited = new boolean[vNum];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        while(!queue.isEmpty()) {
            int s = queue.poll();
            System.out.print(s + " ");
            for(int p : adj[s]) {
                if(!visited[p]) {
                    queue.offer(p);
                    visited[p] = true;
                }
            }
        }
    }

    /**
     * Do a bfs traverse for all node
     */
    public void BFSTraverse() {
        boolean[] visited = new boolean[vNum];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < vNum; ++i) {
            if(!visited[i]) {
                queue.offer(i);
                visited[i] = true;
                while(queue.isEmpty()) {
                    int s = queue.poll();
                    System.out.print(s + " ");
                    for(int p : adj[s]) {
                        if(!visited[p]) {
                            queue.offer(p);
                            visited[p] = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Run a depth first search starting from node v
     * @param v
     */
    public void DFS(int v) {
        boolean[] visited = new boolean[vNum];
        dfsHelper(v, visited);
    }

    private void dfsHelper(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for(int p : adj[v]) {
            if(!visited[p]) {
                dfsHelper(p, visited);
            }
        }
    }

    /**
     * Run a DFS traverse for all nodes
     */
    public void DFSTravesal() {
        boolean[] visited = new boolean[vNum];
        for(int i = 0; i < vNum; ++i) {
            if(!visited[i]) {
                dfsHelper(i, visited);
            }
        }
    }

    public void topologicalSortByBFS(List<Integer> order) {
        List<Integer>[] graph = new ArrayList[vNum];
        for(int i = 0; i < vNum; ++i) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[vNum];
        for(int i = 0; i < adj.length; ++i) {
            for(int j = 0; j < adj[i].size(); ++j) {
                indegree[j]++;
            }
        }
//        for(int i = 0; i < edges.length; ++i) {
//            graph[edges[i][0]].add(edges[i][1]);
//            indegree[edges[i][1]]++;
//        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < vNum; ++i) {
            if(indegree[i] == 0) {
                queue.offer(i);
                order.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int neighbor = queue.poll();
            for(int n : graph[neighbor]) {
                if(--indegree[n] == 0) {
                    queue.offer(n);
                    order.add(n);
                }
            }
        }
    }

    public void topologicalSortByDFS(LinkedList<Integer> order) {
        boolean[] visited = new boolean[vNum];
        for(int i = 0; i < vNum; ++i) {
            if(!visited[i]) {
                dfsTopo(i, order, visited);
            }
        }
    }

    private void dfsTopo(int v, LinkedList<Integer> order, boolean[] visited) {
        visited[v] = true;
        for(int neighbor : adj[v]) {
            if(!visited[neighbor]) {
                dfsTopo(neighbor, order, visited);
            }
        }
        order.addFirst(v);
    }
}
