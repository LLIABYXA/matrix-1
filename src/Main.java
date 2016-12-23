import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: arg1 should be a file with 1st matrix, arg2 - with 2nd!");
            System.exit(1);
        }

        Matrix m1, m2;

        // First file
        FileReader fileReader = new FileReader(args[0]);
        Scanner sc = new Scanner(fileReader);
        long n = sc.nextLong();
        long m = sc.nextLong();
        if (n * m > 1000 * 1000)
            m1 = new SparseMatrix();
        else
            m1 = new RegularMatrix(n, m);
        m1.read(new FileReader(args[0]));

        // Second file
        fileReader = new FileReader(args[1]);
        sc = new Scanner(fileReader);
        n = sc.nextLong();
        m = sc.nextLong();
        if (n * m > 100 * 100)
            m2 = new SparseMatrix();
        else
            m2 = new RegularMatrix(n, m);
        m2.read(new FileReader(args[1]));

        // Multiply them
        Matrix res = m1.multiply(m2);
        FileWriter writer = new FileWriter("out.dat");
        res.store(writer);
    }
}
