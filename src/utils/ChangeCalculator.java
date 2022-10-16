package utils;

import models.money.Money;

import java.util.HashMap;
import java.util.Map;

public class ChangeCalculator {

    public static Map<Money, Integer> calculateChange(double amount) {
        Map<Money, Integer> changeMap = new HashMap<>();

        int _50$ = (int) (amount / 50);
        changeMap.put(Money._50$, _50$);
        amount -= (_50$ * 50);

        int _20$ = (int) (amount / 20);
        changeMap.put(Money._20$, _20$);
        amount -= (_20$ * 20);

        int _1$ = (int) amount;
        changeMap.put(Money._1$, _1$);
        amount -= _1$;


        amount = Math.round(100 * amount) / 100.0;
        amount *= 100;
        int _50c = (int) (amount / 50);
        changeMap.put(Money._50C, _50c);
        amount -= (_50c * 50);

        int _20c = (int) (amount / 20);
        changeMap.put(Money._20C, _20c);
        amount -= (_20c * 20);

        int _10c = (int) (amount / 10);
        changeMap.put(Money._10C, _10c);
        amount -= (_10c * 10);

        return changeMap;
    }

}
