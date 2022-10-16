package components.moneySlot;

public enum SlotType {
    COIN,
    CASH,
    CARD;

    public static SlotType findByIndex(int idx) {
        if (idx < values().length)
            return values()[idx];
        return null;
    }
}
