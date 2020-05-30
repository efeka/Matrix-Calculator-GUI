
public class MatrixA {
	
	private static double[][] A;
	
	public MatrixA(int w, int h) {
		A = new double[h][w];
	}
	
	public static void setA(double[][] arr) {
		A = arr;
	}
	
	public static double[][] getA() {
		return A;
	}

	public static void display() {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				System.out.print(A[i][j] + " ");
				if (j == A[i].length - 1)
					System.out.println("");
			}
		}
	}
}
