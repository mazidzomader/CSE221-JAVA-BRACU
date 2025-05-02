
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;



public class F_An_Ancient_Ordering {
    static Node topoSort(AdjacencyList G, boolean[] usedChars) {
        Node L = null;
        for (int i = 26; i >= 1; i--) {
            if (usedChars[i - 1]) {
                Node u = G.nodes[i - 1];
                if (!u.visited) {
                    L = dfs(G, u, L);
                }
            }
        }
        return L;
    }

    static Node dfs(AdjacencyList G, Node u, Node L) {
        u.visited = true;
        Node temp = G.head[u.data - 1];
        while (temp != null) {
            Node v = G.nodes[temp.data - 1];
            if (!v.visited) {
                L = dfs(G, v, L);
            }
            temp = temp.next;
        }
        L = new Node(u.data, L);
        return L;
    }

    static boolean cycleDetector(AdjacencyList G, boolean[] usedChars) {
        for (int i = 1; i <= 26; i++) {
            if (usedChars[i - 1]) {
                Node u = G.nodes[i - 1];
                if (u.color.equals("White")) {
                    if (dfsCycle(G, u)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean dfsCycle(AdjacencyList G, Node u) {
        u.color = "Gray";
        Node temp = G.head[u.data - 1];
        while (temp != null) {
            Node v = G.nodes[temp.data - 1];
            if (v.color.equals("White")) {
                if (dfsCycle(G, v)) {
                    return true;
                }
            } else if (v.color.equals("Gray")) {
                return true;
            }
            temp = temp.next;
        }
        u.color = "Black";
        return false;
    }

    static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.nextLine();
        }
        sc.close();
        AdjacencyList G = new AdjacencyList(26);
        boolean[] usedChars = new boolean[26];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                usedChars[c - 'a'] = true;
            }
        }

        boolean flag = false;
        for (int i = 0; i < n - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                flag = true;
                break;
            }
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    int u = w1.charAt(j) - 'a' + 1;
                    int v = w2.charAt(j) - 'a' + 1;
                    G.addEdge(u, v);
                    break;
                }
            }
        }

        if (flag) {
            System.out.println(-1);
            return;
        }

        if (cycleDetector(G, usedChars)) {
            System.out.println(-1);
            return;
        }

        Node L = topoSort(G, usedChars);
        List<Character> result = new ArrayList<>();
        while (L != null) {
            int charIdx = L.data - 1;
            result.add((char) ('a' + charIdx));
            L = L.next;
        }

        for (char c : result) {
            System.out.print(c);
        }
        System.out.println();
        
    }

    public static void main(String[] args) {
        solve();
    }
}

class Node {
    int data;
    Node next;
    boolean visited;
    String color;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.visited = false;
        this.color = "White";
    }

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
        this.visited = false;
        this.color = "White";
    }
}

class AdjacencyList {
    Node[] head;
    Node[] nodes;

    AdjacencyList(int n) {
        head = new Node[n];
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
    }

    void addEdge(int u, int v) {
        Node newNodeV = new Node(v);
        newNodeV.next = head[u - 1];
        head[u - 1] = newNodeV;
    }
}