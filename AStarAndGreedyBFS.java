package LatihanHeuristicSearch;

import java.util.*;

class Node {
    int x, y; // Koordinat simpul
    int cost; // Biaya sejauh ini
    int heuristic; // Nilai heuristik

    public Node(int x, int y, int cost, int heuristic) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.heuristic = heuristic;
    }
}

public class AStarAndGreedyBFS {
    public static int[][] map = {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 1, 0},
        {0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    public static int goalX = 4;
    public static int goalY = 4;

    public static int calculateHeuristic(int x, int y) {
        // Implementasikan fungsi heuristik yang sesuai
        // Contoh menggunakan jarak Manhattan
        return Math.abs(x - goalX) + Math.abs(y - goalY);
    }

    public static void AStarSearch() {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost + a.heuristic));
        Set<Node> closedSet = new HashSet<>();

        Node start = new Node(0, 0, 0, calculateHeuristic(0, 0));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == goalX && current.y == goalY) {
                System.out.println("A* Path Found");
                // Implementasikan logika untuk menampilkan path atau operasi lain di sini
                return;
            }

            closedSet.add(current);

            // Implementasikan ekspansi simpul saat ini dan tambahkan tetangga ke openSet
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue; // Jangan ekspansi ke simpul saat ini
                    int newX = current.x + dx;
                    int newY = current.y + dy;

                    if (isValid(newX, newY) && !isInClosedSet(newX, newY, closedSet)) {
                        int newCost = current.cost + 1; // Biaya ke tetangga selalu 1
                        int newHeuristic = calculateHeuristic(newX, newY);
                        Node neighbor = new Node(newX, newY, newCost, newHeuristic);
                        openSet.add(neighbor);
                    }
                }
            }
        }
    }

    public static void GreedyBestFirstSearch() {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(a -> a.heuristic));
        Set<Node> closedSet = new HashSet<>();

        Node start = new Node(0, 0, 0, calculateHeuristic(0, 0));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == goalX && current.y == goalY) {
                System.out.println("Greedy BFS Path Found");
                // Implementasikan logika untuk menampilkan path atau operasi lain di sini
                return;
            }

            closedSet.add(current);

            // Implementasikan ekspansi simpul saat ini dan tambahkan tetangga ke openSet berdasarkan heuristik
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue; // Jangan ekspansi ke simpul saat ini
                    int newX = current.x + dx;
                    int newY = current.y + dy;

                    if (isValid(newX, newY) && !isInClosedSet(newX, newY, closedSet)) {
                        int newHeuristic = calculateHeuristic(newX, newY);
                        Node neighbor = new Node(newX, newY, 0, newHeuristic); // Biaya diabaikan dalam Greedy BFS
                        openSet.add(neighbor);
                    }
                }
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length && map[x][y] == 0;
    }

    public static boolean isInClosedSet(int x, int y, Set<Node> closedSet) {
        for (Node node : closedSet) {
            if (node.x == x && node.y == y) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AStarSearch();
        GreedyBestFirstSearch();
    }
}
