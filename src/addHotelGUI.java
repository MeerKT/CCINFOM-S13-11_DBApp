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
import javax.swing.event.DocumentListener;

/**
 * visual interface for the addHotel functionalities
 */
public class addHotelGUI extends JFrame {
    /**
     * the exit button
     */
    private JButton exitAH;
    /**
     * the confirm button
     */
    private JButton confirmAmount;
    /**
     * textfield where user types out the new name
     */
    private JTextField newName;
    /**
     * textfield where user types out the amount of rooms in the new hotel
     */
    private JTextField roomAmnt;

    /**
     * constructor that sets page size and visibility of visual elements
     */
    public addHotelGUI() {
        super("addHotel");
        this.setLayout(new BorderLayout());
        this.initialize();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 450);
        this.setDefaultCloseOperation(3);
    }

    /**
     * initialize function initializes all the visual elements of the addHotel page
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
        JLabel Title = new JLabel("Welcome to the Hotel Creator!");
        north.add(Title);
        JPanel lblfld1 = new JPanel(new FlowLayout());
        JLabel Name = new JLabel("Hotel Name:");
        this.newName = new JTextField();
        this.newName.setColumns(20);
        lblfld1.add(Name);
        lblfld1.add(this.newName);
        center.add(lblfld1, "North");
        JPanel lblfld2 = new JPanel(new FlowLayout());
        JLabel amnt = new JLabel("Number of Rooms:");
        this.roomAmnt = new JTextField();
        this.roomAmnt.setColumns(20);
        lblfld2.add(amnt);
        lblfld2.add(this.roomAmnt);
        center.add(lblfld2, "Center");
        JPanel lblfld3 = new JPanel(new FlowLayout());
        this.confirmAmount = new JButton("CONFIRM");
        lblfld3.add(this.confirmAmount);
        center.add(lblfld3, "South");
        this.exitAH = new JButton("Back to Main Menu");
        south.add(this.exitAH);
    }

    /**
     * function that adds an action listener to all buttons in the page
     * @param listener is the object that does some action whenever a button is pressed
     */
    public void setActionListener(ActionListener listener) {
        this.exitAH.addActionListener(listener);
        this.confirmAmount.addActionListener(listener);
    }


    /**
     * function that returns the text field that contains the new name
     * @return a JTextfield that contains whatever string value was typed into the newName textfield
     */
    public JTextField getNewName() {
        return this.newName;
    }

    /**
     * function that returns the text field that contains the amount of rooms
     * @return a JTextfield that contains whatever string value was typed into the roomAmnt textfield
     */
    public JTextField getRoomAmnt()
    {
        return this.roomAmnt;
    }
}
