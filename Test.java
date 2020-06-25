import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        int[][] a = new int[3][5];
        int counter = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = counter++;
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]) + "\n");
        }
    }
}