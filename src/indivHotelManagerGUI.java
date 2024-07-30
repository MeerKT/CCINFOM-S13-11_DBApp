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
 * class responsible for displaying the graphical interface that allows the user to select how they want to modify a hotel
 */
public class indivHotelManagerGUI extends JFrame {

    /**
     * button that brings user to rename hotel menu
     */
    private JButton rename;
    /**
     * button that brings user to add room menu
     */
    private JButton roomAdd;
    /**
     * button that brings user to remove room menu
     */
    private JButton roomRemove;
    /**
     * button that brings user to the update price menu
     */
    private JButton updatePrice;
    /**
     * button that brings user to the remove reservation menu
     */
    private JButton removeRes;
    /**
     * button that brings user to modify price per date menu
     */
    private JButton modifyPrice;
    /**
     * button that removes hotel
     */
    private JButton removeHotel;
    /**
     * exit button
     */
    private JButton Exit;
    /**
     * Hotel that will be modified
     */
    private Hotel hotelOfInterest;

    /**
     * constructor that initializes the hotel, page size, and visibility of all visual elements
     * @param hotel is the hotel that will be modified
     */
    public indivHotelManagerGUI(Hotel hotel) {
        super();

        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 450);
        this.setDefaultCloseOperation(3);

        this.hotelOfInterest = hotel;
        this.setTitle(this.hotelOfInterest.getName());


        this.initialize();


    }

    /**
     * function that is responsible for initializing all visual elements of the page
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
        JPanel east = new JPanel(new FlowLayout());
        east.setBackground(Color.cyan);
        this.add(east, "East");
        JPanel west = new JPanel(new FlowLayout());
        west.setBackground(Color.cyan);
        this.add(west, "West");
        this.Exit = new JButton("Back to Main Menu");
        south.add(this.Exit);
        JLabel Title = new JLabel("Welcome to the Hotel Manager of Hotel: "+ this.hotelOfInterest.getName());
        north.add(Title);
        JLabel Prompt = new JLabel("Please select the desired action", 0);
        this.rename = new JButton("1 - Rename Hotel");
        this.roomAdd = new JButton("2 - Add Room");
        this.roomRemove = new JButton("3 - Remove Room");
        this.updatePrice = new JButton("4 - Update Pricing");
        this.removeRes = new JButton("5 - Remove Reservation");
        this.modifyPrice = new JButton("6 - Modify Price per Date");
        this.removeHotel = new JButton("7 - Remove Hotel");
        JPanel holder = new JPanel(new GridLayout(8, 1));
        holder.add(Prompt);
        holder.add(this.rename);
        holder.add(this.roomAdd);
        holder.add(this.roomRemove);
        holder.add(this.updatePrice);
        holder.add(this.removeRes);
        holder.add(this.modifyPrice);
        holder.add(this.removeHotel);
        center.add(holder);
    }

    /**
     * function that is responsible for attaching action listeners to all buttons
     * @param listener is the object that will perform actions based on the action listener
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.rename.addActionListener(listener);
        this.roomAdd.addActionListener(listener);
        this.roomRemove.addActionListener(listener);
        this.updatePrice.addActionListener(listener);
        this.removeRes.addActionListener(listener);
        this.removeHotel.addActionListener(listener);
        this.modifyPrice.addActionListener(listener);
    }
}
