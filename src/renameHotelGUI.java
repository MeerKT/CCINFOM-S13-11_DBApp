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
 * class responsible for implementing visual interface for rename hotel functionalities
 */
public class renameHotelGUI extends JFrame {
    /**
     * exit button
     */
    private JButton Exit;
    /**
     * confirm button
     */
    private JButton rename;
    /**
     * text field where user types in new name
     */
    private JTextField newName;
    /**
     * hotel that will be renamed
     */
    private Hotel hotel;

    /**
     * constructor that initializes the hotel, page size, and visibility of visual elements
     * @param hotel is the hotel that will be renamed
     */
    public renameHotelGUI(Hotel hotel) {
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
     * initializes all visual elements
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
        JPanel east = new JPanel(new GridBagLayout());
        east.setBackground(Color.cyan);
        this.add(east, "East");
        JPanel west = new JPanel(new GridBagLayout());
        west.setBackground(Color.cyan);
        this.add(west, "West");
        JLabel Title = new JLabel("Rename " + this.hotel.getName());
        north.add(Title);
        this.Exit = new JButton("Back to Main Managing Menu");
        south.add(this.Exit);
        JPanel lblfld1 = new JPanel(new FlowLayout());
        JLabel Name = new JLabel("<html> Please type the new name of the hotel</html>");
        this.newName = new JTextField();
        this.newName.setColumns(20);
        lblfld1.add(Name);
        lblfld1.add(this.newName);
        center.add(lblfld1);
        JPanel BtnField = new JPanel(new FlowLayout());
        this.rename = new JButton("CONFIRM");
        BtnField.add(this.rename);
        center.add(BtnField);
    }

    /**
     * returns the text field where the user types in the new name for the hotel
     * @return the text field that contains the string that represents the new name of the hotel
     */
    public JTextField getnewName() { return this.newName; }

    /**
     * adds action listeners to all buttons in the page
     * @param listener is the object that will perform some action based on the button that is pressed
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.rename.addActionListener(listener);
    }
}
