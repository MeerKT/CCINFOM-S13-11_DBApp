//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * class that is responsible for the visual interface of the view individual hotel info functionalities
 */
public class indivHotelInfoGUI extends JFrame {
    /**
     * an exit button
     */
    private JButton Exit;
    /**
     * dropdown meny with all dates
     */
    private JComboBox<String> dateList;
    /**
     * dropdown meny with all available rooms
     */
    private JComboBox<String> roomList;
    /**
     * dropdown menu with all available reservations
     */
    private JComboBox<String> reservationList;
    /**
     * hotel whose information is being viewed
     */
    private Hotel hotel;

    /**
     * constructor that initializes page size, the hotel info that is needed and visibility of all visual elements
     * @param hotel is the hotel whose information is being viewed
     */
    public indivHotelInfoGUI(Hotel hotel) {
        super();
        this.hotel = hotel;
        this.setTitle(this.hotel.getName());


        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(500, 700);
        this.setDefaultCloseOperation(3);
        this.initialize();
    }

    /**
     * function initializes all design elements
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
        this.Exit = new JButton("Back to Main Menu");
        south.add(this.Exit);

        //INITIALIZES TITLE LABEL AND ADDS IT TO NORTH PANEL
        JLabel Title = new JLabel("Welcome to the Hotel Viewer!");
        north.add(Title);

        //INITIALIZES CONTAINER THAT HOLDS ALL HIGH LEVEL INFORMATION AND ADDS IT TO CENTER PANEL
        JPanel container = new JPanel(new GridLayout(4, 1));
        JLabel highLvl = new JLabel("High Level Information", 0);
        JLabel hotelName = new JLabel("Name of Hotel: "+ this.hotel.getName());
        JLabel hotelRoom = new JLabel("Number of Rooms: " + this.hotel.getNoOfRooms());
        JLabel estimateEarnings = new JLabel("Number of earnings: " + this.hotel.getTotalRevenue());
        container.add(highLvl);
        container.add(hotelName);
        container.add(hotelRoom);
        container.add(estimateEarnings);
        center.add(container);

        //INITIALIZES COMBOBOX THAT HOLDS THE DATE SELECTOR AND ADDS IT INTO A CONTAINER IN THE CENTER PANEL
        JPanel container2 = new JPanel(new GridLayout(7, 1));
        String[] datearray = new String[31];



        JLabel lowlvlInfo = new JLabel("Low Level Information", 0);
        JLabel prompt = new JLabel("Select a date to see its availability: ");
        this.dateList = new JComboBox<String>();

        dateList.addItem("PLEASE SELECT A DATE");

        for(int i = 1; i < 32; i++) {
            dateList.addItem(Integer.toString(i));
        }

        container2.add(lowlvlInfo);
        container2.add(prompt);
        container2.add(this.dateList);

        //INITIALIZES COMBOBOX WITH INDIVIDUAL ROOMS AND ADDS IT TO THE CENTER PANEL
        JLabel roomPrompt = new JLabel("Select a Room for more Information ");
        container2.add(roomPrompt);
        this.roomList = new JComboBox<String>();
        roomList.addItem("PLEASE SELECT A ROOM");
        for(int i = 0; i < getHotel().getNoOfRooms();i++)
            this.roomList.addItem(getHotel().getRoomList().get(i).getName());

        container2.add(roomList);

        //INITIALIZES COMBOBOX WITH INDIVIDUAL RESERVATIONS AND ADDS IT TO THE CENTER PANEL
        JLabel reservationPrompt = new JLabel("Select a Reservation for more information ");
        reservationList = new JComboBox<String>();
        if(this.hotel.getReservationCount() == 0)
            reservationList.addItem("NO RESERVATIONS MADE");
        else
        {
            reservationList.addItem("PLEASE SELECT A RESERVATION");
            for(int i = 0 ; i < this.hotel.getReservationCount(); i++)
            {
                reservationList.addItem(this.hotel.getReservationList().get(i).getName());
            }
        }
        container2.add(reservationPrompt);
        container2.add(reservationList);

        center.add(container2);
    }

    /**
     * function attaches action listener to the buttons in the JFame
     * @param listener is the listener that will be told  when a certain button has been pressed
     */

    public void setActionListener(ActionListener listener) { this.Exit.addActionListener(listener); }

    /**
     * function that attaches an item listener to the dateList ComboBox in the JFrame
     * @param listener is the listener that will be told when a certain item has been selected
     */
    public void setdateListItemListener(ItemListener listener ) {
        this.dateList.addItemListener(listener);

    }

    /**
     * function that attaches an item listener to the reservation list dropdown meny
     * @param listener is the listener that will be told when a certain item has been selected
     */
    public void setreservationListListener(ItemListener listener)
    {
        this.reservationList.addItemListener(listener);
    }

    /**
     * function that attaches an item listener to the roomList ComboBox in the JFrame
     * @param listener is the listener that will be told when a certain item has been selected
     */
    public void setroomListItemListener(ItemListener listener ) {
        this.roomList.addItemListener(listener);

    }

    /**
     * function gets the date that was chosen
     * @returns the index of the selected date in the combobox dateList
     */
    public int getSelectedDate() { return this.dateList.getSelectedIndex(); }

    /**
     * function that gets the displayed hotel
     * @return the hotel value that was originally passed to the class
     */
    public Hotel getHotel() { return this.hotel; }

    /**
     * function gets the roomList combo box
     * @return - the roomList combo box
     */
    public String getSelectedRoom() { return (String) this.roomList.getSelectedItem(); }

    /**
     * returns the option of the reservation list
     * @return the int value of the index of the selected reservation
     */
    public int getSelectedReservation(){ return this.reservationList.getSelectedIndex(); }

    /**
     * returns the reservation list
     * @return the specific dropdown menu that represents the reservation list
     */
    public Object getReservation(){ return this.reservationList; }
}

