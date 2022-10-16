package machine;

public abstract class VendingMachineFactory {

    public static VendingMachine createMachine(MachineType type) {
        switch (type) {
            case DRINK:
                return new DrinkMachine();
            case SNACK:
                return new SnackMachine();
            case COFFEE:
                return new CoffeeMachine();
            default:
                return null;
        }
    }

}
