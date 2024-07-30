
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

/**
 * class responsible for implementing visual interface for remove room functionalities
 */
public class removeRoomGUI extends JFrame {
    private JButton Exit;
    private JButton confirm;
    private JComboBox<String> roomList;
    private Hotel hotel;


    /**
     * constructor that initializes visibility of visual elements, hotel and page size
     * @param hotel is the hotel that will remove the selected room
     */
    public removeRoomGUI(Hotel hotel) {
        super("Remove a Room");
        this.setLayout(new BorderLayout());
        this.hotel = hotel;
        this.initialize();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 200);
        this.setDefaultCloseOperation(3);
    }

    /**
     * initializes all visual eleemnts
     */
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
        JLabel Title = new JLabel("Remove Rooms From " + this.hotel.getName()+" Here");
        north.add(Title);
        this.Exit = new JButton("Back to Main Menu");
        south.add(this.Exit);

        JPanel lblfld1 = new JPanel(new FlowLayout());
        JLabel remove = new JLabel("Please select the room you would like to remove");

        this.roomList = new JComboBox<String>();

        roomList.addItem("PLEASE SELECT A ROOM");

        for(int i = 0; i < getHotel().getNoOfRooms();i++)
            this.roomList.addItem(getHotel().getRoomList().get(i).getName());

        lblfld1.add(remove);
        lblfld1.add(roomList);
        center.add(lblfld1, "Center");


        JPanel lblfld2 = new JPanel(new FlowLayout());
        this.confirm = new JButton("CONFIRM");
        lblfld2.add(this.confirm);
        center.add(lblfld2, "South");
    }

    /**
     * adds action listeners to all buttons in the page
     * @param listener is the object that will perform some action based on the button that is pressed
     */
    public void setActionListener(ActionListener listener) {
        this.Exit.addActionListener(listener);
        this.confirm.addActionListener(listener);
    }

    /**
     * returns the selected room index
     * @return an int value that represents the index of the selected option in the roomList dropdown menu
     */
    public int getroomOption() { return this.roomList.getSelectedIndex(); }

    /**
     * returns the hotel
     * @return a Hotel that represents hte hotel that was passed to this class
     */
    public Hotel getHotel() {return this.hotel;}
}
