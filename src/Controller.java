//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, DocumentListener, ListSelectionListener, ItemListener {
    private mainMenuGUI gui;
    private addHotelGUI addGUI;
    private viewHotelGUI viewGUI;
    private manageHotelGUI manageGUI;
    private indivHotelInfoGUI hotelInfoGUI;
    private indivHotelManagerGUI hotelManageGUI;
    private renameHotelGUI renameGUI;
    private updatePricingGUI updatePriceGUI;
    private addRoomGUI addRoomGUI;
    private removeReservationGUI removeReservesGUI;
    private roomGUI indivRoomGUI;
    private removeRoomGUI removeRoomGUI;
    private JFrame currentScreen;

    private Hotel hotelSearch;
    private Room roomSearch;
    private int hotelIndex;

    private HotelManager manager;

    public Controller(mainMenuGUI gui, HotelManager manager) {
        this.manager = manager;
        this.gui = gui;
        gui.setActionListener(this);
        this.hotelIndex = 0;
    }

    public void initializeGUI() {
        this.gui = new mainMenuGUI();
        this.gui.setActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("1 - ADD HOTEL")) {
            this.addGUI = new addHotelGUI();
            this.addGUI.setActionListener(this);
            this.addGUI.setDocumentListener(this);

            this.currentScreen = this.addGUI;
            this.gui.dispose();

        } else if (e.getActionCommand().equals("2 - VIEW HOTEL")) {
            if(manager.getNoOfHotels() == 0)
            {
                JOptionPane.showMessageDialog(null, "Please create a hotel first!");
            }

            else
            {
                this.viewGUI = new viewHotelGUI(this.manager.getHotelList());
                this.viewGUI.setActionListener(this);
                this.viewGUI.setSelectionListener(this);
                this.currentScreen = this.viewGUI;
                this.gui.dispose();
            }
        }

        else if (e.getActionCommand().equals("3 - MANAGE HOTEL")) {

            if(manager.getNoOfHotels() == 0)
            {
                JOptionPane.showMessageDialog(null, "Please create a hotel first!");
            }

            else
            {
                this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                this.manageGUI.initialize();

                this.manageGUI.setActionListener(this);
                this.manageGUI.setSelectionListener(this);
                this.currentScreen = this.manageGUI;
                this.gui.dispose();
            }
        }

        else if (e.getActionCommand().equals("5 - EXIT")) {
            this.gui.dispose();
        }

        else if (e.getActionCommand().equals("1 - Rename Hotel")) {
            this.renameGUI = new renameHotelGUI(hotelSearch);
            this.renameGUI.setActionListener(this);
            this.currentScreen.dispose();

            this.currentScreen = this.renameGUI;
        }

        else if (e.getActionCommand().equals("2 - Add Room")) {
            this.addRoomGUI = new addRoomGUI(hotelSearch);
            this.addRoomGUI.setActionListener(this);
            this.currentScreen.dispose();
            this.currentScreen = this.addRoomGUI;
        }

        else if (e.getActionCommand().equals("3 - Remove Room")) {
            this.removeRoomGUI = new removeRoomGUI(hotelSearch);
            this.removeRoomGUI.setActionListener(this);
            this.currentScreen.dispose();
            this.currentScreen = this.removeRoomGUI;
        }

        else if (e.getActionCommand().equals("4 - Update Pricing")) {
            this.updatePriceGUI = new updatePricingGUI(hotelSearch);
            this.updatePriceGUI.setActionListener(this);
            this.currentScreen.dispose();
            this.currentScreen = this.updatePriceGUI;
        }

        else if (e.getActionCommand().equals("5 - Remove Reservation")) {
            this.removeReservesGUI = new removeReservationGUI(hotelSearch);
            this.removeReservesGUI.setActionListener(this);
            this.currentScreen.dispose();
            this.currentScreen = this.removeReservesGUI;
        }

        else if (e.getActionCommand().equals("6 - Remove Hotel")) {

            for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                    hotelIndex = i;
            }

            this.manager.getHotelList().remove(hotelIndex);
            this.currentScreen.dispose();
            this.initializeGUI();
            this.currentScreen.dispose();
        }

        else if(e.getActionCommand().equals("CONFIRM")) {
            if (this.currentScreen == this.renameGUI) {
                boolean isValid = true; //validator for the hotel's name (checks if unique)

                for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                    if (this.renameGUI.getnewName().getText().equals(this.manager.getHotelList().get(i).getName())) {
                        isValid = false;
                    }
                }

                if(isValid)
                {
                    for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                        if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName())) {
                            hotelIndex = i;
                        }
                    }

                    this.manager.getHotelList().get(hotelIndex).setName(this.renameGUI.getnewName().getText());
                    JOptionPane.showMessageDialog(null, "Name Successfully Changed!");

                    this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                    this.manageGUI.initialize();

                    this.manageGUI.setActionListener(this);
                    this.manageGUI.setSelectionListener(this);
                    this.currentScreen = this.manageGUI;
                    this.renameGUI.dispose();

                }

                else
                {
                    JOptionPane.showMessageDialog(null, "This name is not valid!");
                }

            }

            else if(this.currentScreen == this.addGUI)
            {
                boolean isValid = true; //validator for the hotel's name (checks if unique)

                for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                    if (this.addGUI.newName.getText().equals(this.manager.getHotelList().get(i).getName())) {
                        isValid = false;
                    }
                }

                if(Integer.parseInt(this.addGUI.roomAmnt.getText()) < 1 || Integer.parseInt(this.addGUI.roomAmnt.getText()) > 50){
                    isValid = false;
                }

                if(isValid)
                {
                    this.manager.createHotel(this.addGUI.newName.getText() ,Integer.parseInt(this.addGUI.roomAmnt.getText()));
                }

                else
                    System.out.println("Not Valid!");
            }

            else if(this.currentScreen == this.addRoomGUI)
            {
                try{
                    if(this.addRoomGUI.getAmnt().getText().equals("") || Integer.parseInt(this.addRoomGUI.getAmnt().getText()) > 50 || Integer.parseInt(this.addRoomGUI.getAmnt().getText()) < 0 )
                    {
                        JOptionPane.showMessageDialog(null, "Please Input a valid Amount!");
                    }

                    else if(this.addRoomGUI.getroomOption() == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Please input a valid option!");
                    }

                    else if(this.addRoomGUI.getHotel().getNoOfRooms() + Integer.parseInt(this.addRoomGUI.getAmnt().getText()) > 50 )
                    {
                        JOptionPane.showMessageDialog(null, "Adding this many rooms would put the room count over 50!");
                    }

                    else {
                        for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                            if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                                hotelIndex = i;
                        }

                        this.manager.getHotelList().get(hotelIndex).addRoom(this.addRoomGUI.getroomOption(), Integer.parseInt(this.addRoomGUI.getAmnt().getText()));
                        JOptionPane.showMessageDialog(null,"Added " + Integer.parseInt(this.addRoomGUI.getAmnt().getText()) + " rooms!");

                        this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                        this.manageGUI.initialize();
                        this.manageGUI.setActionListener(this);
                        this.manageGUI.setSelectionListener(this);
                        this.currentScreen = this.manageGUI;
                        this.addRoomGUI.dispose();

                        for(int i = 0; i < this.manager.getHotelList().get(hotelIndex).getRoomList().size();i++)
                        {
                            System.out.print(this.manager.getHotelList().get(hotelIndex).getRoomList().get(i).getName());
                            if(this.manager.getHotelList().get(hotelIndex).getRoomList().get(i) instanceof Deluxe)
                                System.out.print(" Deluxe");

                            else if(this.manager.getHotelList().get(hotelIndex).getRoomList().get(i) instanceof Executive)
                                System.out.print(" Executive");

                            else
                                System.out.print(" Regular");
                        }
                    }


                }catch(NumberFormatException f)
                {
                    JOptionPane.showMessageDialog(null, "Please make sure your input is a number!");
                }

            }

            else if(this.currentScreen == this.removeRoomGUI)
            {

                for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                    if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                        hotelIndex = i;
                }

                    this.manager.getHotelList().get(hotelIndex).getRoomList().remove(this.removeRoomGUI.getroomOption()-1);
                    JOptionPane.showMessageDialog(null, "Room Successfully Deleted!");

                this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                this.manageGUI.initialize();
                this.manageGUI.setActionListener(this);
                this.manageGUI.setSelectionListener(this);
                this.currentScreen = this.manageGUI;
                this.removeRoomGUI.dispose();
            }

            else if(this.currentScreen == this.updatePriceGUI)
            {
                try{
                    for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                        if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                            hotelIndex = i;
                    }

                    this.manager.getHotelList().get(hotelIndex).setPrice(Float.parseFloat(this.updatePriceGUI.getNewPrice().getText()));
                    JOptionPane.showMessageDialog(null, "Price Set to " + this.updatePriceGUI.getNewPrice().getText());

                    this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                    this.manageGUI.initialize();
                    this.manageGUI.setActionListener(this);
                    this.manageGUI.setSelectionListener(this);
                    this.currentScreen = this.manageGUI;
                    this.updatePriceGUI.dispose();


                }catch(NumberFormatException f)
                {
                    JOptionPane.showMessageDialog(null, "Please make sure your input is a number!");
                }

            }

            else if (this.currentScreen == this.removeReservesGUI){

                for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                    if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                        hotelIndex = i;
                }

                if(this.removeReservesGUI.getReservation() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Please Input a Valid Option!");
                }

                else {
                    this.manager.getHotelList().get(hotelIndex).getReservationList().remove(this.removeReservesGUI.getReservation()-1);
                    JOptionPane.showMessageDialog(null, "Successfully Deleted Reservation!");

                    this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                    this.manageGUI.initialize();
                    this.manageGUI.setActionListener(this);
                    this.manageGUI.setSelectionListener(this);
                    this.currentScreen = this.manageGUI;
                    this.removeReservesGUI.dispose();
                }


            }


        }

        else if (e.getActionCommand().equals("Back to Main Menu")) {
            this.currentScreen.dispose();
            this.initializeGUI();
        }

        else if (e.getActionCommand().equals("Back to View Hotel"))
        {
            this.currentScreen.dispose();
            this.viewGUI = new viewHotelGUI(this.manager.getHotelList());
            this.viewGUI.setActionListener(this);
            this.viewGUI.setSelectionListener(this);
            this.currentScreen = this.viewGUI;
        }

        else if(e.getActionCommand().equals("Back to Main Managing Menu"))
        {
            this.currentScreen.dispose();
            this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
            this.manageGUI.setActionListener(this);
            this.manageGUI.setSelectionListener(this);
            this.currentScreen = this.manageGUI;
        }

    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (this.currentScreen == this.viewGUI) {

                for(int i = 0; i < this.manager.getNoOfHotels(); i++)
                {
                    if(this.manager.getHotelList().get(i).getName().equals(this.viewGUI.getSelectedValue()))
                    {
                        hotelSearch = this.manager.getHotelList().get(i);
                    }
                }


                this.currentScreen.dispose();
                this.hotelInfoGUI = new indivHotelInfoGUI(hotelSearch);
                this.hotelInfoGUI.setActionListener(this);

                this.hotelInfoGUI.setdateListItemListener(new dateListListener(this.hotelInfoGUI));
                this.hotelInfoGUI.setroomListItemListener(this);

                this.currentScreen = this.hotelInfoGUI;

            } else {

                for(int i = 0; i < this.manager.getNoOfHotels(); i++)
                {
                    if(this.manager.getHotelList().get(i).getName().equals(this.manageGUI.getSelectedValue()))
                    {
                        hotelSearch = this.manager.getHotelList().get(i);
                    }
                }


                this.currentScreen.dispose();
                this.hotelManageGUI = new indivHotelManagerGUI(hotelSearch);
                this.hotelManageGUI.setActionListener(this);
                this.currentScreen = this.hotelManageGUI;
            }
        }

    }

    public void insertUpdate(DocumentEvent e) {
    }

    public void removeUpdate(DocumentEvent e) {
    }

    public void changedUpdate(DocumentEvent e) {

    }


    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED && this.currentScreen == this.hotelInfoGUI)
        {

            for(int i = 0; i < this.hotelSearch.getNoOfRooms();i++)
            {
                if(this.hotelInfoGUI.getSelectedRoom().equals(this.hotelSearch.getRoomList().get(i).getName()))
                {
                    this.roomSearch = this.hotelSearch.getRoomList().get(i);
                    System.out.println(i);
                }
            }
            this.currentScreen.dispose();
            indivRoomGUI = new roomGUI(roomSearch, hotelSearch);
            indivRoomGUI.initialize();
            indivRoomGUI.setActionListener(this);
            this.currentScreen = this.indivRoomGUI;

        }
    }

}
