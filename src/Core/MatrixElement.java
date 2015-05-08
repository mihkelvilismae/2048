package Core;
public class MatrixElement {
	private int x, y;
	private Tile tile;
	private Matrix matrix;

	public MatrixElement(int x, int y, Matrix matrix) {
		this.x = x;
		this.y = y;
		this.matrix = matrix;
	}

	public MatrixElement getAdjacentMatrixElement(String direction) {
		if (direction.equals(this.matrix.RIGHT)) {
			return this.matrix.getMatrixElement(this.x + 1, this.y);
		} else if (direction.equals(this.matrix.LEFT)) {
			return this.matrix.getMatrixElement(this.x - 1, this.y);
		} else if (direction.equals(this.matrix.UP)) {
			return this.matrix.getMatrixElement(this.x, this.y - 1);
		} else if (direction.equals(this.matrix.DOWN)) {
			return this.matrix.getMatrixElement(this.x, this.y + 1);
		}
		return null;
	}

	public MatrixElement getAdjacentMatrixElementWithTile(String direction) {
		MatrixElement currentElement = this;
		do {
			currentElement = currentElement.getAdjacentMatrixElement(direction);
			if (currentElement==null)
				throw new RuntimeException("joudis ‰‰raneie");
		} while (currentElement.tile==null & currentElement.getAdjacentMatrixElement(direction) != null);
		if (currentElement.tile != null)
			return currentElement;
		else 
			throw new RuntimeException("joudis ‰‰raneie");
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Tile getTile() {
		return tile;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		//		return "[" + x + "," + y + "]";
		if (tile == null) {
			return " ";
		} else {
			return tile.toString();
		}
	}
}