//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * class that is responsible for implementing a visual interface for the main menu
 */
public class mainMenuGUI extends JFrame {
    /**
     * button that brings user to add hotel menu
     */
    private JButton addHotel;
    /**
     * button that brings user to view hotel menu
     */
    private JButton viewHotel;
    /**
     * button that brings user to manage hotel menu
     */
    private JButton manageHotel;
    /**
     * button that brings user to the reservation menu
     */
    private JButton simulateBooking;
    /**
     * button that terminates the program
     */
    private JButton exit;

    /**
     * function that initializes page size and visibility of all visual elements
     */
    public mainMenuGUI() {
        super("HotelGUI");
        this.setLayout(new BorderLayout());
        this.initialize();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(300, 450);
        this.setDefaultCloseOperation(3);
    }

    /**
     * function that initializes all visual elements of the page
     */
    public void initialize() {
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.setBackground(Color.CYAN);
        this.add(south, "South");
        JPanel center = new JPanel();
        center.setLayout(new FlowLayout());
        this.add(center, "Center");
        JPanel north = new JPanel(new FlowLayout());
        north.setBackground(Color.cyan);
        this.add(north, "North");
        this.addHotel = new JButton("1 - ADD HOTEL");
        this.viewHotel = new JButton("2 - VIEW HOTEL");
        this.manageHotel = new JButton("3 - MANAGE HOTEL");
        this.simulateBooking = new JButton("4 - SIMULATE BOOKING");
        this.exit = new JButton("5 - EXIT");
        JPanel btn1 = new JPanel();
        btn1.add(this.addHotel);
        JPanel btn2 = new JPanel();
        btn2.add(this.viewHotel);
        JPanel btn3 = new JPanel();
        btn3.add(this.manageHotel);
        JPanel btn4 = new JPanel();
        btn4.add(this.simulateBooking);
        JPanel btn5 = new JPanel();
        btn5.add(this.exit);
        JPanel holder = new JPanel(new GridLayout(5, 1));
        holder.add(btn1);
        holder.add(btn2);
        holder.add(btn3);
        holder.add(btn4);
        holder.add(btn5);
        center.add(holder);
        JLabel Title = new JLabel("Welcome to Hotel Manager!");
        north.add(Title);
    }

    /**
     * function that adds action listeners to all buttons in the page
     * @param listener is the object that performs actions based on the action listener
     */
    public void setActionListener(ActionListener listener) {
        this.addHotel.addActionListener(listener);
        this.viewHotel.addActionListener(listener);
        this.manageHotel.addActionListener(listener);
        this.simulateBooking.addActionListener(listener);
        this.exit.addActionListener(listener);
    }
}
