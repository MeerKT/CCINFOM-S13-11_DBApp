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

public class viewHotelGUI extends JFrame {
    ArrayList<Hotel> list;
    private ArrayList<String> hotelNames;
    private JList nameList;
    private JButton Exit;
    private JScrollPane listScroll;


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

    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
    }


    public String getSelectedValue() { return (String) this.nameList.getSelectedValue(); }

    public void setSelectionListener(ListSelectionListener listener) {
        this.nameList.addListSelectionListener(listener);
    }
}
