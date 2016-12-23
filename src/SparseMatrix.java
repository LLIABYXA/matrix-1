import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class SparseMatrix implements Matrix {
    private class Index implements Comparable<Index> {
        private long x;
        private long y;

        public Index(long x, long y) {
            super();
            this.x = x;
            this.y = y;
        }

        public int compareTo(Index index) {
            long ix = index.x;
            if (ix == x) {
                long iy = index.y;
                if (iy == y) {
                    return 0;
                } else if (iy < y) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (ix < x) {
                return -1;
            } else {
                return 1;
            }
        }

        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + (int) (x ^ (x >>> 32));
            result = PRIME * result + (int) (y ^ (y >>> 32));
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Index other = (Index) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }
    }

    private SortedMap<Index, Long> data;
    long n, m;

    public SparseMatrix() {
        this.data = new TreeMap<>();
    }

    public SparseMatrix(long n, long m) {
        this.n = n;
        this.m = m;
        this.data = new TreeMap<>();
    }

    public void setSize(long n, long m) {
        this.n = n;
        this.m = m;
    }

    public void set(long i, long j, long val) {
        Index idx = new Index(i, j);
        if (data.containsKey(idx))
            data.replace(idx, val);
        else
            data.put(idx, val);
    }

    public long get(long i, long j) {
        Index idx = new Index(i, j);
        if (data.containsKey(idx))
            return data.get(new Index(i, j));
        else
            return 0;
    }

    public Matrix multiply(Matrix other) {
        Matrix res = new SparseMatrix(this.getN(), other.getM());
        for (int i = 0; i < this.getN(); i++) { // aRow
            for (int j = 0; j < other.getM(); j++) { // bColumn
                for (int k = 0; k < this.getM(); k++) { // aColumn
                    res.set(i, j, res.get(i, j) + this.get(i, k) * other.get(k, j));
                }
            }
        }
        return res;
    }

    public long getN() { return n; }
    public long getM() { return m; }
}
