import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * class responsible to listening to the date list dropdown meny in the individual view hotel meny
 */
public class dateListListener implements ItemListener {
    /**
     * the page where the dropdown meny that this class is listening to is
     */
    indivHotelInfoGUI hotelInfoGUI;

    /**
     * initializes the page that was passed to the class
     * @param hotelInfoGUI is the pagae where the dropdown meny that this class is listening to is located
     */
    public dateListListener(indivHotelInfoGUI hotelInfoGUI) {
        this.hotelInfoGUI = hotelInfoGUI;
    }

    @Override
    /**
     * function that performs actions based on the option that was selected
     * @param e represents the event that the listener gives to this class
     */
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED && this.hotelInfoGUI.getSelectedDate() != 0)
        {
            UIManager.put("OptionPane.minimumSize", new Dimension(200,50));
            JOptionPane.showMessageDialog(null, "There are " + this.hotelInfoGUI.getHotel().getReservedRoomCount(this.hotelInfoGUI.getSelectedDate()) +" rooms reserved and "+ Integer.toString(this.hotelInfoGUI.getHotel().getNoOfRooms() -this.hotelInfoGUI.getHotel().getReservedRoomCount(this.hotelInfoGUI.getSelectedDate()) )+" that are available on day " + this.hotelInfoGUI.getSelectedDate()+"!" );
        }

    }
}
