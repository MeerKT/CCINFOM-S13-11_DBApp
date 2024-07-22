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
import javax.swing.JTextField;

public class addRoomGUI extends JFrame {
    private JButton Exit;
    private JButton confirm;
    private JTextField Amnt;
    private JComboBox<String> roomOption;
    private Hotel hotel;


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

    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.confirm.addActionListener(listener);
    }

    public JTextField getAmnt() {  return this.Amnt; }

    public int getroomOption() { return this.roomOption.getSelectedIndex(); }

    public Hotel getHotel() {return this.hotel;}
}
