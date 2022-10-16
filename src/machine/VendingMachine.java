package machine;

import components.keybad.Keybad;
import components.moneySlot.MoneySlot;
import components.moneySlot.SlotFactory;
import components.moneySlot.SlotType;
import components.moneyWallet.Wallet;
import components.stock.Item;
import components.stock.ItemStock;
import models.money.InsufficientFundException;
import models.money.Money;

import java.util.Map;

public abstract class VendingMachine implements IVendingMachine {

    private MoneySlot coinSlot, cashSlot, cardSlot;
    private Keybad keybad;
    private Wallet wallet;
    private ItemStock stock;

    @Override
    public void initComponents(int rows, int columns) {
        coinSlot = SlotFactory.createSlot(SlotType.COIN);
        cashSlot = SlotFactory.createSlot(SlotType.CASH);
        cardSlot = SlotFactory.createSlot(SlotType.CARD);
        keybad = new Keybad();
        wallet = Wallet.getWallet();
        stock = ItemStock.getStock(rows, columns);
    }

    @Override
    public void initWallet(int _50$, int _20$, int _1$, int _50c, int _20c, int _10c) {
        wallet.init(_50$, _20$, _1$, _50c, _20c, _10c);
    }

    public void refill(int row, int clmn, String name, double price, int quantity) {
        stock.fill(row - 1, clmn - 1, quantity, name, price);
    }

    @Override
    public void acceptCard() {

    }

    public void pressKey(int key) {
        keybad.press(key);
    }

    public Item getItem(int row, int column) {
        return this.stock.getItem(row, column);
    }

    @Override
    public void acceptCoins(int _1$, int _50c, int _20c, int _10c) {
        if (_1$ != 0) {
            coinSlot.accept(Money._1$, _1$);
        }

        if (_50c != 0) {
            coinSlot.accept(Money._50C, _50c);
        }

        if (_20c != 0) {
            coinSlot.accept(Money._20C, _20c);
        }

        if (_10c != 0) {
            coinSlot.accept(Money._10C, _10c);
        }
    }

    @Override
    public void acceptCash(int _50$, int _20$) {
        if (_50$ != 0) {
            cashSlot.accept(Money._50$, _50$);
        }

        if (_20$ != 0) {
            cashSlot.accept(Money._20$, _20$);
        }
    }

    public void dispense(Item item) {
        item.setQuantity(item.getQuantity() - 1);
    }

    public boolean dispenseChange(Map<Money, Integer> change) {
        try {
            wallet.dispense(Money._50$, change.get(Money._50$));
        } catch (InsufficientFundException ex) {
            wallet.insert(Money._50$, change.get(Money._50$));
            return false;
        }

        try {
            wallet.dispense(Money._20$, change.get(Money._20$));
        } catch (InsufficientFundException ex) {
            wallet.insert(Money._20$, change.get(Money._20$));
            return false;
        }

        try {
            wallet.dispense(Money._50C, change.get(Money._50C));
        } catch (InsufficientFundException ex) {
            wallet.insert(Money._50C, change.get(Money._50C));
            return false;
        }

        try {
            wallet.dispense(Money._20C, change.get(Money._20C));
        } catch (InsufficientFundException ex) {
            wallet.insert(Money._20C, change.get(Money._20C));
            return false;
        }

        try {
            wallet.dispense(Money._1$, change.get(Money._1$));
        } catch (InsufficientFundException ex) {
            wallet.insert(Money._1$, change.get(Money._1$));
            return false;
        }

        try {
            wallet.dispense(Money._10C, change.get(Money._10C));
        } catch (InsufficientFundException ex) {
            wallet.insert(Money._10C, change.get(Money._10C));
            return false;
        }

        return true;
    }

}
