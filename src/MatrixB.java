
public class MatrixB {
	
	private static double[][] B;
	
	public MatrixB(int w, int h) {
		B = new double[h][w];
	}
	
	public static void setB(double[][] arr) {
		B = arr;
	}
	
	public static double[][] getB() {
		return B;
	}
	
	public static void display() {
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[i].length; j++) {
				System.out.print(B[i][j] + " ");
				if (j == B[i].length - 1)
					System.out.println("");
			}
		}
	}
}
