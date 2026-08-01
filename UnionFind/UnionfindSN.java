package UnionFind;

public class UnionfindSN {

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        public boolean union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) return false;

            if (size[i] < size[j]) {
                parent[i] = j;
                size[j] += size[i];
            }
            else {
                parent[j] = i;
                size[i] += size[j];
            }

            count--;
            return true;
        }

        public int getcount() {
            return count;
        }
    }

    public static class Log {
        private int timestamp, member1, member2;

        public Log(int timestamp, int member1, int member2) {
            this.timestamp = timestamp;
            this.member1 = member1;
            this.member2 = member2;
        }
    }

    public static int earliestCon(Log[] logs, int n) {
        UnionFind uf = new UnionFind(n);

        for (Log log : logs) {
            if (uf.union(log.member1, log.member2)) {
                if (uf.getcount() == 1) {
                    return log.timestamp;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Log[] logs = new Log[] {
                new Log(20190101, 0, 1),
                new Log(20190104, 3, 4),
                new Log(20190107, 2, 3),
                new Log(20190211, 1, 5),
                new Log(20190224, 2, 4),
                new Log(20190301, 0, 3),
                new Log(20190312, 1, 2),
                new Log(20190322, 4, 5)
        };
        int n = 6;
        int earliestTime = earliestCon(logs, n);
        System.out.println("Earliest time when all members are connected: " + earliestTime);
    }

}
