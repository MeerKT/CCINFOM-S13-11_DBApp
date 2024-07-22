import java.util.Scanner;

/**
 * Houses the main method.
 */
public class Driver{
    public static void main(String[] args) {
        HotelManager manager = new HotelManager();
        mainMenuGUI main = new mainMenuGUI();
        Controller controller = new Controller(main, manager);
    }
}