package Core;
public class Tile {
	private int value;
	
	public Tile(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}