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

public class renameHotelGUI extends JFrame {
    private JButton Exit;
    private JButton rename;
    private JTextField newName;

    private Hotel hotel;

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

    public JTextField getnewName() { return this.newName; }

    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.rename.addActionListener(listener);
    }
}
