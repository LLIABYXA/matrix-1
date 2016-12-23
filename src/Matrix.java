import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public interface Matrix {
    void set(long i, long j, long val);
    long get(long i, long j);
    Matrix multiply(Matrix other);

    void setSize(long n, long m);
    default void read(FileReader in) {
        Scanner sc = new Scanner(in);
        setSize(sc.nextLong(), sc.nextLong());
        for (int i = 0; i < getN(); i++)
            for (int j = 0; j < getM(); j++)
                this.set(i, j, sc.nextLong());
    }

    default void store(FileWriter out) {
        try {
            for (int i = 0; i < getN(); i++) {
                for (int j = 0; j < getM(); j++)
                    out.write(get(i, j) + " ");
                out.write("\n");
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void print() {
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getM(); j++)
                System.out.print(get(i, j) + " ");
            System.out.println();
        }
    }

    long getN();
    long getM();
}
