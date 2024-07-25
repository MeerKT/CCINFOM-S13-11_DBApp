

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static javax.swing.SwingConstants.LEFT;

public class priceModifierGUI extends JFrame {
    private JButton Exit;
    private JButton confirm;
    private JTextField modifierAmnt;
    private JComboBox<String> dateOption;
    private Hotel hotel;


    public priceModifierGUI(Hotel hotel) {
        super("Modify Price per Date");
        this.setLayout(new BorderLayout());
        this.hotel = hotel;
        this.initialize();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(900, 350);
        this.setDefaultCloseOperation(3);
    }

    public void initialize() {
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.setBackground(Color.CYAN);
        this.add(south, "South");
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(7,1));
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
        JLabel Title = new JLabel("Change the Price Modifier of a certain date here!");
        north.add(Title);
        this.Exit = new JButton("Back to Main Menu");
        south.add(this.Exit);

        JPanel lblfld1 = new JPanel(new FlowLayout());
        JLabel modifierPrompt = new JLabel("Please enter a new modifier(50 - 150%");
        this.modifierAmnt = new JTextField();
        this.modifierAmnt.setColumns(5);

        lblfld1.add(modifierPrompt);
        lblfld1.add(modifierAmnt);

        JPanel lblfld2 = new JPanel(new FlowLayout());
        JLabel datePrompt = new JLabel("Select the date you would like to apply this new modifier to (number indicates its current modifier)");
        this.dateOption = new JComboBox();
        dateOption.addItem("PLEASE SELECT A DATE");
        for(int i = 1; i < 32; i++) {
            dateOption.addItem(Integer.toString(i) +" - "+ hotel.getDatePriceModifier(i) * 100);
        }

        lblfld2.add(datePrompt);
        lblfld2.add(this.dateOption);

        center.add(lblfld1);
        center.add(lblfld2);
        JPanel lblfld3 = new JPanel(new FlowLayout());
        this.confirm = new JButton("CONFIRM");
        lblfld3.add(this.confirm);
        center.add(lblfld3 );
    }

    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.confirm.addActionListener(listener);
    }

    public JTextField getModifierAmnt() {  return this.modifierAmnt; }

    public int getDate() { return this.dateOption.getSelectedIndex(); }

    public Hotel getHotel() {return this.hotel;}
}
