import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * class responsible for the visual interface of the individual reservation creation functionalities
 */
public class createReservationGUI extends JFrame {

    /**
     * the exit button
     */
    private JButton Exit;
    /**
     * confirm button
     */
    private JButton confirmReservation;
    /**
     * dropdown menu where user chooses check in date
     */
    private JComboBox<String> checkInDate;
    /**
     * dropdown menu where user chooses check out date
     */
    private JComboBox<String> checkOutDate;
    /**
     * dropdown meny where user chooses their room
     */
    private JComboBox<String> roomList;
    /**
     * hotel where the reservation is added to
     */
    private Hotel hotel;
    /**
     * text field where the user can type their name
     */
    private JTextField guestName;
    /**
     * text field where the user can type in a discount code
     */
    private JTextField discountCode;

    /**
     * initializes page size and visibility of all visual elements
     * @param hotel is the hotel where the reservation is added  to
     */
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
     * function that creates all the design elements of the page
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

    /**
     * function attaches an action listener to all buttons
     * @param listener is the object that will perform actions based on the listeners that are attached to the buttons
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.confirmReservation.addActionListener(listener);
    }

    /**
     * function that gets the guestName textfield
     * @return the text field that has the inputted guestName
     */
    public JTextField getGuestName() {
        return this.guestName;
    }

    /**
     * returns the index of the room option dropdown meny
     * @return an int value that represents the index of the selected option in the room list dropdown meny
     */
    public int getRoom() {
        return this.roomList.getSelectedIndex();
    }

    /**
     *
     * @return the text field that contains the discount code
     */
    public JTextField getDiscountCode() {
        return this.discountCode;
    }

    /**
     *
     * @return the index of the selected item in checkInDate
     */
    public int getCheckInDate() {
        return this.checkInDate.getSelectedIndex();
    }

    /**
     *
     * @return the index of the selected item in checkInDate
     */
    public int getCheckOutDate() {
        return this.checkOutDate.getSelectedIndex();
    }
}
