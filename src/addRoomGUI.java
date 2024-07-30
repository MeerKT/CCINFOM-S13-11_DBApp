

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * visual interface for the addRoom functionalities of the program
 */
public class addRoomGUI extends JFrame {
    /**
     * the exit button
     */
    private JButton Exit;
    /**
     * the confirm button
     */
    private JButton confirm;
    /**
     * text field where the user types how many rooms they would like to add
     */
    private JTextField Amnt;
    /**
     * dropdown where the user chooses what kind of room that they want
     */
    private JComboBox<String> roomOption;
    /**
     * the hotel where these rooms are added to
     */
    private Hotel hotel;

    /**
     * constructor that initializes the hotel that will receive the rooms, page size and visibility of visual elements
     * @param hotel is the hotel that the generated rooms will be added to
     */
    public addRoomGUI(Hotel hotel) {
        super("Add Rooms");
        this.setLayout(new BorderLayout());
        this.hotel = hotel;
        this.initialize();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 450);
        this.setDefaultCloseOperation(3);
    }

    /**
     * initialize function that is responsible for creating all the visual elements of the page
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
        JLabel Title = new JLabel("Add and/or Remove Rooms here");
        north.add(Title);
        this.Exit = new JButton("Back to Main Menu");
        south.add(this.Exit);
        JPanel lblfld1 = new JPanel(new FlowLayout());
        JLabel addAmnt = new JLabel("How many rooms would you like to add?");
        this.Amnt = new JTextField();
        this.Amnt.setColumns(5);
        lblfld1.add(addAmnt);
        lblfld1.add(this.Amnt);
        JLabel type = new JLabel("What kind of rooms would you like to add?");
        String[] options = new String[]{"None", "Regular", "Deluxe", "Executive"};
        this.roomOption = new JComboBox(options);
        lblfld1.add(type);
        lblfld1.add(this.roomOption);
        center.add(lblfld1, "Center");
        JPanel lblfld2 = new JPanel(new FlowLayout());
        this.confirm = new JButton("CONFIRM");
        lblfld2.add(this.confirm);
        center.add(lblfld2, "South");
    }

    /**
     * function that attaches an action listener to all the buttons in the page
     * @param listener is the object that will perform corresponding actions based on the button that is pressed
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.confirm.addActionListener(listener);
    }

    /**
     * function that returns the JTextfield with the anount of rooms
     * @return the JTextfield where the user typed the amount of rooms they want added to the hotel
     */
    public JTextField getAmnt() {  return this.Amnt; }

    /**
     * function that returns the index of the roomOption JCombobox
     * @return an int value that represents the index of the selected option of the roomOption dropdown menu
     */
    public int getroomOption() { return this.roomOption.getSelectedIndex(); }

    /**
     * function that returns the hotel that was passed to this class
     * @return a Hotel that represents the hotel that was passed upon this page's initialization
     */
    public Hotel getHotel() {return this.hotel;}
}
