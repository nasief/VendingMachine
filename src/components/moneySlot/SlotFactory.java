package components.moneySlot;

public abstract class SlotFactory {

    public static MoneySlot createSlot(SlotType type) {
        switch (type) {
            case CASH:
                return new CashSlot();
            case COIN:
                return new CoinSlot();
            case CARD:
                return new CardSlot();
            default:
                return null;
        }
    }

}
