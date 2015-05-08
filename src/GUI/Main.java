package GUI;

import Mihkel.Common;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {
	public static long lastMove = System.currentTimeMillis();
	
	public void start(Stage primaryStage) {
		try {
			GameField.fieldHeight = 4;
			GameField.fieldWidth = 4;
			GameField.tileArray = new Tile[GameField.fieldHeight][GameField.fieldWidth];
			GameField.tileWidth = 100;
			GameField.tileHeight = 100;
			GameField.tilePadding = 5;
			GameField.transitionDelay = 200;
			GameField.setColors();

			for (int i = 0; i<5; i++)
				addTileToGame();
			Scene scene = new Scene(GameField.tileGroup, GameField.fieldWidth * GameField.tileWidth, GameField.fieldHeight * GameField.tileHeight);

			scene.setOnKeyPressed(event -> keyPressed(event.getCode()));
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void keyPressed(KeyCode key) {
		if (lastMove + GameField.transitionDelay > System.currentTimeMillis())
			return;
		GameField.moveAllTo(key.getName());
		lastMove = System.currentTimeMillis();
		System.out.println("lõpp");
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void addTileToGame() {
		int x;
		int y;
		do {
			x = Common.randInt(0,3);
			y = Common.randInt(0,3);
		}
		while (GameField.getTileAt(x, y)!=null);
		System.out.println("x: "  + x + " y: "+y);
		new Tile(x, y, 2);
	}
	
//	public void initializeMatrix(Matrix m) {
//		for (int i = 0; i < m.matrix.length; i++) {
//			for (int j = 0; j < m.matrix[0].length; j++) {
//				
//			}
//		}
//	}
}