import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class createReservationGUI extends JFrame {

    private JButton Exit;
    private JButton confirmReservation;
    private JComboBox<String> checkInDate;
    private JComboBox<String> checkOutDate;
    private JComboBox<String> roomList;
    private Hotel hotel;
    private JTextField guestName;
    private JTextField discountCode;

    public createReservationGUI(Hotel hotel) {
        super();
        this.hotel = hotel;
        this.setTitle(this.hotel.getName());


        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(3);
        this.initialize();    }


    /**
     * function initializes the JFrame and implements all the design elements
     */
    public void initialize() {
        //INITIALIZES SOUTH PANEL
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.setBackground(Color.CYAN);
        this.add(south, "South");

        //INITIALIZES CENTER PANEL
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(8, 1));
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
        JLabel Title = new JLabel("Welcome to the Reservation Creator for Hotel "+ this.hotel.getName());
        north.add(Title);

        //INITIALIZES CONTAINER THAT HOLDS GUEST NAME INFORMATION AND PLACES IT IN THE CENTER PANEL
        JPanel container1 = new JPanel(new FlowLayout());
        JLabel guestPrompt = new JLabel("Please Input Guest Name: ");

        guestName = new JTextField();
        guestName.setColumns(20);

        container1.add(guestPrompt);
        container1.add(guestName);
        center.add(container1);

        //INITIALIZES CONTAINER THAT HOLDS CHECK IN INFORMATION AND PLACES IT IN THE CENTER PANEL
        JPanel container2 = new JPanel(new FlowLayout());
        JLabel datePrompt = new JLabel("Please input your check in date ");
        String[] datearray = new String[31];
        this.checkInDate = new JComboBox<String>();
        checkInDate.addItem("PLEASE SELECT A DATE");
        for(int i = 1; i < 32; i++) {
            checkInDate.addItem(Integer.toString(i));
        }
        container2.add(datePrompt);
        container2.add(checkInDate);
        center.add(container2);

        //INITIALIZES CONTAINER THAT HOLDS CHECK OUT INFORMATION AND PLACES IT IN THE CENTER PANEL
        JPanel container3 = new JPanel(new FlowLayout());
        JLabel checkoutPrompt = new JLabel("Please select your checkout date");
        this.checkOutDate = new JComboBox<String>();
        checkOutDate.addItem("PLEASE SELECT A DATE");
        for(int i = 1; i < 32; i++) {
            checkOutDate.addItem(Integer.toString(i));
        }
        container3.add(checkoutPrompt);
        container3.add(checkOutDate);
        center.add(container3);

        //INITIALIZES CONTAINER THAT HOLDS ROOM INFORMATION AND PLACES IT IN THE CENTER PANEL
        JPanel container4 = new JPanel(new FlowLayout());
        JLabel roomPrompt = new JLabel("Please select the room you would like to use!");
        roomList = new JComboBox<String>();
        roomList.addItem("PLEASE SELECT A ROOM");
        String Type = new String();
        for(int i = 0; i < this.hotel.getNoOfRooms();i++)
        {
            if(this.hotel.getRoomList().get(i) instanceof Deluxe)
                Type = "Deluxe";
            else if(this.hotel.getRoomList().get(i) instanceof Executive)
                Type = "Executive";
            else
                Type = "Regular";

            this.roomList.addItem(this.hotel.getRoomList().get(i).getName() + "-" + Type);
        }

        container4.add(roomPrompt);
        container4.add(roomList);
        center.add(container4);

        //INITIALIZES CONTAINER THAT HOLDS ROOM INFORMATION AND PLACES IT IN THE CENTER PANEL
        JPanel container5 = new JPanel(new FlowLayout());
        JLabel discountPrompt = new JLabel("Enter in a secret code and you might get a discount!");
        discountCode = new JTextField();
        discountCode.setColumns(20);
        container5.add(discountPrompt);
        container5.add(discountCode);
        center.add(container5);

        //INITIALIZES CONTAINER THAT HOLDS CONFIRM BUTTON AND PLACES IT IN THE CENTER PANEL
        JPanel container6 = new JPanel(new FlowLayout());
        confirmReservation = new JButton("CONFIRM");
        container6.add(confirmReservation);

        center.add(container6);
    }

    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.confirmReservation.addActionListener(listener);
    }
}
