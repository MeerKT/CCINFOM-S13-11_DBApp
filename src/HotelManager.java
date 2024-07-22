import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for containing all the hotels and the following four main features of the program:
 * <ul>
 * <li>Create Hotel</li>
 * <li>View Hotel</li>
 * <li>Manage Hotel</li>
 * <li>Simulate Booking</li>
 */
public class HotelManager {
    private ArrayList<Hotel> hotelList;

    /**
     * Constructor that initializes the ArrayList hotelList.
     */
    public HotelManager() {
        this.hotelList = new ArrayList<Hotel>();
    }

    /**
     * Asks the user to input the information for creating a hotel,
     * which are its name and the number of rooms present.
     */
    public void createHotel(String name, int noOfRooms) {
        //initializes a hotel with its name
        this.hotelList.add(new Hotel(name));
        //calls the method that generates the rooms of the hotel with its unique naming convention
        this.hotelList.get(this.hotelList.size()-1).generateRooms(1,noOfRooms, 1);
    }

    /**
     * Asks the user which hotel's info they would like to see and shows that hotel's corresponding
     * high-level or low-level information
     */
    public void viewHotel() {
        Scanner scan = new Scanner(System.in);
        int hotelChoice;
        int viewChoice;
        int roomCounter = 0;    //counter for the number of rooms WITH reservations
        int dateChoice;
        int roomChoice;
        int reservationChoice;
        float totalMonth = 0;   //total estimate earnings for the month
        float totalReserv = 0;  //total earning for a reservation 
        int unreserved;         //number of rooms that are unreserved

        //checks if there are currently existing hotels
        if(getNoOfHotels() > 0){
            //prints the name of all hotels
            for(int i = 0; i < this.hotelList.size();i++){
                System.out.println(i+1+" - "+this.hotelList.get(i).getName());
            }

            //asks which hotel the user would like to view
            do {
                System.out.print("Please input the number of which hotel you would like to view: ");
                hotelChoice = scan.nextInt();

                if(hotelChoice < 1 || hotelChoice > getNoOfHotels())
                    System.out.print("Please input a valid hotel number.\n\n");
            } while (hotelChoice < 1 || hotelChoice > getNoOfHotels());

            //asks if the user would like see high level information or low level information
            do { 
                System.out.printf("1 - High Level Information\n2 - Low Level Information\n3 - Exit\n\n");
                System.out.printf("Please input which kind of information you would like to view: ");
                viewChoice = scan.nextInt();

                //error checking
                if(viewChoice < 1 || viewChoice > 3)
                    System.out.printf("Please input a valid option.\n\n");
                
                //High Level Information
                if(viewChoice == 1){
                    System.out.printf("\nName of Hotel: %s\n", this.hotelList.get(hotelChoice-1).getName());
                    System.out.printf("Number of Rooms: %d\n", this.hotelList.get(hotelChoice-1).getNoOfRooms());

                    totalMonth = this.hotelList.get(hotelChoice-1).getTotalRevenue();

                    System.out.printf("Estimate Earnings for the month: Php %.2f\n\n", totalMonth);
                }
                //Low Level Information
                else if(viewChoice == 2){
                    //checking for availability of rooms on a given date
                    do{
                        System.out.print("Please input the day of the month you want to check the availability of: ");
                        dateChoice = scan.nextInt();
                        //error checking
                        if(dateChoice > 31 || dateChoice < 1)
                            System.out.print("Please input a valid date!\n\n");
                    }while(dateChoice > 31 || dateChoice < 1);
        
                    //counts the number of rooms with reservations
                    roomCounter = this.hotelList.get(hotelChoice-1).getReservedRoomCount(dateChoice);
        
                    unreserved = this.hotelList.get(hotelChoice-1).getNoOfRooms()-roomCounter;
        
                    System.out.println("\nThere is/are "+roomCounter+" room/s unavailable(*) on this date and "+unreserved+" room/s that are available!");
        
                    //prints the room list, room names have a * next to them to signify unavailability
                    for(int i = 0; i < this.hotelList.get(hotelChoice-1).getRoomList().size();i++)
                    {
                        System.out.printf("%d - %s", i + 1, this.hotelList.get(hotelChoice-1).getRoomList().get(i).getName());

                        if(this.hotelList.get(hotelChoice-1).getRoomList().get(i) instanceof Executive)
                            System.out.print(" - Executive Room");


                        else if(this.hotelList.get(hotelChoice-1).getRoomList().get(i) instanceof Deluxe)
                            System.out.print(" - Deluxe Room");

                        else
                            System.out.print(" - Regular Room");

                        if(this.hotelList.get(hotelChoice-1).getRoomList().get(i).getIsReservedDay(dateChoice-1))
                            System.out.printf("*");
                        System.out.printf("\n");
                    }
                    
                    //asks which room the user would like to see information on
                    do{
                        System.out.print("\nPlease input the room details you would like to see: ");
                        roomChoice = scan.nextInt();
                        if(roomChoice > this.hotelList.get(hotelChoice-1).getRoomList().size() || roomChoice < 1)
                            System.out.print("Please input a valid room number!\n\n");
                    }while(roomChoice > this.hotelList.get(hotelChoice-1).getRoomList().size() || roomChoice < 1);
        
                    System.out.println("Room name: "+ this.hotelList.get(hotelChoice-1).getRoomList().get(roomChoice-1).getName());
                    System.out.println("Room price: "+ this.hotelList.get(hotelChoice-1).getRoomList().get(roomChoice-1).getPrice());
                    System.out.print("This room is unavailable on the following dates:\n");
        
                    //prints the dates of all the reservations of the selected room
                    for(int i = 0;i < this.hotelList.get(hotelChoice-1).getReservationList().size();i++)
                    {
                        if(this.hotelList.get(hotelChoice - 1).getRoomList().get(roomChoice - 1).getName().equals(this.hotelList.get(hotelChoice - 1).getReservationList().get(i).getRoom().getName()));
                            System.out.print(this.hotelList.get(hotelChoice - 1).getReservationList().get(i).getcheckIn()+" to "+this.hotelList.get(hotelChoice - 1).getReservationList().get(i).getcheckOut()+"\n");
                    }
                    System.out.println("");

                    //checks if a reservation has been made
                    if(this.hotelList.get(hotelChoice-1).getReservationCount() == 0)
                        System.out.printf("No reservations have been made.\n\n");
                    else{
                        for(int i = 0; i < this.hotelList.get(hotelChoice-1).getReservationList().size();i++)
                            System.out.println(i+1+" - "+this.hotelList.get(hotelChoice-1).getReservationList().get(i).getName());
                        do{
                            System.out.print("\nPlease input the reservation info you would like to see: ");
                            reservationChoice = scan.nextInt();

                            if(reservationChoice < 1 || reservationChoice > this.hotelList.get(hotelChoice-1).getReservationList().size())
                                System.out.print("Please give a valid input!\n\n");
                        }while(reservationChoice < 1 || reservationChoice > this.hotelList.get(hotelChoice-1).getReservationList().size());

                        System.out.println("\nGuest name: "+ this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getName());
                        System.out.println("Check-in Date: "+ this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getcheckIn());
                        System.out.println("Check-out Date: "+ this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getcheckOut());
                        System.out.println("Room Name: "+this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getRoom().getName());

                        int nightsStayed = this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getcheckOut() - this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getcheckIn();
                        totalReserv = nightsStayed * this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getTotalPrice();

                        System.out.println("Total Price Breakdown: "+nightsStayed+" nights * Php "+ this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getRoom().getPrice()+" = Php "+totalReserv+"\n");
                    }
                }
            } while (viewChoice != 3);
        }
        else
            System.out.printf("No hotels have been created.\n\n");
    }

