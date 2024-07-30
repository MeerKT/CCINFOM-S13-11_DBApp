import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * class responsible for implementing visual interface for room information
 */
public class roomGUI extends JFrame{
    /**
     * exit button
     */
    private JButton Exit;
    /**
     * room that will be displayed
     */
    private Room roomOfInterest;
    /**
     * hotel that the room is part of
     */
    private Hotel roomHotel;
    /**
     * the days in which the room is reserved
     */
    private JList reservedDays;
    /**
     * list of reservations to the room
     */
    private DefaultListModel<String> reservedList;
    /**
     * scroll bar
     */
    private JScrollPane listScroll;

    /**
     * constructor that initializes the room, hotel, visibility of all visual elements and page size
     * @param room is the room that will be displayed
     * @param hotel is the hotel that the room is part of
     */
    public roomGUI(Room room, Hotel hotel) {
        super();

        this.roomOfInterest = room;
        this.roomHotel = hotel;
        this.setTitle(this.roomOfInterest.getName());


        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(3);
        this.initialize();
    }

    /**
     * initializes all visual elements
     */
    public void initialize() {
        //INITIALIZES SOUTH PANEL
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.setBackground(Color.CYAN);
        this.add(south, "South");

        //INITIALIZES CENTER PANEL
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 1));
        this.add(center, "Center");

        //INITIALIZES NORTH PANEL
        JPanel north = new JPanel(new FlowLayout());
        north.setBackground(Color.cyan);
        this.add(north, "North");

        //INITIALIZES EAST PANEL
        JPanel east = new JPanel(new FlowLayout());
        east.setBackground(Color.cyan);
        this.add(east, "East");

        //INITIALIZES WEST PANEL
        JPanel west = new JPanel(new FlowLayout());
        west.setBackground(Color.cyan);
        this.add(west, "West");

        //INITIALIZES EXIT BUTTON IN SOUTH PANEL
        this.Exit = new JButton("Back to View Hotel");
        south.add(this.Exit);

        //INITIALIZES TITLE LABEL AND ADDS IT TO NORTH PANEL
        JLabel Title = new JLabel("Welcome to the Room Viewer!");
        north.add(Title);

        //INITIALIZES CONTAINER THAT HOLDS ALL ROOM INFORMATION AND ADDS IT TO CENTER PANEL
        JPanel container = new JPanel(new GridLayout(4, 1));
        JLabel roomName = new JLabel("Name of Room: "+ this.roomOfInterest.getName());

        //Checks what type of room roomOfInterest is
        String type = "";
        if(this.roomOfInterest instanceof Deluxe)
            type = "Deluxe";

        else if(this.roomOfInterest instanceof Executive)
            type = "Executive";

        else
            type = "Regular";

        boolean isPresent = false;
        JLabel roomType = new JLabel("Type of Room: " + type);
        JLabel priceOfRoom = new JLabel("Price of Room: " + this.roomOfInterest.getPrice());

        reservedList = new DefaultListModel<String>();

        for(int i = 0;i < this.roomHotel.getReservationList().size();i++)
        {
            if(this.roomOfInterest.getName().equals(this.roomHotel.getReservationList().get(i).getRoom().getName()))
            {
                isPresent = true;
                reservedList.addElement(this.roomHotel.getReservationList().get(i).getcheckIn()+" to "+this.roomHotel.getReservationList().get(i).getcheckOut());
            }
        }

        if(!isPresent)
            reservedList.addElement("No Reservations Made");


        reservedDays = new JList(reservedList);
        listScroll = new JScrollPane(reservedDays);
        container.add(roomName);
        container.add(roomType);
        container.add(priceOfRoom);
        container.add(listScroll);
        center.add(container);
    }

    /**
     * function attaches action listener to the buttons in the JFame
     * @param listener is the listener that will be told  when a certain button has been pressed
     */

    public void setActionListener(ActionListener listener) { this.Exit.addActionListener(listener); }


    }

