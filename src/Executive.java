public class Executive extends Room {

    /**
     * Constructor for a deluxe room that uses the Room class' constructor to initialize the given name,  the base price at Php 1,299,
     * and the isReserved boolean static array with a size of 31 (corresponding to
     * the number of days in a month). Also all elements of the array are initialized to false as
     * no rooms have been reserved at the time of creating them. The price is then modified to add 35% to the final price
     *
     * @param name - the unique identifier for a room following a specific naming convention
     */
    public Executive(String name) {
        super(name);
        this.price += (this.price * 0.35f);
    }

    /**
     * sets the base price of a room per night to a given new one and adds the executive modifier
     * @param newPrice - the new price of the room
     */

    public void setPrice(float newPrice)
    {
        this.price = newPrice + (newPrice * 0.20f);
    }
}
