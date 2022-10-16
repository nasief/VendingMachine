package components.stock;

import models.items.ItemOutOfStockException;

public class ItemStock {

    private Item[][] stock;
    private int rows, columns;

    private static ItemStock inventory = null;

    private ItemStock(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        stock = new Item[rows][columns];
    }

    public static ItemStock getStock(int rows, int columns) {
        if (inventory == null) {
            inventory = new ItemStock(rows, columns);
            inventory.init();
        }
        return inventory;
    }

    public Item getItem(int row, int column) {
        if (stock[row][column] != null && stock[row][column].getQuantity() != 0) {
            return stock[row][column];
        }
        return null;
    }

    public void dispenseItem(Item item) {

    }

    public void dispenseItem(int row, int column) throws ItemOutOfStockException {
        if (getItem(row, column) != null) {
            stock[row][column].setQuantity(stock[row][column].getQuantity() - 1);
        } else {
            throw new ItemOutOfStockException();
        }
    }

    private void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                stock[i][j] = null;
            }
        }
    }

    public void fill(int row, int column, int count, String name, double price) {
        stock[row][column] = new Item(name, price, count);
    }

}
