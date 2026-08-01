package UnionFind;

public class SuccesorWithD {
    private int[] parent;
    private int[] succ;


    public SuccesorWithD(int n) {
        parent = new int[n];
        succ = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            succ[i] = i;
        }

    }

    public int find(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public void remove(int x) {
        int idX = find(x);
        int idS = find(x + 1);
        if (idX != idS) {
            parent[idX] = idS;
        }
    }

    public int successor(int x) {
        int idX = find(x);
        return succ[idX];
    }

    public static void main(String[] args) {
        SuccesorWithD succesor = new SuccesorWithD(10);
        succesor.remove(3);
        succesor.remove(4);
        System.out.println(succesor.successor(2));
        System.out.println(succesor.successor(4));
        System.out.println(succesor.successor(5));
    }

}
