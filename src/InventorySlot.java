import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class InventorySlot {

	private static final int INVENTORY_SLOT_WIDTH = 171 / 4;
	private static final int INVENTORY_SLOT_HEIGHT = 254 / 7;
	
	Rectangle rectangleToCapture;
	BufferedImage inventorySlotImage;
	
	public InventorySlot(int row, int column) {
		initializeRectangleToCapture(row, column);
	}
	
	private void initializeRectangleToCapture(int row, int column) {
		rectangleToCapture = new Rectangle(row * INVENTORY_SLOT_WIDTH, column * INVENTORY_SLOT_HEIGHT, INVENTORY_SLOT_WIDTH, INVENTORY_SLOT_HEIGHT);
	}
	
	public void updateInventorySlot(BufferedImage image) throws IOException {
		this.inventorySlotImage = image.getSubimage(rectangleToCapture.x, rectangleToCapture.y, rectangleToCapture.width, rectangleToCapture.height);
	}
	
	public String getItemNameInInventorySlot(InventoryItems items) {
		return items.getNameOfItemFromImage(this.inventorySlotImage);
	}
	
	public boolean isInventorySlotEmpty(InventoryItems items) {
		return ("empty" == items.getNameOfItemFromImage(this.inventorySlotImage));
	}
}
