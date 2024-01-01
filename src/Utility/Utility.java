package Utility;

public class Utility {

    public static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            for (String value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
