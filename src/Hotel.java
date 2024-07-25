import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for representing a single hotel.
 */
public class Hotel{
    private String name;
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList;
    private float[] datePriceModifier;

    /**
     * Hotel constructor that initiailizes the hotel's name, list of rooms,
     * list of reservations, and date price modifier.
     * @param name - unique identifier for the hotel
     */
    public Hotel(String name) {
        this.name = name;
        this.roomList = new ArrayList<Room>();
        this.reservationList = new ArrayList<Reservation>();
        this.datePriceModifier = new float[31];

        //initialize all date price modifiers to 1
        java.util.Arrays.fill(datePriceModifier, 1.0f);
    }

    /**
     * Algorithm for generating the room number system that follows the following pattern:
     * <p>
     * A01, A02, ..., A10, B01, ..., E01, ... E10</p>
     * @param option - kind of room to generate, 1 is Standard, 2 is Deluxe, 3 is Executive
     * @param noOfRooms - the number of rooms to be generated
     * @param start - the starting value, corresponds to the number for the room that will directly be generated next
     */
    public void generateRooms(int option, int noOfRooms, int start){
        //algorithm for the room number system (A01-A10, B01-B10, ..., E01-E10)
        char letter = 'A';
        int countPerFloor;

        //checks for cases where you're counting past A10 and adjusts the values
        if(start > 10){
            int tens = start/10;
            start = start%10;
            letter += tens;
        }

        noOfRooms += start-1;
        int dupeMax = noOfRooms;

        /*
            (noOfRooms+9)/10 make the following true for a given noOfRooms value:
             0-10 = 1
            11-20 = 2
            21-30 = 3
            31-40 = 4
            41-50 = 5

            The resulting value refers to the number of 'floors'
        */
        for(int i = 1; i <= (noOfRooms+9)/10; i++){
            if(dupeMax>10)              //checks if the number of rooms is higher than 10 for the inner loop
                countPerFloor = 10;
            else
                countPerFloor = dupeMax;    ///number is from 1-10

            for(int j = start; j <= countPerFloor; j++){
                if(j < 10)
                {
                    switch(option) {
                        default:
                            //Character.toString allows for string concatenation
                            this.roomList.add(new Room(Character.toString(letter) + 0 + j));    //adds a 0 in between the floor letter and the number value
                            break;
                        case(2):
                            this.roomList.add(new Deluxe(Character.toString(letter) + 0 + j));    //adds a 0 in between the floor letter and the number value
                            break;
                        case(3):
                            this.roomList.add(new Executive(Character.toString(letter) + 0 + j));    //adds a 0 in between the floor letter and the number value
                            break;
                    }

                }

                else{
                    switch(option) {
                        default:
                            this.roomList.add(new Room(Character.toString(letter) + j));
                            start = 1;
                            break;

                        case(2):
                            this.roomList.add(new Deluxe(Character.toString(letter) + j));
                            start = 1;
                            break;

                        case(3):
                            this.roomList.add(new Executive(Character.toString(letter) + j));
                            start = 1;
                            break;
                    }
                }
            }
            //increase the letter (A -> B -> C...) and subtract the number of rooms by 10 when a single floor has finished generating
            letter++;
            dupeMax -= 10;
        }
    }

    /**
     * returns the value of the name of the hotel
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the value of the number of rooms in the hotel
     * @return this.roomList.size()
     */
    public int getNoOfRooms() {
        return this.roomList.size();
    }

    /**
     * returns the number of reservations made in the hotel
     * @return this.reservationList.size()
     */
    public int getReservationCount() {
        return this.reservationList.size();
    }

    /**
     * returns the Room ArrayList roomList
     * @return this.roomList
     */
    public ArrayList<Room> getRoomList() { return this.roomList; }

    /**
     * returns the Reservation ArrayList reservation
     * @return this.reservationList
     */
    public ArrayList<Reservation> getReservationList() { return this.reservationList; }

    /**
     * sets the price of all rooms in a hotel to a given value
     * @param newPrice - the new price to be set for every room in the hotel
     */
    public void setPrice(float newPrice) {
        for(int i = 0; i < this.roomList.size();i++)
            this.roomList.get(i).setPrice(newPrice);
        }

    /**
     * Shows the user the list of available rooms and asks the user for all necessary
     * information to create a booking (the room name and/or number, guest name, check-in,
     * and check-out day).
     * <p>
     * Method ends by creating a new instance of Reservation
     */
    public void simulateBooking( int roomNo,int checkIn, int checkOut, String guestName, String discountCode ){

        if(discountCode.isEmpty())
        {
            discountCode = "empty";
        }

        this.reservationList.add(new Reservation(guestName, checkIn, checkOut, roomList.get(roomNo), discountCode, this.datePriceModifier));

        System.out.printf("\nReservation Created!\n");
    }

    /**
     * returns the index value of a room when given its name.
     * @param roomName - the unique string identifier of a room (e.g. A01)
     * @return the index value of the hotel room or -1 when the given room name does not exist
     */
    public int getRoomNo(String roomName){
        for(int i = 0; i < getNoOfRooms(); i++)
            if(roomList.get(i).getName().equals(roomName))  //check if two rooms contain the same name
                return i;

        return -1;
    }

    /**
     * returns the total amount of revenue made through reservations
     * @return a float value that represents the amount of money a hotel has made throughout the entire month
     */
    public float getTotalRevenue() {
        float totalMonth = 0f;
        for(int i = 0; i < this.getReservationCount(); i++)
            totalMonth += getReservationList().get(i).getTotalPrice();

        return totalMonth;
    }

    /**
     * function counts the amount of rooms that are unavailable on a certain date
     * @param date is the date of which the program will check every room's availability
     * @return an integer value that represents the amount of rooms that are reserved on the given date
     */
    public int getReservedRoomCount(int date)
    {
        int roomCounter = 0;
        for(int i = 0; i < getReservationList().size();i++)
        {
            if(date == getReservationList().get(i).getcheckIn() || (date > getReservationList().get(i).getcheckIn() && date < getReservationList().get(i).getcheckOut()))
                roomCounter++;
        }

        return roomCounter;
    }

    /**
     * Sets the name of a hotel to a given new one.
     * @param newName - the new name of the hotel
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Asks user how many rooms they would like to add to the hotel and checks
     * if the inputted number is valid or not.
     * <p>
     * If valid, the method will call the generateRooms method to create the new rooms.
     * If invalid, the method will loop until the user gives a valid input.
     */
    public void addRoom(int option, int num) {
        generateRooms(option, num, getNoOfRooms()+1);
    }

    /**
     * Changes the value for the date price modifier given a date
     * @param date - date to changed
     * @param modifier - the new modifier to be applied
     */
    public void setDatePriceModifier(int date, float modifier){
        this.datePriceModifier[date-1] = modifier/100.0f;
    }

    /**
     * Returns the value for the date price modifier given a date
     * @param date - date to be returned
     * @return date a floating point value that represents the date price modifier
     */
    public float getDatePriceModifier(int date){
        return this.datePriceModifier[date-1];
    }
}