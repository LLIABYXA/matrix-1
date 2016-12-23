public class RegularMatrix implements Matrix {
    long n, m;
    long data[][];

    public RegularMatrix(long n, long m) {
        this.n = n;
        this.m = m;
        this.data = new long[(int)n][(int)m];
    }

    public void setSize(long n, long m) {
        this.n = n;
        this.m = m;
    }

    public void set(long i, long j, long val) {
        data[(int)i][(int)j] = val;
    }

    public long get(long i, long j) {
        return data[(int)i][(int)j];
    }

    public Matrix multiply(Matrix other)  {
        Matrix res = new RegularMatrix(this.getN(), other.getM());
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
