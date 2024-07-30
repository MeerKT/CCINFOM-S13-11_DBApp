//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 * class responsible for implementing visual interface of the manage hotel meny
 */
public class manageHotelGUI extends JFrame {
    /**
     * list of all created hotels
     */
    protected ArrayList<Hotel> list;
    /**
     * exit button
     */
    protected JButton Exit;
    /**
     * Jlist of all created hotels
     */
    protected JList<String> nameList;
    /**
     * default List that contains all hotel names
     */
    protected DefaultListModel<String> hotelList;
    /**
     * scroll bar for visual list
     */
    protected JScrollPane listScroll;

    /**
     * constructor responsible for initializing page size, visibility of visual elements anc list of hotels
     * @param list is the list of hotels that will be displayed
     */
    public manageHotelGUI(ArrayList<Hotel> list) {
        super("Manage Hotel Menu");
        this.setLayout(new BorderLayout());
        this.list = list;
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 450);
        this.setDefaultCloseOperation(3);
        this.initialize();
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
        center.setLayout(new GridLayout(2, 1));
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
        JLabel Title = new JLabel("Welcome to the Hotel Manager!");
        north.add(Title);
        JLabel Name = new JLabel("Please select which hotel you would like to modify");

        //INITIALIZES JLIST OF EXISTING HOTEL NAMES
        hotelList = new DefaultListModel<String>();
        for(int i = 0; i < this.list.size();i++)
        {
            hotelList.addElement(this.list.get(i).getName());
        }
        nameList = new JList<String>(hotelList);

        //INITIALIZES SCROLL BAR FOR NAMELIST
        listScroll = new JScrollPane(nameList);

        //ADDS COMPONENT TO CONTAINER
        JPanel container = new JPanel(new GridLayout(2, 1));
        container.add(Name);
        container.add(this.listScroll);
        center.add(container);
    }

    /**
     * adds an action listener to all buttons in the page
     * @param listener is the object that will perform an action based on the button pressed
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
    }

    /**
     * adds a selection listener to the list in this page
     * @param listener is the object that will perform an action based on the list item that is pressed
     */
    public void setSelectionListener(ListSelectionListener listener) {
        this.nameList.addListSelectionListener(listener);
    }

    /**
     * returns the value of the selected list item
     * @return a string that represents the value of the selected hotel name
     */
    public String getSelectedValue() { return (String) this.nameList.getSelectedValue(); }
}
