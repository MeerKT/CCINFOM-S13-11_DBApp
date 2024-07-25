/**
 * Responsible for handling all the data needed for a customer to reserve a hotel room
 */
public class Reservation {
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room chosenRoom;
    private String discountCode;
    private float pricePerNight;
    private float totalPrice;
    private boolean isDiscounted;

    /**
     * Constructor for the Reservation class
     * @param guestName - name of the guest reserving a hotel room
     * @param checkIn - the day the guest will check-in to their hotel room
     * @param checkOut - the day the guest will check-out
     * @param chosenRoom - the room the guest will stay at
     * @param discountCode - string containing the possibly valid discount code
     * @param datePriceModifier - float array containing the price modifier for each individual date
     */
    public Reservation(String guestName, int checkIn, int checkOut, Room chosenRoom, String discountCode, float[] datePriceModifier) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.chosenRoom = chosenRoom;
        this.discountCode = discountCode;
        this.pricePerNight = chosenRoom.getPrice();
        this.isDiscounted = false;
        computeTotalPrice(datePriceModifier);   //initializes totalPrice

        //applies discount to the total price if able
        applyDiscount(this.discountCode);

        //changes the reserved status of a room up until the day before the checkout day
        //(so that it may be possible to check in the day of a check-out)
        for(int i = checkIn - 1; i < checkOut - 1; i++){
            this.chosenRoom.changeDayAvailability(i);
        }
    }

    /**
     * Computes for the total price of the reservation
     * @param datePriceModifier - float array containing the price modifier for each individual date
     */
    public void computeTotalPrice(float[] datePriceModifier){
        for(int i = this.checkIn; i < this.checkOut; i++){
            this.totalPrice += pricePerNight * datePriceModifier[i-1];
        }
    }

    /**
     * Applies discount based on the given discount code
     * @param discountCode - the discount code to be applied
     */
    public void applyDiscount(String discountCode) {
        boolean coversDay15or30 = false;

        if(discountCode.equals(DiscountCodes.DISCOUNT_FLAT)) {
            this.totalPrice *= 0.9; //10% discount
            this.isDiscounted = true;
        }
        if(discountCode.equals(DiscountCodes.DISCOUNT_GET1FREE)) {
            if (this.checkOut - this.checkIn >= 5) {
                this.totalPrice -= this.pricePerNight; //first night is free
                this.isDiscounted = true;

            }
        }
        if(discountCode.equals(DiscountCodes.DISCOUNT_PAYDAY)){
            if (this.checkIn <= 15 && this.checkOut > 15) {
                coversDay15or30 = true;
            }
            else if (this.checkIn <= 30 && this.checkOut > 30) {
                coversDay15or30 = true;
            }

            if (coversDay15or30) {
                this.totalPrice *= 0.93; //7% discount
                this.isDiscounted = true;
            }
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

    /**
     * returns true if the discount code exists and false if it does not
     * @param discountCode - the discount code to be checked
     * @return
     */
    public boolean checkDiscountCode(String discountCode){
        return discountCode.equals(DiscountCodes.DISCOUNT_FLAT) || discountCode.equals(DiscountCodes.DISCOUNT_GET1FREE) || discountCode.equals(DiscountCodes.DISCOUNT_PAYDAY);
    }

    /**
     *
     * @return the boolean value that represents whether the reservation is discounted
     */
    public boolean getDiscount() { return this.isDiscounted; }

}
