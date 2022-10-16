package components.moneySlot;

import components.moneyWallet.Wallet;
import models.money.Money;

public class CoinSlot implements MoneySlot {

    @Override
    public void accept(Money money, int count) {
        Wallet.getWallet().insert(money, count);
    }
}