    /**
     * Asks the user which hotel they would like to simulate a booking for
     * and the necessary details to make a reservation
     */
    public void simulateBooking() {
        int choice;
        Scanner scan = new Scanner(System.in);

        //checks if there is a currently existing hotel
        if(getNoOfHotels() > 0){
            //prints the name of all hotels
            for(int i = 0; i < this.hotelList.size();i++){
                System.out.println(i+1+".) "+this.hotelList.get(i).getName());
            }
            
            //asks which hotel they would like to make a booking for
            do {
                System.out.print("Please input which hotel you would like to create a booking for: ");
                choice = scan.nextInt();

                //error checking
                if(choice < 1 || choice > getNoOfHotels())
                    System.out.print("Please select a valid hotel number\n\n");
            } while (choice < 1 || choice > getNoOfHotels());

            //calls the simulateBooking method in the Hotel class
            this.hotelList.get(choice-1).simulateBooking();
        }
        else
            System.out.print("No hotels have been created.\n\n");
    }

    /**
     * Asks the user which hotel they would like to change and allows them to change
     * the name, add room(s), remove a room, update the pricing, remove reservations,
     * and remove the hotel itself
     */
    public void manageHotel() {
        Scanner scan = new Scanner(System.in);
        int hotelChoice;
        int choice;
        boolean isValid;
        int roomChoice;
        int reservationChoice;

        if(getNoOfHotels() > 0){
            //prints the name of all hotels
            for(int i = 0; i < this.hotelList.size();i++){
                System.out.printf("%d - %s\n", i+1 ,this.hotelList.get(i).getName());
            }

            do {
                System.out.print("\nPlease input the number of which hotel you would like to configure: ");
                hotelChoice = scan.nextInt();

                if(hotelChoice < 1 || hotelChoice > getNoOfHotels())
                    System.out.print("Please input a valid hotel number.\n\n");
            } while (hotelChoice < 1 || hotelChoice > getNoOfHotels());


            do {
                System.out.println("1 - Change name");
                System.out.println("2 - Add room");
                System.out.println("3 - Remove room");
                System.out.println("4 - Update pricing");
                System.out.println("5 - Remove Reservation");
                System.out.println("6 - Remove hotel");
                System.out.println("7 - Exit");
                System.out.print("\nWhat would you like to do?: ");
                choice = scan.nextInt();
                scan.nextLine();
                if(choice > 7 || choice < 1)
                    System.out.print("Please input a valid choice!\n\n");
                
                //change name
                else if(choice == 1)
                {
                    String name;
                    do {
                        System.out.print("Please input the name of the hotel: ");
                        name = scan.nextLine();

                        //error check
                        if(name.length() == 0)
                            System.out.print("Please input a valid name.\n\n");

                        isValid = true;
                        for(int i = 0; i < this.hotelList.size(); i++) {
                            if(name.equals(this.hotelList.get(i).getName()))
                                isValid = false;
                        }

                        if(!isValid)
                            System.out.print("Please ensure that the name is unique!\n\n");

                    } while (name.length() == 0 || !isValid);

                    this.hotelList.get(hotelChoice-1).setName(name);
                    System.out.print("Name successfully changed!\n\n");
                }

                //add room
//                else if(choice == 2)
//                {
//                    int option;
//
//                    do{
//                        System.out.println("1 - Deluxe (20% more expensive)\n2 - Executive (35% more expensive)\n3 - Regular Room (Base Price)");
//                        System.out.print("Please input the kind of room you would like to add: ");
//                        option = scan.nextInt();
//
//                        if(option > 3 || option < 1)
//                            System.out.println("Please input a valid option!");
//                    }while(option > 3 || option < 1);
//
//
//                    this.hotelList.get(hotelChoice-1).addRoom(option);
//                    System.out.print("Room successfully added!\n\n");
//                }

                //remove room
                else if (choice == 3)
                {
                    if(this.hotelList.get(hotelChoice-1).getRoomList().get(0).getIsReserved() && this.hotelList.get(hotelChoice-1).getNoOfRooms() == 1)
                        System.out.printf("There is only 1 room in the hotel and it currently has a reservation.\n\n");
                    else{
                        for(int i = 0; i < this.hotelList.get(hotelChoice-1).getRoomList().size();i++)
                        {
                            System.out.println(i+1+" - "+this.hotelList.get(hotelChoice-1).getRoomList().get(i).getName());
                            if(this.hotelList.get(hotelChoice-1).getRoomList().get(i) instanceof Executive)
                                System.out.print(" - Executive Room");


                            else if(this.hotelList.get(hotelChoice-1).getRoomList().get(i) instanceof Deluxe)
                                System.out.print(" - Deluxe Room");

                            else
                                System.out.print(" - Regular Room");
                        }
                        do{
                            System.out.print("Please input the room you would like to remove: ");
                            roomChoice = scan.nextInt();
                        
                            if(this.hotelList.get(hotelChoice-1).getRoomList().get(roomChoice-1).getIsReserved())
                                System.out.print("Room currently has a reservation!\n\n");
                            else if(roomChoice < 1 || roomChoice > this.hotelList.get(hotelChoice-1).getRoomList().size())
                                System.out.print("Please give a valid input!\n\n");
                        }while(roomChoice < 1 || roomChoice > this.hotelList.get(hotelChoice-1).getRoomList().size() || this.hotelList.get(hotelChoice-1).getRoomList().get(roomChoice-1).getIsReserved());

                        this.hotelList.get(hotelChoice-1).getRoomList().remove(roomChoice-1);
                        System.out.print("Room successfully removed!\n\n");
                    }
                }

                //update pricing
                else if(choice == 4)
                {
                    if(this.hotelList.get(hotelChoice-1).getReservationCount() != 0)
                        System.out.printf("Price cannot be adjusted as there is still at least 1 room with a reservation.\n\n");
                    else{
                        float newPrice;
                        do { 
                            System.out.printf("Please input a price: ");
                            newPrice = scan.nextFloat();

                            if(newPrice < 100.0f)
                                System.out.printf("Invalid input.\n\n");
                        } while (newPrice < 100.0f);
                        
                        this.hotelList.get(hotelChoice-1).setPrice(newPrice);
                        System.out.println("Price successfully updated!\n");
                    }
                }

                //remove reservation
                else if(choice == 5)
                {
                    if(this.hotelList.get(hotelChoice-1).getReservationCount() == 0)
                        System.out.printf("No reservations have been made.\n\n");
                    else{
                        for(int i = 0; i < this.hotelList.get(hotelChoice-1).getReservationList().size();i++)
                            System.out.printf("%d - %s (Room %s Days %d-%d)\n", i+1, this.hotelList.get(hotelChoice-1).getReservationList().get(i).getName(), this.hotelList.get(hotelChoice-1).getReservationList().get(i).getRoom().getName(), this.hotelList.get(hotelChoice-1).getReservationList().get(i).getcheckIn(), this.hotelList.get(hotelChoice-1).getReservationList().get(i).getcheckOut());
                
                        do{
                            System.out.print("Please input the reservation you would like to remove: ");
                            reservationChoice = scan.nextInt();

                            if(reservationChoice < 1 || reservationChoice > this.hotelList.get(hotelChoice-1).getReservationList().size())
                                System.out.println("Please give a valid input!");
                        }while(reservationChoice < 1 || reservationChoice > this.hotelList.get(hotelChoice-1).getReservationList().size());

                        for(int i = this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getcheckIn(); i < this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getcheckOut(); i++){
                            this.hotelList.get(hotelChoice-1).getReservationList().get(reservationChoice-1).getRoom().changeDayAvailability(i-1);
                        }
                        this.hotelList.get(hotelChoice-1).getReservationList().remove(reservationChoice-1);

                        System.out.println("Reservation successfully removed!\n");
                    }
                }

                //remove hotel
                else if(choice == 6)
                {
                    this.hotelList.remove(hotelChoice-1);
                    System.out.println("Hotel has been successfully removed!\n");
                }
            }while(choice != 6 && choice != 7);
        }

        else
            System.out.println("No hotels have been created.\n");

    }

    /**
     * Getter function for the number of hotels
     * @return this.hotelList.size
     */
    public int getNoOfHotels(){
        return this.hotelList.size();
    }

    public ArrayList<Hotel> getHotelList() { return this.hotelList; }
}
