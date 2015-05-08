package GUI;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Tile {
	public int x, y, previousX, previousY, value;
	public boolean hasMerged = false;
	public boolean destroy = false;
	public Group tile;
	public TranslateTransition[] moveTransitions;

	public Tile(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;

		this.createTile();
		this.createAnimations();

		GameField.setTileAt(this.x, this.y, this);
		GameField.addTile(this);
	}

	private void createTile() {
		Rectangle rect = new Rectangle(this.x * GameField.tileWidth + GameField.tilePadding / 2, this.y * GameField.tileHeight + GameField.tilePadding / 2, GameField.tileWidth - GameField.tilePadding, GameField.tileHeight - GameField.tilePadding);
		rect.setFill(GameField.tileColors.get(this.value));

		int fontSize = 40;
		Text text = new Text(String.valueOf(this.value));
		do {
			text.setFont(new Font(fontSize));
			text.setLayoutX(this.x * GameField.tileWidth + GameField.tileWidth / 2 - text.getLayoutBounds().getWidth() / 2);
			text.setLayoutY(this.y * GameField.tileHeight + GameField.tileHeight / 2 + text.getLayoutBounds().getHeight() / 4);
			fontSize -= 5;
		} while (text.getLayoutBounds().getWidth() >= GameField.tileWidth);

		this.tile = new Group(rect, text);
		this.createAnimations();
	}

	public void createAnimations() {
		moveTransitions = new TranslateTransition[(GameField.fieldWidth + GameField.fieldHeight) * 2 - 2];
		int currentPosition = 0;
		for (int i = 1 - GameField.fieldWidth; i < GameField.fieldWidth; i++) {
			this.moveTransitions[currentPosition] = new TranslateTransition(Duration.millis(GameField.transitionDelay), this.tile);
			this.moveTransitions[currentPosition].setByX(GameField.tileWidth * i);
			this.moveTransitions[currentPosition].setOnFinished(event -> this.finishMove(this));
			currentPosition++;
		}
		for (int i = 1 - GameField.fieldHeight; i < GameField.fieldHeight; i++) {
			this.moveTransitions[currentPosition] = new TranslateTransition(Duration.millis(GameField.transitionDelay), this.tile);
			this.moveTransitions[currentPosition].setByY(GameField.tileWidth * i);
			this.moveTransitions[currentPosition].setOnFinished(event -> this.finishMove(this));
			currentPosition++;
		}
	}

	public void finishMove(Tile a) { 
		if (this.destroy) {
			GameField.removeTile(this);
			GameField.setTileAt(this.x, this.y, null);
			GameField.setTileAt(this.previousX, this.previousY, null);
			return;
		}
		if (this.hasMerged) {
			GameField.removeTile(this);
			GameField.setTileAt(this.previousX, this.previousY, null);
			GameField.setTileAt(this.x, this.y, null);
			this.setValue(this.value * 2);
			this.hasMerged = false;
		}
	}

	public void moveLeft() {
		if (this.x == 0) {
			System.out.println("Play left anim" + 3);
			this.moveTransitions[3].play();
			return;
		}

		int moveAmount = 0;
		for (int i = this.x - 1; i >= 0; i--) {
			if (GameField.getTileAt(i, this.y) != null) {
				if (GameField.getTileAt(i, this.y).value == this.value && !GameField.getTileAt(i, this.y).hasMerged) {
					moveAmount = GameField.getTileAt(i, this.y).x - this.x;
					this.hasMerged = true;
					GameField.getTileAt(i, this.y).hasMerged = true;
					GameField.getTileAt(i, this.y).destroy = true;
				}
				break;
			} else {
				moveAmount--;
			}
		}
		System.out.println("Play left anim" + (moveAmount + GameField.fieldWidth - 1));
		this.moveTransitions[moveAmount + GameField.fieldWidth - 1].play();
		GameField.setTileAt(this.x, this.y, null);
		this.previousX = this.x;
		this.x += moveAmount;
		GameField.setTileAt(this.x, this.y, this);
	}

	public void moveRight() {
		if (this.x == GameField.fieldWidth - 1) {
			this.moveTransitions[3].play();
			return;
		}

		int moveAmount = 0;
		for (int i = this.x + 1; i < GameField.fieldWidth; i++) {
			if (GameField.getTileAt(i, this.y) != null) {
				if (GameField.getTileAt(i, this.y).value == this.value && !GameField.getTileAt(i, this.y).hasMerged) {
					moveAmount = GameField.getTileAt(i, this.y).x - this.x;
					this.hasMerged = true;
					GameField.getTileAt(i, this.y).hasMerged = true;
					GameField.getTileAt(i, this.y).destroy = true;
				}
				break;
			} else {
				moveAmount++;
			}
		}
		GameField.setTileAt(this.x, this.y, null);
		this.previousX = this.x;
		this.x += moveAmount;
		GameField.setTileAt(this.x, this.y, this);
		this.moveTransitions[moveAmount + GameField.fieldWidth - 1].play();
	}

	public void moveUp() {
		if (this.y == 0) {
			this.moveTransitions[3].play();
			return;
		}

		int moveAmount = 0;
		for (int i = this.y - 1; i >= 0; i--) {
			if (GameField.getTileAt(this.x, i) != null) {
				if (GameField.getTileAt(this.x, i).value == this.value && !GameField.getTileAt(this.x, i).hasMerged) {
					moveAmount = GameField.getTileAt(this.x, i).y - this.y;
					this.hasMerged = true;
					GameField.getTileAt(this.x, i).hasMerged = true;
					GameField.getTileAt(this.x, i).destroy = true;
				}
				break;
			} else {
				moveAmount--;
			}
		}
		this.moveTransitions[GameField.fieldWidth * 2 - 1 + moveAmount + GameField.fieldHeight - 1].play();
		GameField.setTileAt(this.x, this.y, null);
		this.previousY = this.y;
		this.y += moveAmount;
		GameField.setTileAt(this.x, this.y, this);
	}

	public void moveDown() {
		if (this.y == GameField.fieldHeight - 1) {
			this.moveTransitions[3].play();
			return;
		}

		int moveAmount = 0;
		for (int i = this.y + 1; i < GameField.fieldHeight; i++) {
			if (GameField.getTileAt(this.x, i) != null) {
				if (GameField.getTileAt(this.x, i).value == this.value && !GameField.getTileAt(this.x, i).hasMerged) {
					moveAmount = GameField.getTileAt(this.x, i).y - this.y;
					this.hasMerged = true;
					GameField.getTileAt(this.x, i).hasMerged = true;
					GameField.getTileAt(this.x, i).destroy = true;
				}
				break;
			} else {
				moveAmount++;
			}
		}
		this.moveTransitions[GameField.fieldWidth * 2 - 1 + moveAmount + GameField.fieldHeight - 1].play();
		GameField.setTileAt(this.x, this.y, null);
		this.previousY = this.y;
		this.y += moveAmount;
		GameField.setTileAt(this.x, this.y, this);
	}

	public void setValue(int value) {
		GameField.removeTile(this);
		this.value = value;
		this.createTile();
		GameField.addTile(this);
		GameField.setTileAt(this.x, this.y, this);
	}
}