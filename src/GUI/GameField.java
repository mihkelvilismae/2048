package GUI;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameField {
	
	public static Map<Integer, Color> tileColors = new HashMap<Integer, Color>();
	public static int fieldWidth, fieldHeight, tileWidth, tileHeight, tilePadding, transitionDelay;
	public static Group tileGroup = new Group();
	public static Tile[][] tileArray;
	
	public static void moveAllTo(String direction) {
		switch (direction) {
		case "Left":
			for (int i = 0; i < GameField.fieldWidth; i++) {
				for (int j = 0; j < GameField.fieldHeight; j++) {
					if (GameField.getTileAt(i, j) == null) {
						continue;
					}
					GameField.getTileAt(i, j).moveLeft();
				}
			}
			break;
		case "Right":
			for (int i = 0; i < GameField.fieldWidth; i++) {
				for (int j = 0; j < GameField.fieldHeight; j++) {
					if (GameField.getTileAt(GameField.fieldWidth-1 - i, j) == null) {
						continue;
					}
					GameField.getTileAt(GameField.fieldWidth-1-i,j).moveRight();
				}
			}
			break;
		case "Up":
			for (int i = 0; i < GameField.fieldHeight; i++) {
				for (int j = 0; j < GameField.fieldWidth; j++) {
					if (GameField.getTileAt(j,i) == null) {
						continue;
					}
					GameField.getTileAt(j,i).moveUp();
				}
			}
			break;
		case "Down":
			for (int i = 0; i < GameField.fieldHeight; i++) {
				for (int j = 0; j < GameField.fieldWidth; j++) {
					if (GameField.getTileAt(j,GameField.fieldHeight-1-i) == null) {
						continue;
					}
					GameField.getTileAt(j,GameField.fieldHeight-1-i).moveDown();
				}
			}
			break;
		default:
			return;		
		}
		//		GameField.printField();
		waitAndAddTile();
	}

	public static void waitAndAddTile() {
		Group g = new Group();
		TranslateTransition trans = new TranslateTransition(new Duration(GameField.transitionDelay), g);
		trans.setOnFinished(event -> Main.addTileToGame());
		trans.play();



	}
	public static void printField() {
		for (int i = 0; i < GameField.tileGroup.getChildren().size(); i++) {
			System.out.println(GameField.tileGroup.getChildren().get(i));
		}
		for (int i = 0; i < GameField.fieldHeight; i++) {
			for (int j = 0; j < GameField.fieldWidth; j++) {
				if (GameField.getTileAt(j, i)!= null) {
					System.out.print(" " + GameField.getTileAt(j, i));
				} else {
					System.out.print(" " + 0);
				}
			}
			System.out.println();
		}
	}
	
	public static void setColors() {
		GameField.tileColors.put(2, Color.RED);
		GameField.tileColors.put(4, Color.PINK);
		GameField.tileColors.put(8, Color.BLUE);
		GameField.tileColors.put(16, Color.BLUE);
		GameField.tileColors.put(32, Color.BLUE);
		GameField.tileColors.put(64, Color.BLUE);
		GameField.tileColors.put(128, Color.BLUE);
		GameField.tileColors.put(256, Color.BLUE);
		GameField.tileColors.put(512, Color.BLUE);
		GameField.tileColors.put(1024, Color.BLUE);
		GameField.tileColors.put(2048, Color.BLUE);
		GameField.tileColors.put(4096, Color.BLUE);
	}
	
	public static void addTile(Tile tile) {
		GameField.tileGroup.getChildren().add(tile.tile);
	}
	
	public static void removeTile(Tile tile) {
		GameField.tileGroup.getChildren().remove(tile.tile);
	}
	
	public static Tile getTileAt(int x, int y) {
		return tileArray[y][x];
	}
	
	public static void setTileAt(int x, int y, Tile tile) {
		tileArray[y][x] = tile;
	}
}