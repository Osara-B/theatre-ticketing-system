public class Ticket {                                                                  //Ticket class created with private attributes row,seat,price and person (Person class)
    private int row;
    private int seat;
    private double price;
    private Person person;                                                             //person attribute of the class Person
    private int ticketNum;

    //Parameterized Constructor created with all the parameters
    public Ticket(int row, int seat, double price, Person person,int ticketNum) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
        this.ticketNum = ticketNum;
    }
    //Getters are used to access the private attributes to compare the tickets data in the ArrayList of tickets
    public int getRow() {
        return row;
    }
    public int getSeat() {
        return seat;
    }
    public double getPrice() {
        return price;
    }

    //Print method that is used to print the information of a ticket
    public void print(){
        System.out.println("-----------TICKET INFORMATION-----------");
        System.out.println(" Ticket number   - "+ ticketNum);
        System.out.println(" User name       - "+ person.getName());
        System.out.println(" User surname    - "+ person.getSurname());
        System.out.println(" User email      - "+ person.getEmail());
        System.out.println(" Row Number      - "+ row);
        System.out.println(" Seat Number     - "+ seat);
        System.out.println(" Ticket Price    - "+ price);
        System.out.println("----------------------------------------");

    }
}
