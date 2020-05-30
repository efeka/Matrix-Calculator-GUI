
public class MatrixOps {

	public double[][] substract(double[][] A, double[][] B) {
		if (A.length != B.length || A[0].length != B[0].length)
			return null;
		double[][] C = new double[A.length][A[0].length];
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A[0].length; j++)
				C[i][j] = A[i][j] - B[i][j];
		return C;
	}

	public double[][] multiply(double[][] A, double number) {
		if (A == null) {
			MainWindow.setCalculatorText("ERROR! Matrices are not set");
			return null;
		}
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A[i].length; j++)
				A[i][j] *= number;
		return A;
	}

	public double[][] multiply(double[][] A, double[][] B) {
		try {
			if (A[0].length != B.length)
				return null;
		} catch (NullPointerException e) {
			MainWindow.setCalculatorText("ERROR! Matrices are not set");
			return null;
		}

		double[][] C = new double[A.length][B[0].length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B[0].length; j++) {
				for (int k = 0; k < A[0].length; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		return C;
	}

	public double[][] sum(double[][] A, double[][] B) {
		if (A.length != B.length || A[0].length != B[0].length)
			return null;
		double[][] C = new double[A.length][A[0].length];
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A[0].length; j++)
				C[i][j] = A[i][j] + B[i][j];
		return C;
	}
	
	public static void display(double[][] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				System.out.print(A[i][j] + " ");
				if (j == A[i].length - 1)
					System.out.println("");
			}
		}
	}

	public double[][] invert(double a[][]) {
		if (determinant(a) == 0)
			return null;
		int n = a.length;
		double x[][] = new double[n][n];
		double b[][] = new double[n][n];
		int index[] = new int[n];
		for (int i=0; i<n; ++i) 
			b[i][i] = 1;

		// Transform the matrix into an upper triangle
		gaussian(a, index);

		// Update the matrix b[i][j] with the ratios stored
		for (int i=0; i<n-1; ++i)
			for (int j=i+1; j<n; ++j)
				for (int k=0; k<n; ++k)
					b[index[j]][k]
							-= a[index[j]][i]*b[index[i]][k];

		// Perform backward substitutions
		for (int i=0; i<n; ++i) 
		{
			x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
			for (int j=n-2; j>=0; --j) 
			{
				x[j][i] = b[index[j]][i];
				for (int k=j+1; k<n; ++k) 
				{
					x[j][i] -= a[index[j]][k]*x[k][i];
				}
				x[j][i] /= a[index[j]][j];
			}
		}
		return x;
	}

	private void gaussian(double a[][], int index[]) 
	{
		int n = index.length;
		double c[] = new double[n];

		// Initialize the index
		for (int i=0; i<n; ++i) 
			index[i] = i;

		// Find the rescaling factors, one from each row
		for (int i=0; i<n; ++i) 
		{
			double c1 = 0;
			for (int j=0; j<n; ++j) 
			{
				double c0 = Math.abs(a[i][j]);
				if (c0 > c1) c1 = c0;
			}
			c[i] = c1;
		}

		// Search the pivoting element from each column
		int k = 0;
		for (int j=0; j<n-1; ++j) 
		{
			double pi1 = 0;
			for (int i=j; i<n; ++i) 
			{
				double pi0 = Math.abs(a[index[i]][j]);
				pi0 /= c[index[i]];
				if (pi0 > pi1) 
				{
					pi1 = pi0;
					k = i;
				}
			}

			// Interchange rows according to the pivoting order
			int itmp = index[j];
			index[j] = index[k];
			index[k] = itmp;
			for (int i=j+1; i<n; ++i) 	
			{
				double pj = a[index[i]][j]/a[index[j]][j];

				// Record pivoting ratios below the diagonal
				a[index[i]][j] = pj;

				// Modify other elements accordingly
				for (int l=j+1; l<n; ++l)
					a[index[i]][l] -= pj*a[index[j]][l];
			}
		}
	}
	
	public double determinant(double[][] matrix){ 
		int sum=0; 
		int s;
		if(matrix.length==1) 
			return(matrix[0][0]);

		for(int i=0;i<matrix.length;i++){ 
			double[][]smaller= new double[matrix.length-1][matrix.length-1];
			for(int a=1;a<matrix.length;a++){
				for(int b=0;b<matrix.length;b++){
					if(b<i)
						smaller[a-1][b]=matrix[a][b];
					else if(b>i)
						smaller[a - 1][b - 1]=matrix[a][b];
				}
			}
			if(i % 2 == 0)
				s = 1;
			else
				s=-1;
			sum += s * matrix[0][i] * (determinant(smaller)); 
		}
		return(sum); 
	}

}
