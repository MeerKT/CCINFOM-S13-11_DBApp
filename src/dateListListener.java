import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class dateListListener implements ItemListener {
    indivHotelInfoGUI hotelInfoGUI;
    public dateListListener(indivHotelInfoGUI hotelInfoGUI) {
        this.hotelInfoGUI = hotelInfoGUI;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED && this.hotelInfoGUI.getSelectedDate() != 0)
        {
            UIManager.put("OptionPane.minimumSize", new Dimension(200,50));
            JOptionPane.showMessageDialog(null, "There are " + this.hotelInfoGUI.getHotel().getReservedRoomCount(this.hotelInfoGUI.getSelectedDate()) +" rooms reserved and "+ Integer.toString(this.hotelInfoGUI.getHotel().getNoOfRooms() -this.hotelInfoGUI.getHotel().getReservedRoomCount(this.hotelInfoGUI.getSelectedDate()) )+" that are available on day " + this.hotelInfoGUI.getSelectedDate()+"!" );
        }

    }
}
