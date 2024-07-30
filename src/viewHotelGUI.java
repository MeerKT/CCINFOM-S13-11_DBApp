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
 * class responsible for implementing a visual interface for the first view hotel page
 */
public class viewHotelGUI extends JFrame {
    /**
     * list of all hotels
     */
    ArrayList<Hotel> list;
    /**
     * array list of all hotel names
     */
    private ArrayList<String> hotelNames;
    /**
     * list of all hotel names
     */
    private JList nameList;
    /**
     * exit button
     */
    private JButton Exit;
    /**
     * scroll bar
     */
    private JScrollPane listScroll;

    /**
     * constructor that initializes the hotel arrayList, page size and visibility of all visual elements
     * @param list is the list of hotels that will be displayed
     */
    public viewHotelGUI(ArrayList<Hotel> list) {
        super("viewHotel");
        this.setLayout(new BorderLayout());
        this.list = list;
        this.hotelNames = new ArrayList<String>();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 450);
        this.setDefaultCloseOperation(3);
        this.initialize();
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
        JLabel Title = new JLabel("Welcome to the Hotel Viewer!");
        north.add(Title);
        JLabel Name = new JLabel("Please select which hotel you would like to see");

        for(int i = 0; i < this.list.size();i++)
        {
            hotelNames.add(this.list.get(i).getName());
        }

        this.nameList = new JList<>( hotelNames.toArray());
        listScroll = new JScrollPane(nameList);

        JPanel container = new JPanel(new GridLayout(2, 1));
        container.add(Name);
        container.add(this.listScroll);
        center.add(container);
    }

    /**
     * adds an action listeners to buttons in this page
     * @param listener is the object that will perform actions based on the button that is pressed
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
    }

    /**
     * returns the selected hotel name
     * @return a string that represents the item that was selected from the list
     */
    public String getSelectedValue() { return (String) this.nameList.getSelectedValue(); }

    /**
     * adds an list selection listeners to list in this page
     * @param listener is the object that will perform actions based on the list item that is pressed
     */
    public void setSelectionListener(ListSelectionListener listener) {
        this.nameList.addListSelectionListener(listener);
    }
}
