// 20221009 W1954099 Osara Bandara
//4COSC010C Coursework Submission (2023/02/25)
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Theatre {
    public static void main(String[] args) {
        int[][] seatMap = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,}};       //A multidimensional array with 3 arrays representing the rows and seats
        ArrayList<Ticket> tickets = new ArrayList<>();                                //creating an array lists to store Tickets objects
        System.out.print("Welcome to the New Theatre!");
        while(true) {
            try {
                System.out.print("""
                                            
                        -------------------------------------------------
                         1) Buy a ticket
                         2) Print seating area
                         3) Cancel ticket
                         4) List available seats
                         5) Save to file
                         6) Load from file
                         7) Print ticket information and total price
                         8) Sort tickets by price\s
                         0) Quit
                        -------------------------------------------------
                         Enter Option : \s""");                                                  //Menu is printed
                Scanner input = new Scanner(System.in);                                          //Scanner is inside the try block to get new input after exception
                int choice = input.nextInt();

                switch (choice) {                                                               //Input taken from the user runs the respective code using switch
                    case 1:                                                //input 1 to buy tickets
                        buy_ticket(seatMap, tickets);
                        break;
                    case 2:                                                //input 2 to print the seating area
                        print_seating_area(seatMap);
                        break;
                    case 3:                                                //input 3 to cancel tickets
                        cancel_ticket(seatMap, tickets);
                        break;
                    case 4:                                                //input 4 to show the available tickets
                        show_available(seatMap);
                        break;
                    case 5:                                                //input 5 to save the content of the array to a file
                        save(seatMap);
                        break;
                    case 6:                                                //input 6 to load the content from the file to the arrays
                        load(seatMap);
                        break;
                    case 7:                                                //input 7 to print ticket information and total price
                        show_tickets_info(tickets);
                        break;
                    case 8:                                                //input 8 to sort the tickets and print in order
                        sort_tickets(tickets);
                        break;
                    case 0:                                                //input 0 to buy quit the program
                        System.out.println("--YOU QUIT THE PROGRAM--");
                        break;
                    default:
                        System.out.println(("\n ---ERROR: Invalid input. Please enter an option in the menu---"));
                }
                if (choice == 0) {                                        //To quit from the infinite while loop
                    break;
                }
            }catch(InputMismatchException e){                            //Code block if a non integer value is entered
                System.out.println("\n ---ERROR: Please enter numeric value from the menu---");
            }
        }
    }
    public static void buy_ticket(int[][] array,ArrayList<Ticket> tickets){
        while (true) {
            try {
                int rowNum = getRow();                                        //Row number input
                if (1>rowNum || rowNum>array.length){                         //Validates row number
                    System.out.println("\n ---ERROR: This row number does not exist. Please select 1-3---");
                    int userChoice = userChoice("\n  || Enter 1 to buy ticket or 0 to return to menu || \n  -- >");
                    if (userChoice==0){
                        break;
                    }else {
                        continue;
                    }
                }
                int seatNum = getSeat();                                     //Seat number input
                if (array[rowNum - 1][seatNum - 1] == 0) {                   //Checks the status of the seat
                    System.out.println(" --SEAT AVAILABLE--");
                    array[rowNum - 1][seatNum - 1] = 1;
                    createObjects(rowNum,seatNum,tickets);                    //Creates objects using the method
                    System.out.println(" --SEAT BOUGHT SUCCESSFULLY-- ");
                    break;
                } else if (array[rowNum - 1][seatNum - 1] == 1) {
                    System.out.println("\n ---ERROR: This seat is reserved. Please select an available seat---");
                }
            } catch (IndexOutOfBoundsException e){                      //Validates seat number
                System.out.println("\n ---ERROR: This seat number does not exist. Row 1-12 Seats | Row 2-16 Seats | Row 3-20 Seats---");
            }catch (InputMismatchException e){                          //Checks and loops for numeric input
                System.out.println("\n ---ERROR: Input for row and seat number should be numeric---");
            }
            int userChoice = userChoice("\n  || Enter 1 to buy ticket or 0 to return to menu || \n  -- >");
            if (userChoice==0){
                break;
            }
        }
    }
    public static void print_seating_area(int[][] array){
        System.out.println("     ***********\n     *  STAGE  *\n     ***********");
        for (int i=0; i<3; i++){                                            //For loop traverses though the rows with variable i
            if (i==0) {System.out.print("    ");}
            else if (i==1) {System.out.print("  ");}                        //Formatting the output
            for (int j=0; j<array[i].length; j++){                          //Nested for loop is used to iterate through the seats using variable j in seat array
                if (j== (array[i].length/2)-1) {
                    System.out.print(letter(array[i][j]) + " ");            //Formatting the space in the middle of the rows & printing X or O using the letter method ()
                }
                else {
                    System.out.print(letter(array[i][j]));                  //Printing X or O using the letter method that converts 1 to X and 0 to O
                }
            }
            System.out.println();                                           //Goes to new line to print new row
        }
    }
    public static void cancel_ticket(int[][] array, ArrayList<Ticket> tickets){
        while (true) {
            try {
                int rowNum = getRow();
                if (1>rowNum || rowNum>array.length){                                       //Validates rowNum number
                    System.out.println("\n ---ERROR: This rowNum number does not exist. Please select 1-3---");
                    int userChoice = userChoice("\n  || Enter 1 to cancel ticket or 0 to return to menu || \n  -- >");
                    if (userChoice==0){
                        break;
                    }else {
                        continue;
                    }
                }
                int seatNum = getSeat();
                if (array[rowNum - 1][seatNum - 1] == 0) {                                  //Checks the status of the seat
                    System.out.println("\n ---ERROR: This seatNum isn't bought yet! Cannot be cancelled---");
                } else if (array[rowNum - 1][seatNum - 1] == 1) {
                    array[rowNum - 1][seatNum - 1] = 0;                                   //Changes status of seatNum to 0 if seat is bought
                    for (int i=0; i< tickets.size();i++){
                        Ticket ticket = tickets.get(i);
                        if (ticket.getRow()== rowNum && ticket.getSeat() == seatNum){     //Checks for ticket object in the array with the same row and seat number
                            tickets.remove(i);                                      //Corresponding ticket object in the array is removed
                        }
                    }
                    System.out.println(" --SEAT CANCELLED--");
                    break;
                }
            } catch (IndexOutOfBoundsException e){                                 //Validates seat number
                System.out.println("\n ---ERROR: This seat number does not exist. Row 1-12 Seats | Row 2-16 Seats | Row 3-20 Seats---");
            } catch (InputMismatchException e){                                   //Checks nad loops for numeric input
                System.out.println("\n ---ERROR: Input for row and seat number should be numeric---");
            }
            int userChoice = userChoice("\n  || Enter 1 to cancel ticket or 0 to return to menu || \n  -- >");
            if (userChoice==0){
                break;
            }
        }
    }
    public static void show_available(int[][] array){
        printAvailable("row 1",array[0]);                                           //Uses a method to print the available seats to avoid repetition
        printAvailable("row 2",array[1]);
        printAvailable("row 3",array[2]);
    }
    public static void save(int[][] array){
        try{
            File myFile  = new File("seating_plan.txt");                        //Creating a new file named seating_plan in the same folder (if it doesn't exist yet)
            FileWriter writing = new FileWriter("seating_plan.txt");            //FileWriter class create to write text into the seating_plan.txt file

            for (int i=0; i< array.length ;i++){                                    //Iterates through seatMap array & writes each seat in a row to file with a space between
                for (int j=0; j < array[i].length ; j++){
                    writing.write(array[i][j]+" ");
                }
                writing.write("\n");                                                //Goes to a new line to write a new row into the text file
            }
            writing.close();
            System.out.println(" ---Seating plan saved to file---");
        } catch (IOException e){
            System.out.println(" ---ERROR: Could not save to file---");                //Catch block that executes if there is an error when accessing the file
        }
    }
    public static void load(int[][] array){
        try{
            Scanner scanner = new Scanner(new File("seating_plan.txt."));              //Scanner class used to read file content
            int i = 0;
            while(scanner.hasNextLine() && i< array.length){                             //while loop executes if there is another line in the file for array length(3)
                String singleRow = scanner.nextLine();                                   //scans a line in the file (A row according to how the data was written)
                String[] rowSeats = singleRow.split(" ");                          //creates an array separated by " " and stores into an array named rowSeats
                for (int j=0; j < array[i].length && j< rowSeats.length; j++){
                    array[i][j]= Integer.parseInt(rowSeats[j]);                         //changes the type of the seat number back to integers and assigns to the SeatMap
                }
                i++;
            }
            System.out.println(" ---Seats loaded from file---");
        }catch (IOException e){
            System.out.println(" ---ERROR: Could not load from file---");                       //Catch block that executes if there is an error when reading the file
        }catch (NumberFormatException e){
            System.out.println(" ---ERROR: File content is invalid---");                        //Catch block if file content is not numerical
        }
    }
    public static void show_tickets_info(ArrayList<Ticket> tickets) {
        double total_price = 0.0;                               //Initialises total to 0
        System.out.println("\n");
        for (Ticket ticket : tickets) {                         //Prints ticket info using print method in Ticket class and calculates total
            ticket.print();
            total_price += ticket.getPrice();
        }
        System.out.println("----------------------------------------\n" +
                "TOTAL PRICE      - " + total_price);            //Calculates total
    }
    public static void sort_tickets(ArrayList<Ticket> tickets){
        ArrayList<Ticket> sortedTickets = new ArrayList<>();                                  //Created new array sortedTickets and stores list of the Ticket objects sorted by price
        sortedTickets = mergeSort(tickets,0,(tickets.size()-1));
        for (Ticket ticket:sortedTickets){                                                   //Prints sorted ticket array
            ticket.print();
        }
    }
    public static int getRow(){                                            //Ask user for row number and returns input
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter the row number: ");
        return input.nextInt();
    }
    public static int getSeat(){                                            //Ask user for seat number and returns input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the seat number: ");
        return input.nextInt();
    }
    public static String letter(int seat) {                                 //Coverts 1 to X and 0 to O when printing the seats (in the print_seating_area method)
        if (seat==1){
            return "X";
        } else {
            return "O";}
    }
    public static void createObjects(int rowNum,int seatNum, ArrayList<Ticket> tickets) {
        Scanner input = new Scanner(System.in);
        String name;                            //Creates local variable, Code block validates name to contain an alphabetic word and gets input
        while (true) {
            System.out.print("Enter your name: ");
            name = input.nextLine();
            if (!name.matches("^[a-zA-Z]*$")){
                System.out.println("\n ---ERROR: Please enter alphabetic characters for the name (One Word)---");
            } else { // name validated
                break;
            }
        }
        String surname;                         //Creates local variable, Code block validates name to have an alphabetic word and gets input for surname
        while (true) {
            System.out.print("Enter your surname: ");
            surname = input.nextLine();
            if (!surname.matches("^[a-zA-Z]*$")){
                System.out.println("\n ---ERROR: Please enter alphabetic characters for the surname (One Word)---");
            } else { // surname validated
                break;
            }
        }
        String email;                          //Creates local variable, Code block validates email to contain @ and . and gets input
        while (true) {
            System.out.print("Enter person email: ");
            email = input.next();
            if (email.contains("@") && email.contains(".")) {
                break; //email validated
            } else {
                System.out.println("\n ---ERROR: Invalid email address. E-mail should contain '@' and '.'---");
            }
        }
        double price;                         //Creates local variable, Code block validates price to numeric input in range and gets input
        while (true) {
            System.out.println("Ticket price range: Row 01 -> $10-$19 | Row 02 -> $20-$29 | Row 03 -> $30-$39");
            System.out.print("Enter ticket price : ");
            try{
                price = input.nextDouble();
                if (rowNum==1 && 9 <price && price<20){break;}
                else if (rowNum==2 && 19<price && price<30){break;}
                else if (rowNum==3 && 29<price && price<40){break;}
                else {
                    System.out.println("\n ---ERROR: Price should be within the given range---");
                }
            } catch (Exception E){
                System.out.println("\n ---ERROR: Price should be a numeric value within the range---");
                input.nextLine();                                    //Moves the scanner to the next line
            }
        }
        Person person = new Person(name, surname, email);           //Creates new person object with the user input
        Ticket ticket = new Ticket(rowNum, seatNum, price, person,tickets.size()+1);       //Creates new ticket object with the user input (Ticket number is size of list +1)
        tickets.add(ticket);                                        //Adds tickets to arraylist of tickets
    }
    public static int userChoice(String message){                   //Ask user whether to buy/cancel ticket again or return to menu
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print(message);
                int num = input.nextInt();
                if (num == 1 || num == 0) {
                    return num;
                } else {
                    System.out.println("Please enter either 1 or 0");
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter either 1 or 0");
            }
        }
    }
    public static ArrayList<Ticket> mergeSort(ArrayList<Ticket> tickets,int start, int end){
        ArrayList<Ticket> sortedArray = new ArrayList<>();                                    //creates new list to store sorted tickets
        if (start<end){                                                                       //if array has more than one item recursively break down the arrays into smaller arrays
            int mid = (start+end)/2;
            ArrayList<Ticket> leftArray = mergeSort(tickets, start, mid);
            ArrayList<Ticket> rightArray = mergeSort(tickets, mid+1, end);
            sortedArray = merge(leftArray,rightArray);                                        //uses merge function to sort all the items in the list
        } else {                                                                              // if the array only has one item (start=end) add item into sorted list
            sortedArray.add(tickets.get(start));
        }
        return sortedArray;                                                                   //returns the sorted array of input array
    }
    public static ArrayList<Ticket> merge(ArrayList<Ticket> arrayOne,ArrayList<Ticket> arrayTwo){
        ArrayList<Ticket> mergedArray = new ArrayList<>();                                  //creates new array to sort and merge two arrays
        int indexOne = 0, indexTwo =0;                                                      //index of the two passed arrays
        while(indexOne<arrayOne.size() && indexTwo< arrayTwo.size()){                       //iterates the size of each array
            if(arrayOne.get(indexOne).getPrice() <= arrayTwo.get(indexTwo).getPrice()){     //checks if tic price of same index of array one is smaller than that of array two
                mergedArray.add(arrayOne.get(indexOne));                                    //assigns smaller value to the array
                indexOne++;
            }
            else{
                mergedArray.add(arrayTwo.get(indexTwo));                                    //otherwise if ticket price of array two is smaller assigns it first to the merged
                indexTwo++;
            }
        }
        while(indexOne< arrayOne.size()){                                                   //if item of array one is left add it to merged array, increase index
            mergedArray.add(arrayOne.get(indexOne));
            indexOne++;
        }
        while(indexTwo< arrayTwo.size()){                                                   //if item of array two is left assign it to merged array, increase index
            mergedArray.add(arrayTwo.get(indexTwo));
            indexTwo++;
        }
        return mergedArray;                                                                 //returns merged array with sorted values
    }
    public static void printAvailable(String row,int[] array){
        System.out.print("\nSeats available in "+row+" : ");
        int count = 0;
        int printCount = 1;
        for (int seat : array){                                             //Takes count of number of available seats in a row(To find last available seat)
            if (seat == 0){
                count++;
            }}
        for (int i=0 ; i<array.length; i++){
            if (array[i]==0 && printCount<count){                          //If the seat is available and not the last available seat, it's printed with a comma
                System.out.print((i+1)+", ");
                printCount++;
            }
            else if (array[i]==0 && printCount==count){                    //If the seat is available and is the last available seat in the row, it's printed with dot
                System.out.print((i+1)+".");
            }
        }
    }
}

//REFERENCES
//4COSC010C WEEK 6 LECTURE - File Handling --> Inspiration taken for save and load methods
//4COSC010C WEEK 8 LECTURE - Search & Sort --> Logic for the merge sort function
//JAVA DOCUMENTATION CLASS PATTERN --> Check whether string is alphabetical using .matches() link:https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html#sum