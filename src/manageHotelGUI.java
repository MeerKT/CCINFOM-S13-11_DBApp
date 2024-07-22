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

public class manageHotelGUI extends JFrame {
    ArrayList<Hotel> list;
    JButton Exit;
    JList<String> nameList;
    private DefaultListModel<String> hotelList;
    private JScrollPane listScroll;

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

    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
    }

    public void setSelectionListener(ListSelectionListener listener) {
        this.nameList.addListSelectionListener(listener);
    }

    public String getSelectedValue() { return (String) this.nameList.getSelectedValue(); }
}
