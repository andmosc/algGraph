package task7;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    Vertex[] vertexList;
    private final int[][] adjacent;
    private int curN;

    public Graph(int size) {
        this.vertexList = new Vertex[size];
        this.adjacent = new int[size][size];
        this.curN = 0;
    }

    public void addVertex(int v) {
        vertexList[curN++] = new Vertex(v);
    }

    public void addAdge(int start, int end) {
        adjacent[start][end] = 1;
        adjacent[end][start] = 1;
    }

    public int[] calcPaths() {
        int k = 0;
        int[] markSize = new int[curN];

        for (int i = 0; i < curN; i++) {
            if (!vertexList[i].isVisited()) {
                k = dfs(i);
                markSize[i] = k;
            }
            markSize[i] = k;
        }

        int[] answer = new int[curN];
        for (int i = 0; i < curN; i++) {
            answer[i] = markSize[i] - 1;
        }
        clearPath();
        return answer;
    }
// в глубину
    public int dfs(int vertex) {
        vertexList[vertex].setVisited(true);
        int size = 1;
        for (int i = vertex; i < curN; i++) {
            if (!vertexList[i].isVisited() && adjacent[vertex][i] == 1)
                size += dfs(i);
        }
        if (vertex == curN - 1)
            vertexList[vertex].setVisited(false);
        return size;
    }

    public void clearPath() {
        for (int i = 0; i < curN; i++) {
            vertexList[i].setVisited(false);
        }
    }

    //обход в ширину
    public void bypassInBreadh() {
        Queue<Integer> queue = new LinkedList<>();
        System.out.println();
        int vertex = 0;
        for (int i = 0; i < curN; i++) {
            //<--установить счетчик для компаненты связности
            if (!vertexList[i].isVisited)
                queue.add(vertexList[i].vertex);

            while (!queue.isEmpty()) {
                vertex = queue.poll();
                System.out.print(vertex+" ");
                vertexList[vertex].setVisited(true);

                for (int j = vertex; j < curN; j++) {
                    if (adjacent[vertex][j] == 1 && !vertexList[j].isVisited) {
                        queue.add(vertexList[j].vertex);
                        vertexList[j].setVisited(true);
                    }
                }
            }
        }
    }

    private static class Vertex {
        int vertex;
        boolean isVisited;

        public Vertex(int vertex) {
            this.vertex = vertex;
            this.isVisited = false;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(boolean visited) {
            isVisited = visited;
        }
    }
}
