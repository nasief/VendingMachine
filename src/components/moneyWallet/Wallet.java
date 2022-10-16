package components.moneyWallet;

import models.money.InsufficientFundException;
import models.money.Money;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private Map<Money, Integer> money;

    private static Wallet wallet = new Wallet();

    private Wallet() {
        money = new HashMap<>();
    }

    public static Wallet getWallet() {
        return wallet;
    }

    public void insert(Money type, int amount) {
        if (money.containsKey(type)) {
            money.put(type, money.get(type) + amount);
        } else {
            money.put(type, amount);
        }
    }

    public int dispense(Money type, int amount) throws InsufficientFundException {
        if (money.containsKey(type)) {
            int exitingAmount = money.get(type);
            if (exitingAmount >= amount) {
                money.put(type, exitingAmount - amount);
                return exitingAmount - amount;
            }
        }
        throw new InsufficientFundException();
    }

    public void init(int _50$, int _20$, int _1$, int _50c, int _20c, int _10c) {
        money.put(Money._20$, _20$);
        money.put(Money._50$, _50$);
        money.put(Money._20C, _20c);
        money.put(Money._1$, _1$);
        money.put(Money._10C, _10c);
        money.put(Money._50C, _50c);
    }

}
