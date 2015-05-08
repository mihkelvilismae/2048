package Core;
public class Matrix {
	public MatrixElement[][] matrix = new MatrixElement[4][4];
	public final String UP = "up";
	public final String DOWN = "down";
	public final String LEFT = "left";
	public final String RIGHT = "right";

	public Matrix() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				matrix[i][j] = new MatrixElement(j, i, this);
			}
		}
	}

	public MatrixElement[] getRow(int y) {
		MatrixElement[] m = new MatrixElement[4];
		for (int x = 0; x < 4; x++) {
			m[x] = getMatrixElement(x, y);
		}
		return m;
	}

	public MatrixElement[] getColumn(int x) {
		MatrixElement[] m = new MatrixElement[4];
		for (int y = 0; y < 4; y++) {
			m[y] = getMatrixElement(x, y);
		}
		return m;
	}

	public void print() {
		for (int i = 0; i < 4; i++) {
			System.out.print("|");
			for (int j = 0; j < 4; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("|");
		}
	}

	public MatrixElement getMatrixElement(int x, int y) {
		if (x>3 || x < 0 || y > 3 || y <0)
			return null;
		return matrix[y][x];
	}
}