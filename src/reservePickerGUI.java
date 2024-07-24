import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class reservePickerGUI extends manageHotelGUI{


    public reservePickerGUI(ArrayList<Hotel> list) {
        super(list);
    }

    @Override
    public void initialize(){
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
        JLabel Title = new JLabel("Welcome to the Simulated Reservation System!");
        north.add(Title);
        JLabel Name = new JLabel("Please select which hotel you would like to make a reservation to");

        //INITIALIZES JLIST OF EXISTING HOTEL NAMES
        this.hotelList = new DefaultListModel<String>();
        for(int i = 0; i < this.list.size();i++)
        {
            this.hotelList.addElement(this.list.get(i).getName());
        }
        this.nameList = new JList<String>(hotelList);

        //INITIALIZES SCROLL BAR FOR NAMELIST
        this.listScroll = new JScrollPane(nameList);

        //ADDS COMPONENT TO CONTAINER
        JPanel container = new JPanel(new GridLayout(2, 1));
        container.add(Name);
        container.add(this.listScroll);
        center.add(container);
    }
}
