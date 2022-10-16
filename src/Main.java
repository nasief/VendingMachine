import components.moneySlot.SlotType;
import components.stock.Item;
import machine.MachineType;
import machine.VendingMachine;
import machine.VendingMachineFactory;
import models.money.Money;
import utils.ChangeCalculator;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        VendingMachine snackMachine = VendingMachineFactory.createMachine(MachineType.SNACK);
        snackMachine.initComponents(5, 5);
        snackMachine.initWallet(10, 10, 10, 10, 10, 10);
        snackMachine.refill(3, 4, "Bounty", 2.20, 5);
        snackMachine.refill(2, 5, "Snickers", 2.20, 5);
        snackMachine.refill(1, 1, "Twix", 1.8, 5);
        snackMachine.acceptCoins(3, 1, 3, 0);
        Scanner scanner = new Scanner(System.in);

        do {
            String number = "";
            int row = 0, column = 0;
            do {
                System.out.print("Input Item Number > ");
                number = scanner.nextLine();
                if (number.length() != 2) {
                    System.out.printf("Invalid Input\n", number);
                }
                row = Integer.parseInt(number) / 10;
                column = Integer.parseInt(number) % 10;
                if (row < 1 || row > 5 || column < 1 || column > 5) {
                    System.out.printf("Invalid Input\n", number);
                }
            } while (number.length() != 2 || row < 1 || row > 5 || column < 1 || column > 5);

            snackMachine.pressKey(row);
            snackMachine.pressKey(column);

            Item item = snackMachine.getItem(row, column);
            if (item != null) {
                SlotType slotToUse;
                int more;
                double totalAmount = 0;
                do {
                    slotToUse = null;
                    System.out.println(String.format("Item %s is available. Price is $%.2f", item.getName(), item.getPrice()));
                    System.out.println("Please select Payment Method > ");
                    System.out.println("0. Coin");
                    System.out.println("1. Cash");
                    System.out.println("2. Card");

                    int paymentMethod = scanner.nextInt();
                    slotToUse = SlotType.findByIndex(paymentMethod);
                    if (slotToUse != null) {
                        switch (slotToUse) {
                            case CARD: {
                                break;
                            }
                            case CASH: {
                                System.out.println("Please Input number of $50 > ");
                                int _50$ = scanner.nextInt();
                                if (_50$ != 0) {
                                    totalAmount += _50$ * 50.0;
                                    System.out.printf("Total: %.2f%n", totalAmount);
                                }
                                System.out.println("Please Input number of $20 > ");
                                int _20$ = scanner.nextInt();
                                if (_20$ != 0) {
                                    totalAmount += _20$ * 20.0;
                                    System.out.printf("Total: %.2f%n", totalAmount);
                                }
                                snackMachine.acceptCash(_50$, _20$);
                                break;
                            }
                            case COIN: {
                                System.out.println("Please Input number of $1 > ");
                                int _1$ = scanner.nextInt();
                                if (_1$ != 0) {
                                    totalAmount += _1$ * 1.0;
                                    System.out.printf("Total: %.2f%n", totalAmount);
                                }
                                System.out.println("Please Input number of 20 cents > ");
                                int _20c = scanner.nextInt();
                                if (_20c != 0) {
                                    totalAmount += _20c * 0.2;
                                    System.out.printf("Total: %.2f%n", totalAmount);
                                }
                                System.out.println("Please Input number of 10 cents > ");
                                int _10c = scanner.nextInt();
                                if (_10c != 0) {
                                    totalAmount += _10c * 0.1;
                                    System.out.printf("Total: %.2f%n", totalAmount);
                                }
                                System.out.println("Please Input number of 50 cents > ");
                                int _50c = scanner.nextInt();
                                if (_50c != 0) {
                                    totalAmount += _50c * 0.5;
                                    System.out.printf("Total: %.2f%n", totalAmount);
                                }
                                snackMachine.acceptCoins(_1$, _50c, _20c, _10c);
                                break;
                            }
                        }
                    }
                    if (totalAmount < item.getPrice()) {
                        System.out.println("Amount is not enough! Insert More.");
                    }
                } while (slotToUse != null && totalAmount < item.getPrice());

                double totalChange = Math.round(10 * (totalAmount - item.getPrice())) / 10.0;
                boolean dispenable = false;
                if (totalChange != 0) {
                    System.out.printf("Change to be returned is %.2f\n", totalChange);
                    Map<Money, Integer> change = ChangeCalculator.calculateChange(totalChange);
                    if (snackMachine.dispenseChange(change)) {
                        dispenable = true;
                        System.out.println("Change to be dispensed");
                        if (change.get(Money._50$) != 0) {
                            System.out.printf("$50: %d\n", change.get(Money._50$));
                        }
                        if (change.get(Money._20$) != 0) {
                            System.out.printf("$20: %d\n", change.get(Money._20$));
                        }
                        if (change.get(Money._50C) != 0) {
                            System.out.printf("50c: %d\n", change.get(Money._50C));
                        }
                        if (change.get(Money._10C) != 0) {
                            System.out.printf("10c: %d\n", change.get(Money._10C));
                        }
                        if (change.get(Money._20C) != 0) {
                            System.out.printf("20c: %d\n", change.get(Money._20C));
                        }
                        if (change.get(Money._1$) != 0) {
                            System.out.printf("$1: %d\n", change.get(Money._1$));
                        }
                    }
                } else {
                    System.out.println("No Change remaining");
                    dispenable = true;
                }

                if (dispenable) {
                    snackMachine.dispense(item);
                    System.out.printf("Item %s is dispensed.\n", item.getName());
                } else {
                    System.out.println("Item was not dispensed because of no sufficient change");
                }
            } else {
                System.out.println("Item not available!");
            }
            number = scanner.nextLine();
        } while (true);
    }
}