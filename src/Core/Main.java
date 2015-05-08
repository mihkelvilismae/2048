package Core;
public class Main {
	public static Matrix matrix;

	public static void main(String[] args) {
		matrix = new Matrix();
		test();
		matrix.print();
		MatrixElement asd = matrix.getMatrixElement(0, 0).getAdjacentMatrixElementWithTile("left");
		System.out.println(asd.getX() + ", " + asd.getY());
	}

	public static void moveRight() {
		for (int y = 0; y < 4; y++) {
			MatrixElement[] row = matrix.getRow(y);
			for (int x = 0; x < row.length; x++) {

			}
		}
	}

	public static void test() {
		Tile t = new Tile(2);
		Tile t1 = new Tile(2);

		matrix.getMatrixElement(0, 0).setTile(t);
		matrix.getMatrixElement(3, 0).setTile(t1);
		matrix.getMatrixElement(2, 1).setTile(t1);
	}
}