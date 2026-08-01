package UnionFind;

public class CanEvnt {

    public static class CanonicalUnionFind {
        private int[] parent;
        private int[] size;
        private int[] max; // Almacena el elemento más grande de cada componente

        public CanonicalUnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            max = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                max[i] = i;
            }
        }

        private int root(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        public int find(int i) {
            int r = root(i);
            return max[r];
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        public void union(int p, int q) {
            int i = root(p);
            int j = root(q);

            if (i == j) return;

            if (size[i] < size[j]) {
                parent[i] = j;
                size[j] += size[i];
                max[j] = Math.max(max[j], max[i]);
            }
            else {
                parent[j] = i;
                size[i] += size[j];
                max[i] = Math.max(max[i], max[j]);
            }
        }
    }

}
