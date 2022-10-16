package machine;

import components.stock.Item;
import models.money.Money;

import java.util.Map;

public interface IVendingMachine {

    void initComponents(int rows, int columns);

    void initWallet(int _50$, int _20$, int _1$, int _50c, int _20c, int _10c);

    void acceptCard();

    void acceptCoins(int _1$, int _50c, int _20c, int _10c);

    void acceptCash(int _50$, int _20$);

    void dispense(Item item);

    boolean dispenseChange(Map<Money, Integer> change);
}