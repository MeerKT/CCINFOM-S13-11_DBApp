import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class reservationGUI extends JFrame{
    private JButton Exit;
    private Reservation reservation;


    public reservationGUI(Reservation reservation) {
        super();

        this.reservation = reservation;
        this.setTitle(this.reservation.getName());


        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(3);
        this.initialize();
    }

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
        JLabel Title = new JLabel("Welcome to the Reservation Viewer!");
        north.add(Title);

        //INITIALIZES CONTAINER THAT HOLDS ALL ROOM INFORMATION AND ADDS IT TO CENTER PANEL
        JPanel container = new JPanel(new GridLayout(4, 1));
        JLabel reservationName = new JLabel("Name of Guest: "+ this.reservation.getName());

        JLabel checkIn = new JLabel("Check-In Day: " + this.reservation.getcheckIn());
        JLabel checkOut = new JLabel("Check-Out Day: " + this.reservation.getcheckOut());

        JLabel PriceBreakdown = new JLabel("Total Price Breakdown: "+ (this.reservation.getcheckOut() - this.reservation.getcheckIn()) +" nights * PHP " + this.reservation.getRoom().getPrice() + " = PHP "+this.reservation.getTotalPrice());


        container.add(reservationName);
        container.add(checkIn);
        container.add(checkOut);
        container.add(PriceBreakdown);
        center.add(container);
    }

    /**
     * function attaches action listener to the buttons in the JFame
     * @param listener is the listener that will be told  when a certain button has been pressed
     */

    public void setActionListener(ActionListener listener) { this.Exit.addActionListener(listener); }


}

