import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int openSitesCount;
    private final int size;
    private final int Top, bottom;
    private final WeightedQuickUnionUF uf, uffFull;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        grid = new boolean[n][n];
        size = n;
        openSitesCount = 0;
        Top = 0;
        bottom = n * n + 1;

        uf = new WeightedQuickUnionUF(n * n + 2);
        uffFull = new WeightedQuickUnionUF(n * n + 1);

    }

    public void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("Index out of range");
        }
    }

    private int xyto1D(int row, int col) {
        return (row - 1) + size * col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;
        grid[row - 1][col - 1] = true;
        openSitesCount++;

        int currentid = xyto1D(row, col);
        if (row == 1) {
            uf.union(currentid, Top);
            uffFull.union(currentid, Top);
        }
        if (row == size) {
            uf.union(currentid, bottom);
        }
        // up
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(currentid, xyto1D(row - 1, col));
            uffFull.union(currentid, xyto1D(row - 1, col));
        }
        // down
        if (row < size && isOpen(row + 1, col)) {
            uf.union(currentid, xyto1D(row + 1, col));
            uffFull.union(currentid, xyto1D(row + 1, col));
        }
        // left
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(currentid, xyto1D(row, col - 1));
            uffFull.union(currentid, xyto1D(row, col - 1));
        }
        // right
        if (col < size && isOpen(row, col + 1)) {
            uf.union(currentid, xyto1D(row, col + 1));
            uffFull.union(currentid, xyto1D(row, col + 1));
        }

    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int currentid = xyto1D(row, col);
        return isOpen(row, col) && uffFull.find(currentid) == uffFull.find(Top);

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {

        return uf.find(Top) == uf.find(bottom);
    }


    // test client (optional)
    public static void main(String[] args) {

        int n = 3;

        Percolation percs = new Percolation(n);

        // open (1,2) , (1,3), (1,1)
        System.out.println("Is Open " + percs.isOpen(1, 2));
        percs.open(1, 2);
        System.out.println("Is Now open " + percs.isOpen(1, 2));

        percs.open(1, 3);
        percs.open(2, 1);

        System.out.println("Percolates?? " + percs.percolates());

        percs.open(2, 2);
        percs.open(3, 1);

        System.out.println("Percolates?? " + percs.percolates());

    }
}
