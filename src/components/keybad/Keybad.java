package components.keybad;

public class Keybad {

    private static int NOT_PRESSED = -1;
    public static int SELECTED = 1;
    private static int NEXT = 0;

    private int[] pressed = {NOT_PRESSED, NOT_PRESSED};

    public int press(int key) {
        if (pressed[0] == NOT_PRESSED) {
            pressed[0] = key;
        } else if (pressed[1] == NOT_PRESSED) {
            pressed[1] = key;
            return SELECTED;
        }
        return NEXT;
    }
}
