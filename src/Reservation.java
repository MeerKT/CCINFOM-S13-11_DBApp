/**
 * Responsible for handling all the data needed for a customer to reserve a hotel room
 */
public class Reservation {
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room chosenRoom;
    private float pricePerNight;
    private float totalPrice;

    /**
     * Constructor for the Reservation class
     * @param guestName - name of the guest reserving a hotel room
     * @param checkIn - the day the guest will check-in to their hotel room
     * @param checkOut - the day the guest will check-out
     * @param chosenRoom - the room the guest will stay at
     */
    public Reservation(String guestName, int checkIn, int checkOut, Room chosenRoom) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.chosenRoom = chosenRoom;
        this.pricePerNight = chosenRoom.getPrice();
        this.totalPrice = (this.checkOut - this.checkIn) * this.pricePerNight;  //number of nights spent * price per night

        //changes the reserved status of a room up until the day before the checkout day
        //(so that it may be possible to check-in the day of a check-out)
        for(int i = checkIn - 1; i < checkOut - 1; i++){
            this.chosenRoom.changeDayAvailability(i);
        }
    }

    /**
     * returns the name of the guest of a reservation
     * @return this.guestName
     */
    public String getName() {
        return this.guestName;
    }

    /**
     * returns the room selected in the reservation
     * @return this.chosenRoom
     */
    public Room getRoom (){
        return this.chosenRoom;
    }

    /**
     * sets the chosen room to a new one
     * @param newRoom - the new room to be set
     */
    public void setRoom(Room newRoom) {
        this.chosenRoom = newRoom;
    }

    /**
     * returns the check-in day of a reservation
     * @return this.checkIn
     */
    public int getcheckIn() { return this.checkIn; }

    /**
     * returns the check-out day of reservation
     * @return this.checkOut
     */
    public int getcheckOut(){ return this.checkOut; }

    /**
     * returns the base price for one night spent in a room
     * @return this.pricePerNight
     */
    public float getPricePerNight() { return this.pricePerNight; }

    /**
     * returns the total price a guest must spend for their reservation
     * @return this.totalPrice
     */
    public float getTotalPrice() { return this.totalPrice; }
}
