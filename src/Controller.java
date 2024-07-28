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
    private reservePickerGUI reservationGUI;
    private JFrame currentScreen;
    private createReservationGUI indivReservation;
    private  priceModifierGUI newPriceModifier;
    private reservationGUI reservationInfo;

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

    /**
     * function creates a new main menu page
     */
    public void initializeGUI() {
        this.gui = new mainMenuGUI();
        this.gui.setActionListener(this);
    }

    /**
     * function determines what actions should be performed when pressing buttons
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        //checks the button and initializes an add hotel page
        if (e.getActionCommand().equals("1 - ADD HOTEL")) {
            this.addGUI = new addHotelGUI();
            this.addGUI.setActionListener(this);
            this.addGUI.setDocumentListener(this);

            this.currentScreen = this.addGUI;
            this.gui.dispose();

            //checks the button
        } else if (e.getActionCommand().equals("2 - VIEW HOTEL")) {

            //checks if there are any hotels to view, gives an error message if there are none
            if(manager.getNoOfHotels() == 0)
            {
                JOptionPane.showMessageDialog(null, "Please create a hotel first!");
            }

            //initializes a view hotel page if there are hotels present in hotel list
            else
            {
                this.viewGUI = new viewHotelGUI(this.manager.getHotelList());
                this.viewGUI.setActionListener(this);
                this.viewGUI.setSelectionListener(this);
                this.currentScreen = this.viewGUI;
                this.gui.dispose();
            }
        }

        //checks the button
        else if (e.getActionCommand().equals("3 - MANAGE HOTEL")) {

            //checks if there are any hotels to manage, gives an error statement if there are none
            if(manager.getNoOfHotels() == 0)
            {
                JOptionPane.showMessageDialog(null, "Please create a hotel first!");
            }

            //initializes a manage hotel page
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

        else if (e.getActionCommand().equals("4 - SIMULATE BOOKING")) {

            //checks if there are any hotels to make reservations to, gives an error statement if there are none
            if(manager.getNoOfHotels() == 0)
            {
                JOptionPane.showMessageDialog(null, "Please create a hotel first!");
            }

            //initializes a hotel selection page
            else
            {
                this.reservationGUI = new reservePickerGUI(this.manager.getHotelList());
                this.reservationGUI.initialize();

                this.reservationGUI.setActionListener(this);
                this.reservationGUI.setSelectionListener(this);

                this.currentScreen = this.reservationGUI;
                this.gui.dispose();
            }
        }

        //Terminates the program if the exit button is pressed
        else if (e.getActionCommand().equals("5 - EXIT")) {
            this.gui.dispose();
        }

        //opens a rename hotel page
        else if (e.getActionCommand().equals("1 - Rename Hotel")) {
            this.renameGUI = new renameHotelGUI(hotelSearch);
            this.renameGUI.setActionListener(this);
            this.currentScreen.dispose();

            this.currentScreen = this.renameGUI;
        }

        //opens an add room page
        else if (e.getActionCommand().equals("2 - Add Room")) {
            this.addRoomGUI = new addRoomGUI(hotelSearch);
            this.addRoomGUI.setActionListener(this);
            this.currentScreen.dispose();
            this.currentScreen = this.addRoomGUI;
        }

        //opens a remove room page
        else if (e.getActionCommand().equals("3 - Remove Room")) {
            this.removeRoomGUI = new removeRoomGUI(hotelSearch);
            this.removeRoomGUI.setActionListener(this);
            this.currentScreen.dispose();
            this.currentScreen = this.removeRoomGUI;
        }

        //opens an update pricing page
        else if (e.getActionCommand().equals("4 - Update Pricing")) {

            boolean isReserved = false;

            for(int i = 0; i < hotelSearch.getNoOfRooms();i++)
            {
                if(hotelSearch.getRoomList().get(i).getIsReserved())
                {
                    isReserved = true;
                    break;
                }
            }

            if(isReserved)
            {
                JOptionPane.showMessageDialog(null, "This Hotel has some reservations, please remove them all before changing the price!");
            }

            else
            {
                this.updatePriceGUI = new updatePricingGUI(hotelSearch);
                this.updatePriceGUI.setActionListener(this);
                this.currentScreen.dispose();
                this.currentScreen = this.updatePriceGUI;
            }
        }

        //opens a remove reservation page
        else if (e.getActionCommand().equals("5 - Remove Reservation")) {
            this.removeReservesGUI = new removeReservationGUI(hotelSearch);
            this.removeReservesGUI.setActionListener(this);
            this.currentScreen.dispose();
            this.currentScreen = this.removeReservesGUI;
        }

        else if(e.getActionCommand().equals("6 - Modify Price per Date"))
        {
            boolean isReserved = false;

            for(int i = 0; i < hotelSearch.getNoOfRooms();i++)
            {
                if(hotelSearch.getRoomList().get(i).getIsReserved())
                {
                    isReserved = true;
                    break;
                }
            }

            if(isReserved)
            {
                JOptionPane.showMessageDialog(null, "This Hotel has some reservations, please remove them all before changing the price!");
            }

            else
            {
                this.newPriceModifier = new priceModifierGUI(hotelSearch);
                this.newPriceModifier.setActionListener(this);
                this.currentScreen.dispose();
                this.currentScreen = this.newPriceModifier;
            }

        }

        //removes the hotel and goes back to the main menu
        else if (e.getActionCommand().equals("7 - Remove Hotel")) {

            //searches for the real hotel that corresponds to the temporary hotel
            for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                    hotelIndex = i;
            }

            // terminates the manage screen and goes back to the main menu
            this.manager.getHotelList().remove(hotelIndex);
            this.currentScreen.dispose();
            this.initializeGUI();
            this.currentScreen.dispose();
        }

        //checks if the button is a confirm button
        else if(e.getActionCommand().equals("CONFIRM")) {
            //checks if the button being pressed is in the rename GUI
            if (this.currentScreen == this.renameGUI) {
                boolean isValid = true; //validator for the hotel's name (checks if unique)

                //checks if the name is unique
                for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                    if (this.renameGUI.getnewName().getText().equals(this.manager.getHotelList().get(i).getName())) {
                        isValid = false;
                    }
                }

                //searches for the real hotel that corresponds to searchHotel
                if(isValid)
                {
                    for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                        if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName())) {
                            hotelIndex = i;
                        }
                    }

                    //changes the name of the real hotel and tells the user
                    this.manager.getHotelList().get(hotelIndex).setName(this.renameGUI.getnewName().getText());
                    JOptionPane.showMessageDialog(null, "Name Successfully Changed!");

                    //goes back to the manage hotel GUI
                    this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                    this.manageGUI.initialize();
                    this.manageGUI.setActionListener(this);
                    this.manageGUI.setSelectionListener(this);
                    this.currentScreen = this.manageGUI;
                    this.renameGUI.dispose();

                }

                //error message for if the given name isn't valid
                else
                {
                    JOptionPane.showMessageDialog(null, "This name is not valid!");
                }

            }

            //code that executes if the confirm button is pressed in the add hotel GUI
            else if(this.currentScreen == this.addGUI)
            {
               try{
                   boolean isValid = true; //validator for the hotel's name (checks if unique)

                   //checks the hotel list if the name is unique
                   for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                       if (this.addGUI.newName.getText().equals(this.manager.getHotelList().get(i).getName())) {
                           isValid = false;
                       }
                   }

                   // checks if the amount of rooms is below 50 and above 1
                   if(Integer.parseInt(this.addGUI.roomAmnt.getText()) < 1 || Integer.parseInt(this.addGUI.roomAmnt.getText()) > 50){
                       isValid = false;
                   }

                   //adds the hotel to the list
                   if(isValid)
                   {
                       this.manager.createHotel(this.addGUI.newName.getText() ,Integer.parseInt(this.addGUI.roomAmnt.getText()));
                       JOptionPane.showMessageDialog(null, "Hotel Successfully Created!");

                       this.initializeGUI();
                       this.addGUI.dispose();
                   }

                   //error message if any of the above conditions are not met
                   else
                       JOptionPane.showMessageDialog(null, "Inputs not valid! Please make sure the hotel name is unique and that it has only 50 or less rooms!");
               }
               catch(NumberFormatException f)
               {
                   JOptionPane.showMessageDialog(null, "Please make sure your room input is a number!");
               }
            }

            // code that executes if the confirm button is in the add room GUI
            else if(this.currentScreen == this.addRoomGUI)
            {
                //checks if the amount is valid
                try{
                    if(this.addRoomGUI.getAmnt().getText().equals("") || Integer.parseInt(this.addRoomGUI.getAmnt().getText()) > 50 || Integer.parseInt(this.addRoomGUI.getAmnt().getText()) < 0 )
                    {
                        JOptionPane.showMessageDialog(null, "Please Input a valid Amount!");
                    }

                    //checks if the user has inputted a room type option
                    else if(this.addRoomGUI.getroomOption() == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Please input a valid option!");
                    }

                    //checks if the given amount doesn't make the hotel's room count go over 50
                    else if(this.addRoomGUI.getHotel().getNoOfRooms() + Integer.parseInt(this.addRoomGUI.getAmnt().getText()) > 50 )
                    {
                        JOptionPane.showMessageDialog(null, "Adding this many rooms would put the room count over 50!");
                    }

                    // if the above conditions aren't true, the code finds the real hotel, and adds the sepcified amount of rooms
                    else {
                        for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                            if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                                hotelIndex = i;
                        }

                        this.manager.getHotelList().get(hotelIndex).addRoom(this.addRoomGUI.getroomOption(), Integer.parseInt(this.addRoomGUI.getAmnt().getText()));
                        JOptionPane.showMessageDialog(null,"Added " + Integer.parseInt(this.addRoomGUI.getAmnt().getText()) + " rooms!");

                        // goes back to the manage menu
                        this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                        this.manageGUI.initialize();
                        this.manageGUI.setActionListener(this);
                        this.manageGUI.setSelectionListener(this);
                        this.currentScreen = this.manageGUI;
                        this.addRoomGUI.dispose();
                    }


                    // catch statement that gives an error message if the user doesn't input an actual number
                }catch(NumberFormatException f)
                {
                    JOptionPane.showMessageDialog(null, "Please make sure your input is a number!");
                }

            }

            //the code below executes if the confirm button is in the remove room GUI
            else if(this.currentScreen == this.removeRoomGUI)
            {
                // looks for the real hotel that corresponds to hotelSearch
                for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                    if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                        hotelIndex = i;
                }
                //removes the selected room in the real hotel
                this.manager.getHotelList().get(hotelIndex).getRoomList().remove(this.removeRoomGUI.getroomOption()-1);
                JOptionPane.showMessageDialog(null, "Room Successfully Deleted!");

                // goes back to the hotel manager screen
                this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                this.manageGUI.initialize();
                this.manageGUI.setActionListener(this);
                this.manageGUI.setSelectionListener(this);
                this.currentScreen = this.manageGUI;
                this.removeRoomGUI.dispose();
            }

            //the code below executes if the confirm button is in the update pricing GUI
            else if(this.currentScreen == this.updatePriceGUI)
            {
                try{
                    //looks for the real hotel that corresponds to hotelSearch
                    for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                        if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                            hotelIndex = i;
                    }

                    if(Float.parseFloat(this.updatePriceGUI.getNewPrice().getText()) < 100.0f)
                        JOptionPane.showMessageDialog(null,"New price needs to be at least PHP 100!");

                    else
                    {
                        //updates the pricing of that hotel
                        this.manager.getHotelList().get(hotelIndex).setPrice(Float.parseFloat(this.updatePriceGUI.getNewPrice().getText()));
                        this.manager.getHotelList().get(hotelIndex).setBasePrice(Float.parseFloat(this.updatePriceGUI.getNewPrice().getText()));

                        JOptionPane.showMessageDialog(null, "Price Set to " + this.updatePriceGUI.getNewPrice().getText());
                    }


                    //goes back to the hotel management screen
                    this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                    this.manageGUI.initialize();
                    this.manageGUI.setActionListener(this);
                    this.manageGUI.setSelectionListener(this);
                    this.currentScreen = this.manageGUI;
                    this.updatePriceGUI.dispose();


                    //catch statement gives an error message if the user doesn't give an actual number
                }catch(NumberFormatException f)
                {
                    JOptionPane.showMessageDialog(null, "Please make sure your input is a number!");
                }

            }

            //code below executes if the confirm button is in the removeReservationGUI
            else if (this.currentScreen == this.removeReservesGUI){
                //searches for the real hotel that corresponds to hotelSearch
                for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                    if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                        hotelIndex = i;
                }

                //gives an error message if the user tries to remove the placeholder option
                if(this.removeReservesGUI.getReservation() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Please Input a Valid Option!");
                }

                //deletes the reservation in the actual hotel and goes back to the manage hotel menu
                else {

                    for(int i = this.manager.getHotelList().get(hotelIndex).getReservationList().get(this.removeReservesGUI.getReservation()-1).getcheckIn(); i < this.manager.getHotelList().get(hotelIndex).getReservationList().get(this.removeReservesGUI.getReservation()-1).getcheckOut(); i++){
                        this.manager.getHotelList().get(hotelIndex).getReservationList().get(this.removeReservesGUI.getReservation()-1).getRoom().changeDayAvailability(i-1);
                    }

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

            else if(this.currentScreen == this.indivReservation)
            {
                //error checking every text field
                if(this.indivReservation.getGuestName().getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please input a valid name!");
                }

                else if(this.indivReservation.getCheckInDate() == 0 || this.indivReservation.getCheckOutDate() == 0 || (this.indivReservation.getCheckOutDate()) < this.indivReservation.getCheckInDate())
                {
                    JOptionPane.showMessageDialog(null, "Please input valid dates!");
                }

                else if(this.indivReservation.getRoom() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Please input a valid room!");
                }

                else
                {

                    //looks for the real hotel that corresponds to hotelSearch
                    for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                        if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                            hotelIndex = i;
                    }

                    boolean isReserved = false;

                    for(int i = this.indivReservation.getCheckInDate(); i < this.indivReservation.getCheckOutDate();i++  )
                    {
                        if(this.manager.getHotelList().get(hotelIndex).getRoomList().get(this.indivReservation.getRoom()-1).getIsReservedDay(i))
                            isReserved = true;
                    }

                    if(isReserved)
                    {
                        JOptionPane.showMessageDialog(null, "Room is already reserved on this timeframe!");
                    }

                    else
                    {
                        this.manager.getHotelList().get(hotelIndex).simulateBooking( this.indivReservation.getRoom()-1,this.indivReservation.getCheckInDate(), this.indivReservation.getCheckOutDate(), this.indivReservation.getGuestName().getText(), this.indivReservation.getDiscountCode().getText());
                        int reservationIndex = 0;


                        for (int i = 0; i < this.manager.getHotelList().get(hotelIndex).getReservationList().size(); i++) {
                            if (this.manager.getHotelList().get(hotelIndex).getReservationList().get(i).getName().equals(this.indivReservation.getGuestName().getText())) {
                                reservationIndex = i;
                            }
                        }


                        if (this.manager.getHotelList().get(hotelIndex).getReservationList().get(reservationIndex).getDiscount()) {
                            JOptionPane.showMessageDialog(null, "Discount applied!");
                        }
                        JOptionPane.showMessageDialog(null, "Reservation created!");

                        this.reservationGUI = new reservePickerGUI(this.manager.getHotelList());
                        this.reservationGUI.setActionListener(this);
                        this.reservationGUI.setSelectionListener(this);
                        this.currentScreen = this.reservationGUI;
                        this.indivReservation.dispose();
                    }
                }
            }

            else if(this.currentScreen == this.newPriceModifier)
            {
                try{
                    if(this.newPriceModifier.getDate() == 0)
                        JOptionPane.showMessageDialog(null, "Please input an actual date!");

                    else if(Integer.parseInt(this.newPriceModifier.getModifierAmnt().getText()) < 50 || Integer.parseInt(this.newPriceModifier.getModifierAmnt().getText()) > 150  )
                        JOptionPane.showMessageDialog(null, "Please input a valid modifier!");

                    else
                    {
                        for (int i = 0; i < this.manager.getHotelList().size(); i++) {
                            if (this.hotelSearch.getName().equals(this.manager.getHotelList().get(i).getName()))
                                hotelIndex = i;
                        }

                        this.manager.getHotelList().get(hotelIndex).setDatePriceModifier(this.newPriceModifier.getDate(), Float.parseFloat(this.newPriceModifier.getModifierAmnt().getText()));
                        JOptionPane.showMessageDialog(null,"Modifier has been applied!");

                        this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
                        this.manageGUI.initialize();
                        this.manageGUI.setActionListener(this);
                        this.manageGUI.setSelectionListener(this);
                        this.currentScreen = this.manageGUI;
                        this.newPriceModifier.dispose();

                    }

                }
                catch(NumberFormatException f)
                {
                    JOptionPane.showMessageDialog(null,"Please input an actual number!");
                }
            }
        }

        //Exits the current screen and initializes a main menu screen
        else if (e.getActionCommand().equals("Back to Main Menu")) {
            this.currentScreen.dispose();
            this.initializeGUI();
        }

        //Exits current screen and initializes a view hotel menu
        else if (e.getActionCommand().equals("Back to View Hotel"))
        {
            this.currentScreen.dispose();
            this.viewGUI = new viewHotelGUI(this.manager.getHotelList());
            this.viewGUI.setActionListener(this);
            this.viewGUI.setSelectionListener(this);
            this.currentScreen = this.viewGUI;
        }

        //Exits current screen and initializes a manage hotel menu
        else if(e.getActionCommand().equals("Back to Main Managing Menu"))
        {
            this.currentScreen.dispose();
            this.manageGUI = new manageHotelGUI(this.manager.getHotelList());
            this.manageGUI.setActionListener(this);
            this.manageGUI.setSelectionListener(this);
            this.currentScreen = this.manageGUI;
        }

    }

    /**
     * function that determines actions done for list selection events
     * @param e the event that characterizes the change.
     */
    public void valueChanged(ListSelectionEvent e) {
        //checks if the user finished clicking on the list ite
        if (!e.getValueIsAdjusting()) {
            //the code below executes if the list is in the view hotel page
            if (this.currentScreen == this.viewGUI) {
                //finds the corresponding item in the hotel list and stores it in a temporary variable
                for(int i = 0; i < this.manager.getNoOfHotels(); i++)
                {
                    if(this.manager.getHotelList().get(i).getName().equals(this.viewGUI.getSelectedValue()))
                    {
                        hotelSearch = this.manager.getHotelList().get(i);
                    }
                }

                //disposes the current screen and initializes an individual hotel info page
                this.currentScreen.dispose();
                this.hotelInfoGUI = new indivHotelInfoGUI(hotelSearch);
                this.hotelInfoGUI.setActionListener(this);

                this.hotelInfoGUI.setdateListItemListener(new dateListListener(this.hotelInfoGUI));
                this.hotelInfoGUI.setroomListItemListener(this);
                this.hotelInfoGUI.setreservationListListener(this);

                this.currentScreen = this.hotelInfoGUI;

                //code below executes if the list is in the manage hotel page
            } else if(this.currentScreen == this.manageGUI) {
                //finds the corresponding item in the hotel list and stores it in a temporary variable
                for(int i = 0; i < this.manager.getNoOfHotels(); i++)
                {
                    if(this.manager.getHotelList().get(i).getName().equals(this.manageGUI.getSelectedValue()))
                    {
                        hotelSearch = this.manager.getHotelList().get(i);
                    }
                }

                //disposes the current screen and initializes an individual manage hotel page
                this.currentScreen.dispose();
                this.hotelManageGUI = new indivHotelManagerGUI(hotelSearch);
                this.hotelManageGUI.setActionListener(this);
                this.currentScreen = this.hotelManageGUI;
            }

            //code below executes if the list is in the pick reservation screen
            else if(this.currentScreen == this.reservationGUI)
            {
                //finds the corresponding item in the hotel list and stores it in a temporary variable
                for(int i = 0; i < this.manager.getNoOfHotels(); i++)
                {
                    if(this.manager.getHotelList().get(i).getName().equals(this.reservationGUI.getSelectedValue()))
                    {
                        hotelSearch = this.manager.getHotelList().get(i);
                    }
                }

                //disposes the current screen and initializes an individual reservation page
                this.currentScreen.dispose();
                this.indivReservation = new createReservationGUI(hotelSearch);
                this.indivReservation.setActionListener(this);
                this.currentScreen = this.indivReservation;
            }
        }

    }

    public void insertUpdate(DocumentEvent e) {
    }

    public void removeUpdate(DocumentEvent e) {
    }

    public void changedUpdate(DocumentEvent e) {

    }

    /**
     * function that handles the events of selected dropdowns
     * @param e the event to be processed
     */
    public void itemStateChanged(ItemEvent e) {
        //the code below executes when the user selects the item in the dropdown in the hotel info screen
        if(e.getStateChange() == ItemEvent.SELECTED && this.currentScreen == this.hotelInfoGUI && e.getSource() != this.hotelInfoGUI.getReservation())
        {
            //the loop finds the room that has the same name as the corresponding item
            for(int i = 0; i < this.hotelSearch.getNoOfRooms();i++)
            {
                if(this.hotelInfoGUI.getSelectedRoom().equals(this.hotelSearch.getRoomList().get(i).getName()))
                {
                    this.roomSearch = this.hotelSearch.getRoomList().get(i);
                }
            }
            //disposes the current screen and initializes an individual room info screen
            this.currentScreen.dispose();
            indivRoomGUI = new roomGUI(roomSearch, hotelSearch);
            indivRoomGUI.initialize();
            indivRoomGUI.setActionListener(this);
            this.currentScreen = this.indivRoomGUI;

        }

        else if(e.getStateChange() == ItemEvent.SELECTED && e.getSource() == this.hotelInfoGUI.getReservation()  && this.currentScreen == this.hotelInfoGUI )
        {
            this.currentScreen.dispose();
            reservationInfo = new reservationGUI(hotelSearch.getReservationList().get(this.hotelInfoGUI.getSelectedReservation()-1));
            reservationInfo.setActionListener(this);
            this.currentScreen = this.reservationInfo;
        }
    }

}
