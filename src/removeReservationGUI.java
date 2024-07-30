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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * class responsible for implementing a visual interface for the remove reservation functionalities
 */
public class removeReservationGUI extends JFrame {
    /**
     * exit button
     */
    private JButton Exit;
    /**
     * confirm button that applies changes
     */
    private JButton confirm;
    /**
     * dropdown meny that contains all reservation names
     */
    private JComboBox<String> reservationList;
    /**
     * hotel that will remove selected reservation
     */
    private Hotel hotel;

    /**
     * constructor that initializes selected hotel, visibility of selected elements and  page size
     * @param hotel is the hotel that will remove the reservation
     */
    public removeReservationGUI(Hotel hotel) {
        super();
        this.hotel = hotel;
        this.setTitle(this.hotel.getName());
        this.setLayout(new BorderLayout());
        this.initialize();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 200);
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
        JLabel Title = new JLabel("Remove Individual Reservations here");
        north.add(Title);
        this.Exit = new JButton("Back to Main Menu");
        south.add(this.Exit);
        JPanel lblfld1 = new JPanel(new FlowLayout());
        JLabel remove = new JLabel("Whose reservation would you like to remove?");

        this.reservationList = new JComboBox<String>();
        reservationList.addItem("PLEASE SELECT A RESERVATION");
        for(int i = 0; i < this.hotel.getReservationCount();i++)
        {
            this.reservationList.addItem(this.hotel.getReservationList().get(i).getName());
        }

        lblfld1.add(remove);
        lblfld1.add(this.reservationList);
        center.add(lblfld1, "Center");
        JPanel lblfld2 = new JPanel(new FlowLayout());
        this.confirm = new JButton("CONFIRM");
        lblfld2.add(this.confirm);
        center.add(lblfld2, "South");
    }

    /**
     * adds an action listener to all buttons in the page
     * @param listener is the object that will perform some action based on the button that is pressed
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.confirm.addActionListener(listener);
    }

    /**
     * returns the selected reservation of the reservation dropdown menu
     * @return an int value that represents the index of the selected value in reservationList
     */
    public int getReservation() { return this.reservationList.getSelectedIndex(); }
}
