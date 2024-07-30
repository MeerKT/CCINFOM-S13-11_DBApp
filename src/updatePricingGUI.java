//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * is the class that is responsible for implementing visual interface for the update price functionalities
 */
public class updatePricingGUI extends JFrame {
    /**
     * exit button
     */
    private JButton Exit;
    /**
     * the confirm button
     */
    private JButton confirm;
    /**
     * text field where user types in their new price
     */
    private JTextField newPrice;
    /**
     * hotel where new price will be applied
     */
    private Hotel hotel;

    /**
     * constructor that initializes page size, hotel and visibility of all visual elements
     * @param hotel is the hotel where new price will be applied
     */
    public updatePricingGUI(Hotel hotel) {
        super();

        this.hotel = hotel;

        this.setTitle(this.hotel.getName());
        this.setLayout(new BorderLayout());
        this.initialize();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 450);
        this.setDefaultCloseOperation(3);
    }

    /**
     * initializes all visual elements of the page
     */
    public void initialize() {
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.setBackground(Color.CYAN);
        this.add(south, "South");
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        this.add(center, "Center");
        JPanel north = new JPanel(new FlowLayout());
        north.setBackground(Color.cyan);
        this.add(north, "North");
        JPanel east = new JPanel(new GridBagLayout());
        east.setBackground(Color.cyan);
        this.add(east, "East");
        JPanel west = new JPanel(new GridBagLayout());
        west.setBackground(Color.cyan);
        this.add(west, "West");
        JLabel Title = new JLabel("Change the Base Price of " + this.hotel.getName()+" Here");
        north.add(Title);
        this.Exit = new JButton("Back to Main Menu");
        south.add(this.Exit);
        JPanel lblfld1 = new JPanel(new FlowLayout());
        JLabel prompt = new JLabel("Please input a new base pricing");
        this.newPrice = new JTextField();
        this.newPrice.setColumns(5);
        lblfld1.add(prompt);
        lblfld1.add(this.newPrice);
        center.add(lblfld1, "Center");
        JPanel lblfld2 = new JPanel(new FlowLayout());
        this.confirm = new JButton("CONFIRM");
        lblfld2.add(this.confirm);
        center.add(lblfld2, "South");
    }

    /**
     * function attaches action listener to the buttons in the JFame
     * @param listener is the listener that will be told  when a certain button has been pressed
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.confirm.addActionListener(listener);
    }

    /**
     * returns the text field where the user typed in their new price
     * @return the text field that contains the string representing the hotel's new price
     */
    public JTextField getNewPrice() { return this.newPrice; }
}
