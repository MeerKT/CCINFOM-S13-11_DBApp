import java.util.Arrays;
/**
 * Responsible for handling all the data corresponding to a hotel room
 */
public class Room {
    /**
     * String containing the name of the room (A01, A02, ..., A10, B01, ...)
     */
    protected String name;
    /**
     * Floating point value indicating the price of the room
     */
    protected float price;
    /**
     * Boolean array containing whether a room is reserved or not on a specific day
     */
    protected Boolean[] isReserved;

    /**
     * Constructor for room that initializes the given name, sets the base price to Php 1,299,
     * and initializes the isReserved boolean static array with a size of 31 (corresponding to
     * the number of days in a month). Also all elements of the array are initialized to false as
     * no rooms have been reserved at the time of creating them.
     * @param name - the unique identifier for a room following a specific naming convention
     * @param price - the price of the room
     */
    public Room(String name, float price) {
        this.name = name;
        this.price = price;
        this.isReserved = new Boolean[31];

        //initialize all elements of the array to false
        Arrays.fill(isReserved, false);
    }

    /**
     * returns the name/unique idenifier following a specified naming convention of the room
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the base price of a room per night
     * @return this.price
     */
    public float getPrice() {
        return this.price;
    }

    /**
     * sets the base price of a room per night to a given new one
     * @param newPrice - the new price of the room
     */
    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    /**
     * Changes the availability/reserved status of a given day. If a day
     * is already reserved, this method will change it to available, and
     * vice versa.
     * @param day - the day to be changed (from 0 to 30)
     */
    public void changeDayAvailability(int day) {
        this.isReserved[day]  = !this.isReserved[day];
    }

    /**
     * gets the value of the availability/reserved status of a given day
     * @param day - the day to checked (from 0 to 30)
     * @return this.isReserved[day]
     */
    public Boolean getIsReservedDay(int day) {
        return this.isReserved[day];
    }

    /**
     * Checks if AT LEAST ONE element of the boolean array is true
     * @return true once it has found 1 element of the boolean array with
     * a value of true, and false otherwise
     */
    public Boolean getIsReserved(){
        for(int i = 0; i < this.isReserved.length; i++)
            if(this.isReserved[i])
                return true;
        
        return false;
    }

    /**
     * Checks if EVERY element of the boolean array is true
     * @return false once it has found 1 element that is false,
     * returns true otherwise
     */
    public Boolean checkIfAllReserved(){
        for(int i = 0; i < this.isReserved.length; i++)
            if(!this.isReserved[i])  //if any day is not reserved, it's available (false)
                return false;
        
        return true;
    }

    /**
     * finds the maximum possible value a user can check-out of a room to avoid
     * any possible overlap in reservations.
     * @param checkIn - starting value that corresponds to the day the guest will check-in
     * @return the index+1 once it has found another reservation,
     * returns the max possible value otherwise
     */
    public int checkMaxAvailable(int checkIn){
        for(int i = checkIn; i < isReserved.length; i++)
            if(this.isReserved[i])
                return i+1;
        
        return isReserved.length;
    }
}
